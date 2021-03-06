package com.assignment.warehouse;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;

import static java.nio.file.Files.newBufferedReader;

@Component
public class JsonReader {

    public <T> T deserializeTo(String fileName, Class<T> classOf) throws ReadFileException {
        Gson gson = new Gson();
        BufferedReader reader;

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
            reader = newBufferedReader(file.toPath());
        } catch (Exception e) {
            throw new ReadFileException("Error reading file: ", e);
        }

        return gson.fromJson(reader, classOf);
    }
}
