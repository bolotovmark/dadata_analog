package com.bolotovmd.dadata_analog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Setting;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "company_index")
@Setting(settingPath = "/elasticsearch-settings.json")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDoc {
    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "russian_analyzer")
    private String full_name;

    @Field(type = FieldType.Text, analyzer = "russian_analyzer")
    private String inn;
}
