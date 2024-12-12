package com.bolotovmd.dadata_analog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "company")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date vypiska_date;

    @Column(columnDefinition = "TEXT")
    private String ogrn;

    private Date ogrn_date;

    @Column(columnDefinition = "TEXT")
    private String inn;

    @Column(columnDefinition = "TEXT")
    private String kpp;

    @Column(columnDefinition = "TEXT")
    private String type;

    @Column(columnDefinition = "TEXT")
    private String full_name;

    @Column(columnDefinition = "TEXT")
    private String short_name;
}
