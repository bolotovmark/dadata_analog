package com.bolotovmd.dadata_analog.service;

import com.bolotovmd.dadata_analog.entity.Company;
import com.bolotovmd.dadata_analog.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class XmlParserService {
    private final CompanyRepository companyRepository;

    public XmlParserService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> parseXml(File file) {
        List<Company> companies = new ArrayList<>();
        try {
            // Установка фабрики и билдера для чтения XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Парсинг XML
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();

            NodeList companyNodes = document.getElementsByTagName("СвЮЛ");

            for (int i = 0; i < companyNodes.getLength(); i++) {
                Node node = companyNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Извлечение данных
                    String vypiskaDateStr = element.getAttribute("ДатаВып");
                    Date vypiskaDate = new SimpleDateFormat("yyyy-MM-dd").parse(vypiskaDateStr);
                    String ogrn = element.getAttribute("ОГРН");
                    String inn = element.getAttribute("ИНН");
                    String kpp = element.getAttribute("КПП");

                    String fullName = getAttributeByTagName(element, "СвНаимЮЛ", "НаимЮЛПолн");
                    String shortName = getAttributeByTagName(element, "СвНаимЮЛСокр", "НаимСокр");

                    // Проверка существования записи в базе данных
                    Company existingCompany = companyRepository.findByOgrn(ogrn);
                    if (existingCompany != null) {
                        // Если запись существует, проверяем дату
                        if (existingCompany.getVypiska_date().before(vypiskaDate)) {
                            existingCompany.setVypiska_date(vypiskaDate);
                            existingCompany.setInn(inn);
                            existingCompany.setKpp(kpp);
                            existingCompany.setFull_name(fullName);
                            existingCompany.setShort_name(shortName);
                            companyRepository.save(existingCompany); // Обновляем запись
                            companies.add(existingCompany);
                        }
                    } else {
                        // Если записи нет, создаём новую
                        Company newCompany = new Company(null, vypiskaDate, ogrn, inn, kpp, fullName, shortName);
                        companies.add(newCompany);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Сохраняем все новые записи в базу
        companyRepository.saveAll(companies);
        return companies;
    }

    // Вспомогательный метод для получения атрибута из вложенного тега
    private String getAttributeByTagName(Element parent, String tagName, String attributeName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            Element element = (Element) nodeList.item(0);
            return element.getAttribute(attributeName);
        }
        return null;
    }
}
