package kostapp.model;

import java.math.BigDecimal;

public interface Payable {
    BigDecimal getAmountDue();
    boolean isPaid();
}