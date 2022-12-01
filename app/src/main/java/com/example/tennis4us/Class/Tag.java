package com.example.tennis4us.Class;

public class Tag {
    private String TagName;
    private int Image;
    public Tag() { }
    public Tag(String tagName, int image) {
        this.TagName = tagName;
        this.Image = image;
    }
    public int getPhoto() { return Image; }
    public String getTagName() { return TagName; }
    public void setTagName(String tagName) {  this.TagName = tagName; }
    public void setPhoto(int image) {  this.Image = image; }
}
