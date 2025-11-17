package kostapp.service;

import kostapp.model.Contract;
import kostapp.model.Invoice;
import kostapp.model.Payment;

public interface NotificationService {
    void notifyInfo(String message);
    void notifyContractCreated(Contract contract);
    void notifyInvoiceCreated(Invoice invoice);
    void notifyPaymentReceived(Payment payment);
}