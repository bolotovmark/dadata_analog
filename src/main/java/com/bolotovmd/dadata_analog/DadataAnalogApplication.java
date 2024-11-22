package com.bolotovmd.dadata_analog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories
public class DadataAnalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(DadataAnalogApplication.class, args);
	}

}
