package kostapp.model;

import java.math.BigDecimal;

public abstract class Room {
    protected String roomId;
    protected String roomNumber;
    protected BigDecimal monthlyPrice;
    protected String roomStatus;

    public Room(String roomId, String roomNumber, BigDecimal monthlyPrice) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.monthlyPrice = monthlyPrice;
        this.roomStatus = "VACANT";
    }

    public void markAsOccupied() {
        this.roomStatus = "OCCUPIED";
    }

    public void markAsVacant() {
        this.roomStatus = "VACANT";
    }

    public boolean isOccupied() {
        return "OCCUPIED".equalsIgnoreCase(this.roomStatus);
    }

    public boolean isVacant() {
        return "VACANT".equalsIgnoreCase(this.roomStatus);
    }

    public String getRoomId() {
        return roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public BigDecimal getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(BigDecimal monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId='" + roomId + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", roomStatus='" + roomStatus + '\'' +
                ", monthlyPrice=" + monthlyPrice +
                '}';
    }
}
