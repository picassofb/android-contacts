package ec.e_box.contacts;

import android.content.ContentValues;

import java.io.Serializable;

public class Student implements Serializable{
    private int id;
    private final String name;
    private final String address;
    private final String phoneNumber;
    private final String email;
    private final String website;
    private final double grading;

    public Student(String name, String address, String phoneNumber, String email, String website, double grading) {

        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.website = website;
        this.grading = grading;
    }

    public Student(int id, String name, String address, String phoneNumber, String website, String email, double grading) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.email = email;
        this.grading = grading;
    }

    public String getName() {
        return name;
    }

    public double getGrading() {
        return grading;
    }

    public String getPhoneNumber(){return phoneNumber;}

    public String getWebsite(){return website;}

    public String getEmail(){return email;}



    @Override
    public String toString() {
        return id + " " + name;
    }

    public ContentValues toContentValues() {
        ContentValues data = new ContentValues();
        data.put("name", name);
        data.put("address",address);
        data.put("phoneNumber",phoneNumber);
        data.put("email",email);
        data.put("website",website);
        data.put("grading",grading);
        return data;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }
}
