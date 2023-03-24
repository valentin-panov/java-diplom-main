package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;


public class Converter implements TextGraphicsConverter {

    private int maxWidth;
    private int maxHeight;
    private int maxRatio;
    private TextColorSchema schema = new ColorConverter();

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));
        int width = img.getWidth();
        int height = img.getHeight();

        if (height / width > maxRatio) {
            throw new BadImageSizeException((double) height / width, maxRatio);
        }

        //смущают эти касты, но лучше не придумал :( Если есть вариант лучше, просьба направить
        double scaleRatio = setScaleRatio(maxWidth, maxHeight, width, height);
        int newWidth = (int) ((double) width / scaleRatio);
        int newHeight = (int) ((double) height / scaleRatio);
        StringBuilder textPic = new StringBuilder();

        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        WritableRaster bwRaster = bwImg.getRaster();

        for (int h = 0; h < newHeight; h++) {
            for (int w = 0; w < newWidth; w++) {
                int color = bwRaster.getPixel(w, h, new int[3])[0];
                char c = schema.convert(color);
                textPic.append(c);
                textPic.append(c);
                if (w == newWidth - 1) {
                    textPic.append("\n");
                }
            }
        }
        return textPic.toString();
    }

    public double setScaleRatio(int maxWidth, int maxHeight, int width, int height) {
        if (maxWidth != 0) {
            return (double) width / maxWidth;
        } else if (maxHeight != 0) {
            return (double) height / maxHeight;
        } else {
            return 1;
        }
    }

    @Override
    public void setMaxWidth(int width) {
        maxWidth = width;
    }

    @Override
    public void setMaxHeight(int height) {
        maxHeight = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = (int) maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }
}
