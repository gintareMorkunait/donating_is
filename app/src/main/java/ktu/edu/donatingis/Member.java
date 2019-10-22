package ktu.edu.donatingis;

import android.widget.Adapter;

public class Member /*implements Adapter */{
    private String Name;
    private String Address;
    private String BloodType;

    public Member(String name, String address, String bloodType) {
        Name = name;
        Address = address;
        BloodType = bloodType;
    }
    public Member()
    {
        Name = "";
        Address = "";
        BloodType = "";
    }

    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public String getBloodType() {
        return BloodType;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setBloodType(String bloodType) {
        BloodType = bloodType;
    }
}
