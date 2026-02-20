package dev.sandeep.BookMyShowNov25.dto;

import java.util.List;

public class TicketReqDTO {
    private int userId;
    private List<Integer> showSeatIds;

    public TicketReqDTO() {
    }

    public TicketReqDTO(int userId, List<Integer> showSeatIds) {
        this.userId = userId;
        this.showSeatIds = showSeatIds;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Integer> getShowSeatIds() {
        return showSeatIds;
    }

    public void setShowSeatIds(List<Integer> showSeatIds) {
        this.showSeatIds = showSeatIds;
    }
}
