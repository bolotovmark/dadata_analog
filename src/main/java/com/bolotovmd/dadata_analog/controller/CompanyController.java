package com.bolotovmd.dadata_analog.controller;

import com.bolotovmd.dadata_analog.entity.Company;
import com.bolotovmd.dadata_analog.service.CompanyService;
import com.bolotovmd.dadata_analog.service.XmlParserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class CompanyController {
    private final XmlParserService xmlParserService;
    private final CompanyService companyService;

    public CompanyController(XmlParserService xmlParserService, CompanyService companyService) {
        this.xmlParserService = xmlParserService;
        this.companyService = companyService;
    }

    @GetMapping("/u")
    public String uploadForm() {
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
        try {
            // Сохранение загруженного файла во временное хранилище
            File tempFile = File.createTempFile("uploaded", ".xml");
            file.transferTo(tempFile);

            // Парсинг XML и сохранение данных в базу
            List<Company> companies = xmlParserService.parseXml(tempFile);
            companyService.saveCompanies(companies);

            model.addAttribute("message", "Файл успешно обработан и данные сохранены в базу!");
            model.addAttribute("companies", companies);
        } catch (IOException e) {
            model.addAttribute("message", "Ошибка обработки файла: " + e.getMessage());
        }
        return "upload";
    }
}
