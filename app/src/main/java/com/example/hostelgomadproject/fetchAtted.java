package com.example.hostelgomadproject;

public class fetchAtted {
    String Name,RoomNo,USN;

    public fetchAtted(String name, String roomNo, String USN) {
        Name = name;
        RoomNo = roomNo;
        this.USN = USN;
    }

    public fetchAtted() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRoomNo() {
        return RoomNo;
    }

    public void setRoomNo(String roomNo) {
        RoomNo = roomNo;
    }

    public String getUSN() {
        return USN;
    }

    public void setUSN(String USN) {
        this.USN = USN;
    }
}
