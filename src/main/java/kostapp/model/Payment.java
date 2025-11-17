package kostapp.model;

import java.time.LocalDate;
import java.math.BigDecimal;

public class Payment {
    private String paymentId;
    private LocalDate paymentDate;
    private BigDecimal amount;
    private String method;
    private String note;

    public Payment(String paymentId, LocalDate paymentDate, BigDecimal amount, String method, String note) {
        this.paymentId = paymentId;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.method = method;
        this.note = note;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getMethod() {
        return method;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", paymentDate=" + paymentDate +
                ", amount=" + amount +
                ", method='" + method + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
