package bookStore;

import java.time.LocalDate;

public class Author {
    private int author_id;
    private String author_name;
    private int gender;
    private LocalDate dob;
    private String contact;
    private String address;

    public Author(int author_id, String author_name, int gender, LocalDate dob, String contact, String address) {
        this.author_id = author_id;
        this.author_name = author_name;
        this.gender = gender;
        this.dob = dob;
        this.contact = contact;
        this.address = address;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
