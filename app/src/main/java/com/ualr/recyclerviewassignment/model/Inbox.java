package com.ualr.recyclerviewassignment.model;

public class Inbox {
    private String from;
    private String email;
    private String message;
    private String date;
    private int imageIndex;     // Assign an index value for the users image in an array of image resource values
    private boolean selected;

    public Inbox() {
        this.selected = false;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void toggleSelection() {
        this.selected = !this.selected;
    }

    // Getter and setter for assigning the user image:
    public int getImageIndex() {
        return imageIndex;
    }
    public void setImageIndex(int imageIndex) {
        this.imageIndex = imageIndex;
    }
}