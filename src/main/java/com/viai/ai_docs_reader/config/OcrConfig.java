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

    @Value("${ocr.psm:3}")
    private int pageSegMode;

    @Value("${ocr.oem:1}")
    private int ocrEngineMode;

    @Bean
    public Tesseract tesseractBean() {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(tessdataPath);
        tesseract.setLanguage(language);
        return tesseract;
    }
}
