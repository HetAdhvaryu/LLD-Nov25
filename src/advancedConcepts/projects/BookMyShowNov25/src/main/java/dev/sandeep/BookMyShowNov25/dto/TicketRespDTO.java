package dev.sandeep.BookMyShowNov25.dto;

import java.time.LocalDateTime;
import java.util.List;

public class TicketRespDTO {
    private int id;
    private String movieTitle;
    private LocalDateTime showStartTime;
    private List<String> seatNumbers;
    private String auditoriumName;
    private String theatreName;

    public TicketRespDTO() {
    }

    public TicketRespDTO(int id, String movieTitle, LocalDateTime showStartTime, List<String> seatNumbers, String auditoriumName, String theatreName) {
        this.id = id;
        this.movieTitle = movieTitle;
        this.showStartTime = showStartTime;
        this.seatNumbers = seatNumbers;
        this.auditoriumName = auditoriumName;
        this.theatreName = theatreName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public LocalDateTime getShowStartTime() {
        return showStartTime;
    }

    public void setShowStartTime(LocalDateTime showStartTime) {
        this.showStartTime = showStartTime;
    }

    public List<String> getSeatNumbers() {
        return seatNumbers;
    }

    public void setSeatNumbers(List<String> seatNumbers) {
        this.seatNumbers = seatNumbers;
    }

    public String getAuditoriumName() {
        return auditoriumName;
    }

    public void setAuditoriumName(String auditoriumName) {
        this.auditoriumName = auditoriumName;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }
}
