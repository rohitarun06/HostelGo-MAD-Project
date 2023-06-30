package com.example.hostelgomadproject;

public class fetchFoodMenu {
    String Breakfast,Lunch,Dinner,Weekday;

    public String getWeekday() {
        return Weekday;
    }

    public fetchFoodMenu(String breakfast, String lunch, String dinner, String weekday) {
        Breakfast = breakfast;
        Lunch = lunch;
        Dinner = dinner;
        Weekday = weekday;
    }

    public void setDay(String day) {
        Weekday = day;
    }

    public fetchFoodMenu() {
    }

    public String getBreakfast() {
        return Breakfast;
    }

    public String getLunch() {
        return Lunch;
    }


    public void setBreakfast(String breakfast) {
        Breakfast = breakfast;
    }

    public void setLunch(String lunch) {
        Lunch = lunch;
    }

    public void setDinner(String dinner) {
        Dinner = dinner;
    }

    public String getDinner() {
        return Dinner;
    }
}
