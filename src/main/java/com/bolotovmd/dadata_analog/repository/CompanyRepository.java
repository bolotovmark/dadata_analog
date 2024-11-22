package com.bolotovmd.dadata_analog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.bolotovmd.dadata_analog.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
    @Query("""
                SELECT m FROM Company m
                WHERE LOWER(m.full_name) LIKE LOWER(CONCAT('%', :query, '%'))
                OR LOWER(m.inn) LIKE LOWER(CONCAT('%', :query, '%'))
                ORDER BY LOWER(m.full_name) DESC
            """)
    Page<Company> searchByQuery(@Param("query") String query, Pageable pageable);
}
