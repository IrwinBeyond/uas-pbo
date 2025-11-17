package kostapp.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Contract {
    private String contractId;
    private Resident resident;
    private Room room;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal monthlyRate;
    private List<Invoice> invoices;

    public Contract(String contractId, Resident resident, Room room, LocalDate startDate, LocalDate endDate, BigDecimal monthlyRate) {
        this.contractId = contractId;
        this.resident = resident;
        this.room = room;
        this.status = "ACTIVE";
        this.startDate = startDate;
        if (endDate.isBefore(startDate)) throw new IllegalArgumentException("End date cannot be before start date");
        this.endDate = endDate;
        this.monthlyRate = monthlyRate;
        this.invoices = new ArrayList<Invoice>();
    }

    public void addInvoice(Invoice invoice) {
        if (invoice == null) {
            throw new IllegalArgumentException("Invoice cannot be null");
        }

        Contract existingContract = invoice.getContract();

        if (existingContract == null) {
            invoice.setContract(this);
        } else if (existingContract != this) {
            throw new IllegalArgumentException(
                    "Invoice belongs to a different contract: " + existingContract.getContractId()
            );
        }

        if (!this.invoices.contains(invoice)) {
            this.invoices.add(invoice);
        }
    }

    public boolean isActive() {
        return "ACTIVE".equalsIgnoreCase(this.status);
    }

    public void terminate() {
        this.status = "TERMINATED";

        if (this.room != null) {
            this.room.markAsVacant();
        }
    }

    public String getContractId() {
        return contractId;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public BigDecimal getMonthlyRate() {
        return monthlyRate;
    }

    public void setMonthlyRate(BigDecimal monthlyRate) {
        this.monthlyRate = monthlyRate;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "contractId='" + contractId + '\'' +
                ", resident=" + (resident != null ? resident.getName() : "null") +
                ", room=" + (room != null ? room.getRoomNumber() : "null") +
                ", status='" + status + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", monthlyRate=" + monthlyRate +
                ", invoiceCount=" + (invoices != null ? invoices.size() : 0) +
                '}';
    }
}
