package kostapp.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Invoice implements Payable {
    private String invoiceNumber;
    private Contract contract;
    private LocalDate dueDate;
    private BigDecimal amount;
    private String status;
    private List<Payment> payments;

    public Invoice (String invoiceNumber, Contract contract, LocalDate dueDate, BigDecimal amount) {
        this.invoiceNumber = invoiceNumber;
        this.contract = contract;
        this.dueDate = dueDate;
        this.amount = amount;
        this.status = "UNPAID";
        this.payments = new ArrayList<>();
    }

    @Override
    public BigDecimal getAmountDue() {
        BigDecimal totalPaid = BigDecimal.ZERO;

        for (Payment p : payments) {
            totalPaid = totalPaid.add(p.getAmount());
        }

        return amount.subtract(totalPaid);
    }

    public void addPayment(Payment payment) {
        if (payment == null) {
            throw new IllegalArgumentException("Payment cannot be null");
        }

        this.payments.add(payment);

        if(isPaid()) {
            this.status ="PAID";
            contract.getRoom().markAsOccupied();
        }
    }

    @Override
    public boolean isPaid() {
        return getAmountDue().compareTo(BigDecimal.ZERO) <= 0;
    }

    public void markAsPaid() {
        this.status = "PAID";
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceNumber='" + invoiceNumber + '\'' +
                ", contractId='" + (contract != null ? contract.getContractId() : "null") + '\'' +
                ", dueDate=" + dueDate +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", amountDue=" + getAmountDue() +
                '}';
    }
}
