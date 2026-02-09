package dev.sandeep.BookMyShowNov25.entity;

import java.util.List;

public class City extends BaseModel{
    private String cityName;
    private List<Theatre> theatres;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<Theatre> getTheatres() {
        return theatres;
    }

    public void setTheatres(List<Theatre> theatres) {
        this.theatres = theatres;
    }
}
