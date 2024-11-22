package com.bolotovmd.dadata_analog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bolotovmd.dadata_analog.entity.Company;
import com.bolotovmd.dadata_analog.entity.CompanyDoc;
import com.bolotovmd.dadata_analog.repository.CompanyElasticsearchRepository;
import com.bolotovmd.dadata_analog.repository.CompanyRepository;

import java.util.List;

@Service
public class CompanyIndexingService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyElasticsearchRepository companyElasticsearchRepository;

    @Transactional(readOnly = true)
    public void reindexAllCompany() {
        List<Company> companies = companyRepository.findAll();

        companyElasticsearchRepository.saveAll(
                companies.stream().map(
                        company -> new CompanyDoc(
                                company.getId(),
                                company.getFull_name(),
                                company.getInn()
                        )
                ).toList()
        );
    }
}
