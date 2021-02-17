package com.vikras.model;

import com.drew.imaging.ImageProcessingException;
import com.vikras.model.processor.FileProvider;
import com.vikras.model.processor.ImageAnalyzer;
import com.vikras.model.processor.ImageInfo;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Engine {

    FileProvider fileProvider = new FileProvider(ImageAnalyzer.getSupportedExtensionList());


    public List<ImageInfo> getImageInfo(Path directory) throws ImageProcessingException, IOException {
        return fileProvider.getAllContains(directory).stream()
                .map(wrapper(ImageAnalyzer::getImageInfos))
                .collect(Collectors.toList());
    }

    static <T, S, E extends Exception> Function<T, S> wrapper(ThrowingFunction<T, E ,S> throwingConsumer) {
        return i -> {
            try {
               return throwingConsumer.accept(i);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }
}
