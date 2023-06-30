package com.example.hostelgomadproject;

public class fetchNotice {

    String Date,Title,Content,ID;

    public fetchNotice() {
    }

    public void setDate(String date) {
        Date = date;
    }

    public fetchNotice(String date, String title, String content, String ID) {
        Date = date;
        Title = title;
        Content = content;
        this.ID = ID;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDate() {return Date;}
    public String getTitle() {
        return Title;
    }

    public String getContent() {
        return Content;
    }

}
