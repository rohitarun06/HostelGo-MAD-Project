package com.example.hostelgomadproject;

public class fetchViewAttendence {
    String Date,RoomNo,Status,Name;

    public fetchViewAttendence() {
    }

    public fetchViewAttendence(String date, String roomNo, String status, String name) {
        Date = date;
        RoomNo = roomNo;
        Status = status;
        Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getRoomNo() {
        return RoomNo;
    }

    public void setRoomNo(String roomNo) {
        RoomNo = roomNo;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
