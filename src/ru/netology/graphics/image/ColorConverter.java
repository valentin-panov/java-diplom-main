package ru.netology.graphics.image;

public class ColorConverter implements TextColorSchema{

    private char[] mapper;
    private double k;

    public ColorConverter() {
        this.mapper = new char[]{'#', '$', '@', '%', '*', '+', '-', '\''};
        k = ((double) mapper.length) / 256d;
    }

    public ColorConverter(char[] mapper) {
        this.mapper = mapper.clone();
        k = ((double) mapper.length) / 256d;
    }

    @Override
    public char convert(int color) {
        int intColor = (int) (color * k);
        return mapper[intColor];
    }

}
