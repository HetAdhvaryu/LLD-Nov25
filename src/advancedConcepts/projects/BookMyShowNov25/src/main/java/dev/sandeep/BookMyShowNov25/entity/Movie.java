package dev.sandeep.BookMyShowNov25.entity;

import dev.sandeep.BookMyShowNov25.entity.constants.Feature;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.List;

@Entity
public class Movie extends BaseModel {
    private String title;
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
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
