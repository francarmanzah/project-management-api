package com.franc.appmanagement.appusers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
public class ImageUtility {

    public static byte[] compressImage(String imageUrl) throws IOException {
        BufferedImage image = ImageIO.read(new URL(imageUrl));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Compress the image
        ImageIO.write(image, "png", outputStream);

        return outputStream.toByteArray();
    }
}
