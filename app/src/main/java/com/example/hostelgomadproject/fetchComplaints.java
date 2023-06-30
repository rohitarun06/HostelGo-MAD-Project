package com.example.hostelgomadproject;

public class fetchComplaints {

    String Date,RoomNo,Content,ID;

    public fetchComplaints() {
    }

    public String getID() {
        return ID;
    }

    public void setDate(String date) {
        Date = date;
    }

    public fetchComplaints(String date, String roomNo, String content, String ID) {
        Date = date;
        RoomNo = roomNo;
        Content = content;
        this.ID = ID;
    }

    public void setRoomNo(String roomNo) {
        RoomNo = roomNo;
    }

    public void setContent(String content) {
        Content = content;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDate() {return Date;}
    public String getRoomNo() {
        return RoomNo;
    }

    public String getContent() {
        return Content;
    }

}
