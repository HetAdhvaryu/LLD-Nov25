package dev.sandeep.BookMyShowNov25.entity;

import java.util.List;

public class Theatre extends BaseModel {
    private String theatreName;
    private String address;
    private List<Auditorium> auditoriums;

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Auditorium> getAuditoriums() {
        return auditoriums;
    }

    public void setAuditoriums(List<Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }
}
