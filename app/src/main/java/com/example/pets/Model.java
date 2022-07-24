package com.example.pets;

public class Model {
    private int image;
    private String title;
    private String desc;
    private int audio;

    public Model(int image, String desc, String title){
        this.image = image;
        this.title = title;
        this.desc = desc;
    }

    public int getImage(){
        return image;
    }
    public void setImage(int image){
        this.image = image;
    }

    public int getAudio(){
        return audio;
    }
    public  void setAudio(int audio){
        this.audio = audio;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getDesc(){
        return desc;
    }
    public void setDesc (String desc){
        this.desc = desc;
    }
}
