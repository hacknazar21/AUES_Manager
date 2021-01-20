package com.example.auesmanager.pojo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todos")
public class TODO {
    @PrimaryKey(autoGenerate = true)
    private byte Id = 0;
    private String TextDo;
    private String TextDate;
    private int ImageLink;
    private String IsYes;

    public void setTextDo(String TextDo) {
        this.TextDo = TextDo;
    }
    public void setTextDate(String TextDate) {
        this.TextDate = TextDate;
    }
    public void setImageLink(int ImageLink) {
        this.ImageLink = ImageLink;
    }
    public void setId(byte Id) {
        this.Id = Id;
    }
    public void setIsYes(String IsYes) {
        this.IsYes = IsYes;
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
    public byte getId() {
        return Id;
    }


    public TODO(String TextDo, String TextDate, int ImageLink, String IsYes) {
        this.TextDo = TextDo;
        this.TextDate = TextDate;
        this.ImageLink = ImageLink;
        this.IsYes = IsYes;
    }
}

