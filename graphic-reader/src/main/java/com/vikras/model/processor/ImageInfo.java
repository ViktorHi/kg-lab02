package com.vikras.model.processor;

public class ImageInfo {
    private String fileName     = "" ;
    private String height       = "" ;
    private String width        = "" ;
    private String dpiH         = "" ;
    private String dpiW         = "" ;
    private String zipping      = "-" ;
    private String colorDepth   = "" ;

    void setField(String field, String value) {
        if (field == null || value == null) return;
        switch (field) {
            case "height" -> setHeight(value);
            case "width" -> setWidth(value);
            case "dpiH" -> setDpiH(value);
            case "dpiW" -> setDpiW(value);
            case "colorDepth" -> setColorDepth(value);
            case "zipping" -> setZipping(value);
            default ->  throw new RuntimeException("Unknown field name");
        }
    }

    public void setFileName(String fileName) {
        if (this.fileName.length() <= fileName.length())
            this.fileName = fileName;
    }

    public void setHeight(String height) {
        if (this.height.length() <= height.length())
        this.height = height;
    }

    public void setWidth(String width) {
        if (this.width.length() <= width.length())
        this.width = width;
    }

    public void setDpiH(String dpiH) {
        if (this.dpiH.length() <= dpiH.length())
        this.dpiH = dpiH;
    }

    public void setDpiW(String dpiW) {
        if (this.dpiW.length() <= dpiW.length())
        this.dpiW = dpiW;
    }

    public void setColorDepth(String colorDepth) {
        if (this.colorDepth.length() <= colorDepth.length())
        this.colorDepth = colorDepth;
    }

    public void setZipping(String zipping) {
        if (this.zipping.length() <= zipping.length())
        this.zipping = zipping;
    }

    public String getFileName() {
        return fileName;
    }

    public String getHeight() {
        return height;
    }

    public String getWidth() {
        return width;
    }

    public String getDpiH() {
        return dpiH;
    }

    public String getDpiW() {
        return dpiW;
    }

    public String getZipping() {
        return zipping;
    }

    public String getColorDepth() {
        return colorDepth;
    }

    @Override
    public String toString() {
        return "ImageInfo{" +
                "fileName='" + fileName + '\'' +
                ", height='" + height + '\'' +
                ", width='" + width + '\'' +
                ", dpiH='" + dpiH + '\'' +
                ", dpiW='" + dpiW + '\'' +
                ", zipping='" + zipping + '\'' +
                ", colorDepth='" + colorDepth + '\'' +
                '}';
    }
}
