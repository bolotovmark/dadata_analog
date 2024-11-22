package com.bolotovmd.dadata_analog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bolotovmd.dadata_analog.entity.Company;
import com.bolotovmd.dadata_analog.service.CompanyService;

@Controller
public class CompanySearchController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/")
    public String home() {
        return "search";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam("query") String query,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            Model model
    ) {
        Page<Company> companysPage = companyService.searchCompanyViaElastic(query, page, size);
        model.addAttribute("companysPage", companysPage);
        model.addAttribute("query", query);
        return "search";
    }
}
