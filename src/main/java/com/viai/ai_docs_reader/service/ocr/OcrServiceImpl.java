package com.viai.ai_docs_reader.service.ocr;

import com.viai.ai_docs_reader.exception.ErrorCode;
import com.viai.ai_docs_reader.exception.error.BusinessException;
import com.viai.ai_docs_reader.utils.FileConverter;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@Service
public class OcrServiceImpl implements OcrService{

    private final Tesseract tesseract;

    public OcrServiceImpl(Tesseract tesseract) {
        this.tesseract = tesseract;
    }

    @Override
    public String processFile(MultipartFile file) throws IOException, TesseractException {
        String mimeType = file.getContentType();
        if(mimeType == null) {
            throw new BusinessException(ErrorCode.INVALID_TYPE);
        }

        if(mimeType.startsWith("image/")) {
            BufferedImage image = ImageIO.read(file.getInputStream());
            return tesseract.doOCR(image);
        } else if (mimeType.equals("application/pdf")) {
            List<String> textPages = FileConverter.extractTextFromPdf(file);

            if (textPages.stream().anyMatch(text -> !text.trim().isEmpty())) {
                return String.join("\n", textPages);
            }
        }  else if (mimeType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
            return String.join("\n", FileConverter.extractTextFromDocx(file));
        }
        throw new BusinessException(ErrorCode.UNSUPPORTED_FILE_TYPE);
    }
}
