package com.example.auesmanager.pojo;
public class Rasp {
    private String Dayl;
    private String Timel;
    private String SubGroup;
    private String Namel;
    private String Sensei;
    private String Rooml;


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

    public Rasp(String dayl, String timel, String subGroup, String namel, String sensei, String rooml) {
        Dayl = dayl;
        Timel = timel;
        SubGroup = subGroup;
        Namel = namel;
        Sensei = sensei;
        Rooml = rooml;
    }
}
