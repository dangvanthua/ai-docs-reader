package com.viai.ai_docs_reader.util;

import org.bytedeco.javacv.Java2DFrameUtils;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.awt.image.BufferedImage;

public class ImagePreprocessor {
    public static Mat preprocess(BufferedImage image) {
        Mat mat = Java2DFrameUtils.toMat(image);
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2GRAY);
        Imgproc.GaussianBlur(mat, mat, new Size(5, 5), 0);
        Imgproc.adaptiveThreshold(mat, mat, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 11, 2);
        return mat;
    }
}
