package dev.sandeep.BookMyShowNov25.entity;

import dev.sandeep.BookMyShowNov25.entity.constants.SeatType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Seat extends BaseModel {
    private String seatNo;
    private int seatRow;
    private int seatColumn;
    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int row) {
        this.seatRow = row;
    }

    public int getSeatColumn() {
        return seatColumn;
    }

    public void setSeatColumn(int column) {
        this.seatColumn = column;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }
}
