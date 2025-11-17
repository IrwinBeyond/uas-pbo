package kostapp.model;

import java.math.BigDecimal;

public class StandardRoom extends Room {
    private boolean hasFan;
    private boolean hasSharedBathroom;

    public StandardRoom(String roomId, String roomNumber, BigDecimal monthlyPrice) {
        super(roomId, roomNumber, monthlyPrice);
        this.hasFan = false;
        this.hasSharedBathroom = false;
    }

    public boolean hasFan() {
        return hasFan;
    }

    public void setHasFan(boolean hasFan) {
        this.hasFan = hasFan;
    }

    public boolean hasSharedBathroom() {
        return hasSharedBathroom;
    }

    public void setSharedBathroom(boolean hasSharedBathroom) {
        this.hasSharedBathroom = hasSharedBathroom;
    }

    @Override
    public String toString() {
        return "StandardRoom{" +
                "roomId='" + roomId + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", roomStatus='" + roomStatus + '\'' +
                ", monthlyPrice=" + monthlyPrice +
                ", hasFan=" + hasFan +
                ", hasSharedBathroom=" + hasSharedBathroom +
                '}';
    }
}
