package com.viai.ai_docs_reader.controller;

import com.viai.ai_docs_reader.dto.response.ApiResponse;
import com.viai.ai_docs_reader.exception.ErrorCode;
import com.viai.ai_docs_reader.exception.error.BusinessException;
import com.viai.ai_docs_reader.service.ocr.OcrService;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/ocr")
@RequiredArgsConstructor
public class OcrController {

    private final OcrService ocrService;

    @PostMapping(value = "/uploads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<String> uploadFile(@RequestParam("file")MultipartFile file) {
        try {
           String text = ocrService.processFile(file);
           return ApiResponse.<String>builder()
                   .message("Extract text from file success")
                   .data(text)
                   .build();
        } catch (IOException | TesseractException e) {
            throw new BusinessException(ErrorCode.INVALID_TYPE);
        }
    }
}
