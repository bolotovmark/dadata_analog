package com.bolotovmd.dadata_analog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.bolotovmd.dadata_analog.entity.Company;
import com.bolotovmd.dadata_analog.entity.CompanyDoc;
import com.bolotovmd.dadata_analog.repository.CompanyElasticsearchRepository;
import com.bolotovmd.dadata_analog.repository.CompanyRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Comparator.comparingInt;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyElasticsearchRepository companyElasticsearchRepository;

    public Page<Company> searchCompany(String query, int page, int size) {
        var pageable = PageRequest.of(page, size);
        return companyRepository.searchByQuery(query, pageable);
    }

    public Page<Company> searchCompanyViaElastic(String searchText, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CompanyDoc> searchResults = companyElasticsearchRepository.searchByQuery(searchText, pageable);

        Map<Long, Integer> idsMap = new HashMap<>();
        List<CompanyDoc> companyDocs = searchResults.getContent();
        for (int i = 0; i < companyDocs.size(); i++) {
            idsMap.put(companyDocs.get(i).getId(), i);
        }

        Set<Long> ids = idsMap.keySet();

        List<Company> companyFromDb = companyRepository.findAllById(ids);
        companyFromDb.sort(comparingInt(company -> idsMap.get(company.getId())));

        return new PageImpl<>(companyFromDb, pageable, searchResults.getTotalElements());
    }
}
