package com.group0201.motobikelicenseexam.model;

import java.io.Serializable;

public class TraficSign implements Serializable {
    private long Id;
    private String Name;
    private String Image;
    private String Content;
    private int Type;

    public TraficSign(long id, String name, String image, String content, int type) {
        Id = id;
        Name = name;
        Image = image;
        Content = content;
        Type = type;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }
}
