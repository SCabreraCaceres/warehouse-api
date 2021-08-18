package com.assignment.warehouse;

import java.io.IOException;

public class ReadFileException extends IOException {

    public ReadFileException(String message, Throwable err) {
        super(message, err);
    }
}
