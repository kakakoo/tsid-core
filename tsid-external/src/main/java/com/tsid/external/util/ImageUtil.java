package com.tsid.external.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtil {

    public static InputStream resize(String fileName, InputStream is, int maxWidth, int maxHeight) throws IOException {
        Image srcImg = ImageIO.read(is);
        String suffix = fileName.substring(fileName.lastIndexOf('.')+1).toLowerCase();
        Image imgTarget = srcImg.getScaledInstance(maxWidth, maxHeight, Image.SCALE_SMOOTH);
        int pixels[] = new int[maxWidth * maxHeight];
        PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, maxWidth, maxHeight, pixels, 0, maxWidth);
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
            throw new IOException(e.getMessage());
        }
        BufferedImage destImg = new BufferedImage(maxWidth, maxHeight, BufferedImage.TYPE_INT_RGB);
        destImg.setRGB(0, 0, maxWidth, maxHeight, pixels, 0, maxWidth);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(destImg, suffix, os);
        InputStream destIs = new ByteArrayInputStream(os.toByteArray());

        return destIs;
    }
}
