package com.viai.ai_docs_reader.config;

import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OcrConfig {

    @Value("${ocr.tessdata.path}")
    private String tessdataPath;

    @Value("${ocr.language}")
    private String language;

    @Bean
    public Tesseract tesseractBean() {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(tessdataPath);
        tesseract.setLanguage(language);
        return tesseract;
    }
}
