package sk.upjs.ics.bakalarka.entity;

import java.util.Date;

public class Patient {

    private String name;
    private String surname;
    private Long id;
    private Date dob;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Patient{" + "name=" + name + ", surname=" + surname + ", id=" + id + ", dob=" + dob + '}';
    }

}
