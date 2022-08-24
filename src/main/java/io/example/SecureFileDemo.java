package io.example;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

public class SecureFileDemo {

    public static void main(String[] args) {
        try {
            File.createTempFile("fred", "barney");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
