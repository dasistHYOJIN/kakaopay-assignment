package com.ehem.kakaopay.model.guarantee.service.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GuaranteeFileRequestDto {
    private static final String CSV_CONTENT_TYPE = "text/csv";

    @NotNull
    private MultipartFile file;

    public GuaranteeFileRequestDto(@NotNull final MultipartFile file) {
        this.file = validateFileContentType(file);
    }

    private MultipartFile validateFileContentType(final @NotNull MultipartFile file) {
        String contentType = file.getContentType();

        if (isNotManageableContentType(Objects.requireNonNull(contentType))) {
            throw new IllegalArgumentException(String.format("%s는 적합하지 않은 파일 확장자입니다.", contentType));
        }

        return file;
    }

    private boolean isNotManageableContentType(final String contentType) {
        return !contentType.equals(CSV_CONTENT_TYPE);
    }
}
