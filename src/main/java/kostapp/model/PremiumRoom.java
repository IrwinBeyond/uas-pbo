package kostapp.model;

import java.math.BigDecimal;

public class PremiumRoom extends Room {
    private boolean hasAC;
    private boolean hasPrivateBathroom;
    private boolean includesCleaningService;

    public PremiumRoom(String roomId, String roomNumber, BigDecimal monthlyPrice) {
        super(roomId, roomNumber, monthlyPrice);
        this.hasAC = false;
        this.hasPrivateBathroom = false;
        this.includesCleaningService = false;
    }

    public boolean hasAC() {
        return hasAC;
    }

    public void setHasAC(boolean hasAC) {
        this.hasAC = hasAC;
    }

    public boolean hasPrivateBathroom() {
        return hasPrivateBathroom;
    }

    public void setHasPrivateBathroom(boolean hasPrivateBathroom) {
        this.hasPrivateBathroom = hasPrivateBathroom;
    }

    public boolean includesCleaningService() {
        return includesCleaningService;
    }

    public void setIncludesCleaningService(boolean includesCleaningService) {
        this.includesCleaningService = includesCleaningService;
    }

    @Override
    public String toString() {
        return "PremiumRoom{" +
                "roomId='" + roomId + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", roomStatus='" + roomStatus + '\'' +
                ", monthlyPrice=" + monthlyPrice +
                ", hasAC=" + hasAC +
                ", hasPrivateBathroom=" + hasPrivateBathroom +
                ", includesCleaningService=" + includesCleaningService +
                '}';
    }
}