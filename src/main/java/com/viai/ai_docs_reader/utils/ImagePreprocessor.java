package com.viai.ai_docs_reader.utils;

import org.bytedeco.javacv.Java2DFrameUtils;
import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.global.opencv_imgproc;

import java.awt.image.BufferedImage;

public class ImagePreprocessor {
    public static BufferedImage preprocessForOcr(BufferedImage image) {
        Mat src = Java2DFrameUtils.toMat(image);

        // Kiểm tra số kênh của ảnh
        Mat gray = new Mat();
        if (src.channels() > 1) {
            opencv_imgproc.cvtColor(src, gray, opencv_imgproc.COLOR_BGR2GRAY);
        } else {
            src.copyTo(gray);
        }

        // Làm mờ
        Mat blurred = new Mat();
        opencv_imgproc.GaussianBlur(gray, blurred, new Size(5, 5), 0);

        // Phân ngưỡng thích ứng
        Mat binary = new Mat();
        opencv_imgproc.adaptiveThreshold(blurred, binary, 255,
                opencv_imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, opencv_imgproc.THRESH_BINARY, 11, 2);

        // Giãn nở để nối các ký tự
        Mat dilated = new Mat();
        Mat kernel = opencv_imgproc.getStructuringElement(opencv_imgproc.MORPH_RECT, new Size(3, 3));
        opencv_imgproc.dilate(binary, dilated, kernel);

        return Java2DFrameUtils.toBufferedImage(dilated);
    }
}
