package com.vikras.model.processor;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileProvider {
    private final List<String> extensions;

    public FileProvider(List<String> extensions) {
        this.extensions = extensions;
    }

    public List<Path> getAllContains(Path root) throws IOException {
        try (Stream<Path> paths = Files.walk(root,1) ){
            return paths
                    .filter(Files::isRegularFile)
                    .filter(this::extensionFilter)
                    .collect(Collectors.toList());
        }
    }

    private boolean extensionFilter(Path path){
        String name = path.toString().toLowerCase(Locale.ROOT);
        return extensions.stream()
                .anyMatch(name::endsWith);

    }

    public static long getDirectorySize(File dir) {
        return FileUtils.sizeOfDirectory(dir);
    }
}
