package com.tripster.project.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class VerificationEmailBuilder {

    public static String build(String path, String name, String link) {

        String template;

        try {
            template = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return template.replace("{{name}}", name).replace("{{link}}", link);
    }

}
