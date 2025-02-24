package com.viai.ai_docs_reader;

import com.viai.ai_docs_reader.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class DjlDocsReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(DjlDocsReaderApplication.class, args);
	}

}
