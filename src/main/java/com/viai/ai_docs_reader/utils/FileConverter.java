package com.viai.ai_docs_reader.utils;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileConverter {
    public static List<String> extractTextFromPdf(MultipartFile file) throws IOException {
        List<String> pagesText = new ArrayList<>();
        PDDocument document = Loader.loadPDF(file.getInputStream().readAllBytes());
        PDFTextStripper stripper = new PDFTextStripper();
        int totalPages = document.getNumberOfPages();
        for (int i = 1; i <= totalPages; i++) {
            stripper.setStartPage(i);
            stripper.setEndPage(i);
            String pageText = stripper.getText(document);
            pagesText.add(pageText);
        }
        document.close();
        return pagesText;
    }

    public static List<String> extractTextFromDocx(MultipartFile file) throws IOException {
        try (InputStream is = file.getInputStream();
             XWPFDocument document = new XWPFDocument(is);
             XWPFWordExtractor extractor = new XWPFWordExtractor(document)) {

            String fullText = extractor.getText();

            if (fullText.contains("\f")) {
                return Arrays.asList(fullText.split("\f"));
            } else {
                return List.of(fullText);
            }
        }
    }
}
