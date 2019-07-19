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

    public String getSrc() {
        return src;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
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
