package kostapp.service;

import kostapp.model.Contract;
import kostapp.model.Invoice;
import kostapp.model.Payment;

public class ConsoleNotificationService implements NotificationService {
    @Override
    public void notifyInfo(String message) {
        System.out.println("[INFO] " + message);
    }

    @Override
    public void notifyContractCreated(Contract contract) {
        System.out.println("[CONTRACT CREATED] " + contract);
    }

    @Override
    public void notifyInvoiceCreated(Invoice invoice) {
        System.out.println("[INVOICE CREATED] " + invoice);
    }

    @Override
    public void notifyPaymentReceived(Payment payment) {
        System.out.println("[PAYMENT RECEIVED] " + payment);
    }
}