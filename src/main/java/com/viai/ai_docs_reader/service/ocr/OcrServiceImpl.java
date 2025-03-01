package com.viai.ai_docs_reader.service.ocr;

import com.viai.ai_docs_reader.exception.ErrorCode;
import com.viai.ai_docs_reader.exception.error.BusinessException;
import com.viai.ai_docs_reader.utils.FileConverter;
import com.viai.ai_docs_reader.utils.TextPostProcessor;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.List;

@Service
public class OcrServiceImpl implements OcrService{

    private final Tesseract tesseract;

    public OcrServiceImpl(Tesseract tesseract) {
        this.tesseract = tesseract;
    }

    @Override
    public String processFile(MultipartFile file) throws Exception {
        String mimeType = file.getContentType();
        if(mimeType == null) {
            throw new BusinessException(ErrorCode.INVALID_TYPE);
        }

        String rawText = "";
        if (mimeType.startsWith("image/")) {
            BufferedImage image = ImageIO.read(file.getInputStream());
            rawText = tesseract.doOCR(image);
        } else if (mimeType.equals("application/pdf")) {
            List<String> textPages = FileConverter.extractTextFromPdf(file);
            if (textPages.stream().anyMatch(text -> !text.trim().isEmpty())) {
                rawText = String.join("\n", textPages);
            }
        } else if (mimeType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
            rawText = String.join("\n", FileConverter.extractTextFromDocx(file));
        } else {
            throw new BusinessException(ErrorCode.UNSUPPORTED_FILE_TYPE);
        }

        return TextPostProcessor.postProcess(rawText);
    }
}
