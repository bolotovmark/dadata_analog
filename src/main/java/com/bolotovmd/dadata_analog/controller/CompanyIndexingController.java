package com.bolotovmd.dadata_analog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bolotovmd.dadata_analog.service.CompanyIndexingService;

@RestController
public class CompanyIndexingController {

    @Autowired
    private CompanyIndexingService companyIndexingService;

    @GetMapping("/reindex")
    public String reindexCompany() {
        companyIndexingService.reindexAllCompany();
        return "Переиндексация завершена!";
    }
}
