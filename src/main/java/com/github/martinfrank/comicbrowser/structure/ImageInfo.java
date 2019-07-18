package com.github.martinfrank.comicbrowser.structure;

public class ImageInfo {

    private final String src;
    private final int width;
    private final int height;

    public ImageInfo(final int width, final int height, final String src) {
        this.src = src;
        this.width = width;
        this.height = height;
    }

    String getSrc() {
        return src;
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "ImageInfo{" +
                "src='" + src + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
