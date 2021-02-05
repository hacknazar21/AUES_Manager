package com.example.auesmanager.pojo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "rasps")
public class Rasp {
    @PrimaryKey(autoGenerate = true)
    private byte Id = 0;
    private String Dayl;
    private String Timel;
    private String SubGroup;
    private String Namel;
    private String Sensei;
    private String Rooml;


    public void setDayl(String Dayl) {
        this.Dayl = Dayl;
    }
    public void setTimel(String Timel) {
        this.Timel = Timel;
    }
    public void setSubGroup(String SubGroup) {
        this.SubGroup = SubGroup;
    }
    public void setId(byte Id) {
        this.Id = Id;
    }
    public void setNamel(String Namel) {
        this.Namel = Namel;
    }
    public void setSensei(String Sensei) {
        this.Sensei = Sensei;
    }
    public void setRooml(String Rooml) {
        this.Rooml = Rooml;
    }

    public String getDayl() {
        return Dayl;
    }
    public String getTimel() {
        return Timel;
    }
    public String getSubGroup() {
        return SubGroup;
    }
    public String getNamel() {
        return Namel;
    }
    public String getSensei() {
        return Sensei;
    }
    public String getRooml() {
        return Rooml;
    }
    public byte getId() {
        return Id;
    }


    public Rasp(String Dayl, String Timel, String SubGroup, String Namel, String Sensei, String Rooml) {
        this.Dayl = Dayl;
        this.Timel = Timel;
        this.SubGroup = SubGroup;
        this.Namel = Namel;
        this.Sensei = Sensei;
        this.Rooml = Rooml;
    }
}
