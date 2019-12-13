package com.ehem.kakaopay.util;

import com.ehem.kakaopay.util.vo.Record;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {
    public static List<Record> readFile(final MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);

        return bufferedReader.lines()
                .map(Record::new)
                .collect(Collectors.toList());
    }
}
