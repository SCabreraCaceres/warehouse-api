package com.assignment.warehouse;

import com.google.gson.Gson;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;

@Configuration
public class JsonReader {

    public <T> T deserializeTo(String fileName, Class<T> classOf) throws ReadFileException {
        Gson gson = new Gson();
        BufferedReader reader;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
            reader = Files.newBufferedReader(file.toPath());
        } catch (Exception e) {
            throw new ReadFileException("Error reading file: ", e);
        }
        return gson.fromJson(reader, classOf);
    }
}
