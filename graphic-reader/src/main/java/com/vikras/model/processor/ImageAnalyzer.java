package com.vikras.model.processor;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.Sanselan;


import java.io.IOException;
import java.nio.file.Path;

import java.util.*;

public class ImageAnalyzer {

    static public List<String> getSupportedExtensionList() {
        return new ArrayList<>() {
            {
                add(".jpg");
                add(".png");
                add(".bmp");
                add(".tif");
                add(".gif");
                add(".pcx");
            }
        };
    }

    private static final Map<String, String> params;

    static {
        {
            params = new HashMap<>();
            params.put("Image Height", "height");
            params.put("Y Max", "height");
            params.put("Image Width", "width");
            params.put("X Max", "width");

            params.put("Vertical DPI", "dpiH");
            params.put("Pixels Per Unit Y", "dpiH");
            params.put("Y Resolution", "dpiH");

            params.put("Horizontal DPI", "dpiW");
            params.put("Pixels Per Unit X", "dpiW");
            params.put("X Resolution", "dpiW");

            params.put("Bits per Pixel", "colorDepth");
            params.put("Bits Per Pixel", "colorDepth");
            params.put("Data Precision", "colorDepth");
            params.put("Color Planes", "colorDepth");
            params.put("Bits Per Sample", "colorDepth");

            params.put("Compression", "zipping");
            params.put("Compression Type", "zipping");
        }
    }


    public static ImageInfo getImageInfos(Path path) throws ImageProcessingException, IOException, ImageReadException {
        var ans = new ImageInfo();
        ans.setFileName(path.getFileName().toString());
        setHResolution(path, ans);
        setWResolution(path, ans);

        Metadata metadata = ImageMetadataReader.readMetadata(path.toFile());
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                if (params.containsKey(tag.getTagName())) {
                    String field = params.get(tag.getTagName());
                    ans.setField(field, tag.getDescription());
                }
//                System.out.format("[%s] - %s = %s\n",
//                        directory.getName(), tag.getTagName(), tag.getDescription());

                if (directory.hasErrors()) {
                    for (String error : directory.getErrors()) {
                        System.err.format("ERROR: %s", error);
                    }
                }
            }

        }
        return ans;
    }


    private static ImageInfo setHResolution(Path path, ImageInfo info) throws IOException {
        org.apache.sanselan.ImageInfo imageInfo = null;
        try {
            imageInfo = Sanselan.getImageInfo(path.toFile());
            info.setDpiH(String.valueOf(imageInfo.getPhysicalHeightDpi()));
        } catch (ImageReadException e) {
        }
        return info;
    }

    private static ImageInfo setWResolution(Path path, ImageInfo info) throws IOException {
        final org.apache.sanselan.ImageInfo imageInfo;
        try {
            imageInfo = Sanselan.getImageInfo(path.toFile());
            info.setDpiW(String.valueOf(imageInfo.getPhysicalWidthDpi()));
        } catch (ImageReadException e) {
        }
        return info;
    }


}
