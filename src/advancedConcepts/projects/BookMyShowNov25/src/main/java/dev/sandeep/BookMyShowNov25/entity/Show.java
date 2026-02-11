package dev.sandeep.BookMyShowNov25.entity;

import dev.sandeep.BookMyShowNov25.entity.constants.Feature;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Show extends BaseModel {
    @ManyToOne
    private Movie movie; // show to movie :: M:1
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @ManyToOne
    private Auditorium auditorium;
    @OneToMany
    @JoinColumn(name = "show_id")
    private List<ShowSeat> showSeat;
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

    public List<ShowSeat> getShowSeat() {
        return showSeat;
    }

    public void setShowSeat(List<ShowSeat> showSeat) {
        this.showSeat = showSeat;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }
}
