package com.viai.ai_docs_reader.utils;

public class TextPostProcessor {

    public static String postProcess(String ocrText) {
        if (ocrText == null || ocrText.isEmpty()) {
            return ocrText;
        }

        String[] lines = ocrText.split("\\r?\\n");
        StringBuilder processedText = new StringBuilder();

        for (String line : lines) {
            line = line.replaceAll("[^\\p{L}\\p{N}\\p{P}\\p{Z}\\n]", "").trim();
            if (!line.isEmpty()) {
                processedText.append(line).append("\n");
            }
        }

        return processedText.toString().trim();
    }
}
