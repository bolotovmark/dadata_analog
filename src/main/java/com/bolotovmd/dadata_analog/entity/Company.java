package com.bolotovmd.dadata_analog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "company")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ogrn;

    private String inn;

    private String kpp;

    @Column(columnDefinition = "TEXT")
    private String full_name;

    @Column(columnDefinition = "TEXT")
    private String short_name;
}
