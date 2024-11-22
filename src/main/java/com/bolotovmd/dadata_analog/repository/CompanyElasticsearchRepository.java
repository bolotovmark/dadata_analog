package com.bolotovmd.dadata_analog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.bolotovmd.dadata_analog.entity.CompanyDoc;

public interface CompanyElasticsearchRepository extends ElasticsearchRepository<CompanyDoc, Long> {
    @Query("""
            {
              "bool": {
                "should": [
                  {
                    "multi_match": {
                      "query": "?0",
                      "fields": ["full_name^4", "inn^3"],
                      "type": "best_fields",
                      "operator": "or"
                    }
                  },
                  {
                    "match_phrase": {
                      "full_name": {
                        "query": "?0",
                        "boost": 3
                      }
                    }
                  },
                  {
                    "match_phrase": {
                      "inn": {
                        "query": "?0",
                        "boost": 2
                      }
                    }
                  }
                ],
                "minimum_should_match": 1
              }
            }
            """)
    Page<CompanyDoc> searchByQuery(String query, Pageable pageable);
}
