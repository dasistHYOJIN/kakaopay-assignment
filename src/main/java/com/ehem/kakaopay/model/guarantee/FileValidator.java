package com.ehem.kakaopay.model.guarantee;

import com.ehem.kakaopay.model.guarantee.exception.IllegalFileFormatException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Component
public class FileValidator {
    private static final String CSV_CONTENT_TYPE = "text/csv";

    public MultipartFile validate(final MultipartFile file) {
        String contentType = Objects.requireNonNull(file).getContentType();

        if (isNotManageableContentType(contentType)) {
            throw new IllegalFileFormatException(String.format("%s는 적합하지 않은 파일 확장자입니다.", contentType));
        }

        return file;
    }

    private boolean isNotManageableContentType(final String contentType) {
        return !contentType.equals(CSV_CONTENT_TYPE);
    }
}
