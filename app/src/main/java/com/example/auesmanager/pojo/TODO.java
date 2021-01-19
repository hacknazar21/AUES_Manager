package com.example.auesmanager.pojo;

public class TODO {
    private final String TextDo;
    private final String TextDate;
    private final int ImageLink;
    private final String IsYes;



    public TODO(String textDo, String textDate, int imageLink, String isYes) {
        TextDo = textDo;
        TextDate = textDate;
        ImageLink = imageLink;
        IsYes = isYes;
    }

    public String getTextDo() {
        return TextDo;
    }

    public String getTextDate() {
        return TextDate;
    }

    public int getImageLink() {
        return ImageLink;
    }

    public String getIsYes() {
        return IsYes;
    }
}
