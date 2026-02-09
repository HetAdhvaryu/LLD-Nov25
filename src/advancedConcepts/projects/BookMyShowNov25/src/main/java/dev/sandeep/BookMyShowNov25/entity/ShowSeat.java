package dev.sandeep.BookMyShowNov25.entity;

public class ShowSeat extends BaseModel{
    private Seat seat;
    private Show show;
    private ShowSeatStatus showSeatStatus;
    private int price;

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public ShowSeatStatus getShowSeatStatus() {
        return showSeatStatus;
    }

    public void setShowSeatStatus(ShowSeatStatus showSeatStatus) {
        this.showSeatStatus = showSeatStatus;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
