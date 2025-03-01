package com.viai.ai_docs_reader.utils;

import static org.bytedeco.opencv.global.opencv_core.*;
import com.viai.ai_docs_reader.exception.ErrorCode;
import com.viai.ai_docs_reader.exception.error.BusinessException;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_imgproc.CLAHE;

import static org.bytedeco.opencv.global.opencv_imgcodecs.*;
import static org.bytedeco.opencv.global.opencv_imgproc.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ImagePreprocessor {


    public static BufferedImage preprocess(BufferedImage image) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] bytes = baos.toByteArray();

        // Đọc thành Mat màu (3 kênh)
        Mat inputMat = imdecode(new Mat(bytes), IMREAD_COLOR);
        if (inputMat.empty()) {
            throw new BusinessException(ErrorCode.INVALID_TYPE);
        }

        // 2. Chuyển sang grayscale
        Mat grayMat = new Mat();
        cvtColor(inputMat, grayMat, COLOR_BGR2GRAY);

        // 3. Lọc nhiễu GaussianBlur
        GaussianBlur(grayMat, grayMat, new Size(3, 3), 0);

        // 4. Adaptive threshold -> tạo ảnh nhị phân
        Mat binaryMat = new Mat();
        adaptiveThreshold(grayMat, binaryMat, 255, ADAPTIVE_THRESH_GAUSSIAN_C,
                THRESH_BINARY, 11, 2);

        // 5. (Tùy chọn) Deskew để chỉnh nghiêng
        binaryMat = deskew(binaryMat);

        // 6. Morphological Close để lấp lỗ hổng nhỏ
        Mat kernel = getStructuringElement(MORPH_RECT, new Size(2, 2));
        morphologyEx(binaryMat, binaryMat, MORPH_CLOSE, kernel);

        // 7. Chuyển Mat -> BufferedImage
        return matToBufferedImage(binaryMat);
    }

    /**
     * deskew: ước lượng góc nghiêng dựa trên moment của ảnh nhị phân và xoay lại.
     * Chỉ hoạt động tốt khi văn bản chiếm phần lớn ảnh và nghiêng nhẹ (< 45 độ).
     */
    private static Mat deskew(Mat src) {
        Moments m = moments(src, true);
        double mu11 = m.mu11();
        double mu20 = m.mu20();
        double mu02 = m.mu02();

        // Tính góc nghiêng
        double skewAngle = -Math.atan2(2 * mu11, mu20 - mu02) * 180.0 / Math.PI;

        // Nếu |skewAngle| > 45, có thể ảnh bị tính sai
        if (Math.abs(skewAngle) > 45) {
            return src.clone(); // Không xoay
        }

        // Tạo ma trận xoay
        Point2f center = new Point2f(src.cols() / 2.0f, src.rows() / 2.0f);
        Mat rotMatrix = getRotationMatrix2D(center, skewAngle, 1.0);

        // Xoay ảnh
        Mat result = new Mat();
        warpAffine(src, result, rotMatrix, src.size(),
                INTER_CUBIC, BORDER_CONSTANT, new Scalar(255, 255, 255, 255));
        return result;
    }

    /**
     * Chuyển Mat -> BufferedImage
     */
    private static BufferedImage matToBufferedImage(Mat mat) throws Exception {
        try (BytePointer bytePointer = new BytePointer()) {
            // Mã hóa Mat thành định dạng PNG (có thể dùng JPG tùy ý)
            if (!imencode(".png", mat, bytePointer)) {
                throw new Exception("Lỗi imencode: không thể mã hóa Mat thành PNG.");
            }
            byte[] buffer = new byte[(int) bytePointer.limit()];
            bytePointer.get(buffer);
            try (ByteArrayInputStream bais = new ByteArrayInputStream(buffer)) {
                return ImageIO.read(bais);
            }
        }
    }

    /**
     * (Tuỳ chọn) Nâng cao độ tương phản ảnh xám bằng CLAHE
     */
    private static Mat enhanceContrast(Mat grayMat) {
        Mat result = new Mat();
        try (CLAHE clahe = createCLAHE(2.0, new Size(8, 8))) {
            clahe.apply(grayMat, result);
        }
        return result;
    }
}
