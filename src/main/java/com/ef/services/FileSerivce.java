package com.ef.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileSerivce {
    @Value("${app.readFileLinesCount}") private Integer linesCount;

    public void read(String filePath, ReadableStringList strReader) throws FileNotFoundException {
        if (!new File(filePath).exists()) {
            throw new FileNotFoundException("File doesn't exists");
        }

        String line;
        List<String> records = new ArrayList<>(linesCount);

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            for (int counter = 0; (line = reader.readLine()) != null; ++counter) {
                if (counter == linesCount) {
                    strReader.read(records);
                    counter = 0;
                    records = new ArrayList<>(linesCount);
                }

                records.add(line);
            }

            if (!records.isEmpty()) {
                strReader.read(records);
            }

        } catch (IOException e) {
            throw new FileNotFoundException("File " + filePath + " cannot be open");
        }
    }
}
