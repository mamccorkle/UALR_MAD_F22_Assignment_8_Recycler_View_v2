package com.ualr.recyclerviewassignment.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Inbox implements Parcelable
{
    private String from;
    private String email;
    private String message;
    private String date;
    private int imageIndex;     // Assign an index value for the users image in an array of image resource values
    private boolean selected;

    // Constructor:
    public Inbox() { this.selected = false; }

    protected Inbox(Parcel in) {
        from = in.readString();
        email = in.readString();
        message = in.readString();
        date = in.readString();
        imageIndex = in.readInt();
        selected = in.readByte() != 0;
    }

    public static final Creator<Inbox> CREATOR = new Creator<Inbox>() {
        @Override
        public Inbox createFromParcel(Parcel in) {
            return new Inbox(in);
        }

        @Override
        public Inbox[] newArray(int size) {
            return new Inbox[size];
        }
    };

    public String getFrom() { return from; }

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
    public void setSelected(Boolean val) {
        this.selected = val;
    }

    public void toggleSelection() {
        this.selected = !this.selected;
    }

    // Getter and setter for assigning the user image:
    public int getImageIndex() {
        return imageIndex;
    }
    public void setImageIndex(int imageIndex) { this.imageIndex = imageIndex; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(from);
        dest.writeString(email);
        dest.writeString(message);
        dest.writeString(date);
        dest.writeInt(imageIndex);
        dest.writeByte((byte) (selected ? 1 : 0));
    }
}