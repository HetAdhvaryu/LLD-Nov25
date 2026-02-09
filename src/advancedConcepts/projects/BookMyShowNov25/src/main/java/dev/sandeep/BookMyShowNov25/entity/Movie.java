package dev.sandeep.BookMyShowNov25.entity;

import java.util.List;

public class Movie extends BaseModel {
    private String title;
    private List<Feature> features;

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
