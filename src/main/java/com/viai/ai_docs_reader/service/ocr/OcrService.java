package com.viai.ai_docs_reader.service.ocr;

import net.sourceforge.tess4j.TesseractException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface OcrService {
    String processFile(MultipartFile file) throws IOException, TesseractException;
}
