package kostapp.app;

import kostapp.model.*;
import kostapp.service.ConsoleNotificationService;
import kostapp.service.NotificationService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Owner owner;
    private static Kost kost;
    private static final List<Resident> residents = new ArrayList<>();
    private static final List<Contract> contracts = new ArrayList<>();
    private static final List<Invoice> invoices = new ArrayList<>();

    private static int residentCounter = 1;
    private static int contractCounter = 1;
    private static int invoiceCounter = 1;
    private static int paymentCounter = 1;

    private static final Scanner scanner = new Scanner(System.in);
    private static final NotificationService notificationService = new ConsoleNotificationService();

    public static void main(String[] args) {
        initSampleData();

        int choice;
        do {
            printMenu();
            choice = readInt("Pilih menu: ");
            System.out.println();

            switch (choice) {
                case 1 -> showKostAndRooms();
                case 2 -> addResident();
                case 3 -> showResidents();
                case 4 -> createContract();
                case 5 -> showContracts();
                case 6 -> payInvoice();
                case 7 -> showInvoices();
                case 8 -> createAdditionalInvoice();
                case 0 -> notificationService.notifyInfo("Keluar dari aplikasi. Terima kasih.");
                default -> System.out.println("Pilihan tidak dikenal. Coba lagi.");
            }

            System.out.println();
        } while (choice != 0);
    }

    private static void initSampleData() {
        notificationService.notifyInfo("KostApp console demo started.");

        owner = new Owner(
                "Pak Budi",
                "budispeed@gmail.com",
                "08123456789",
                "OWN-001",
                "123-456-789"
        );
        notificationService.notifyInfo("Owner created: " + owner);

        kost = new Kost(
                "KOST-001",
                "Kost Manyar",
                "Jl. Manyar Kertoadi No. 10",
                "Kost nyaman josjis",
                owner
        );

        Room roomA1 = new StandardRoom(
                "ROOM-001",
                "A1",
                new BigDecimal("750000")
        );
        Room roomA2 = new PremiumRoom(
                "ROOM-002",
                "A2",
                new BigDecimal("1200000")
        );

        kost.addRoom(roomA1);
        kost.addRoom(roomA2);

        notificationService.notifyInfo("Kost created: " + kost);
        notificationService.notifyInfo("Rooms in kost:");
        for (Room r : kost.getRooms()) {
            System.out.println("  " + r);
        }

        System.out.println();
    }

    private static void printMenu() {
        System.out.println("===== MENU KOSTAPP =====");
        System.out.println("1. Lihat data kost & kamar");
        System.out.println("2. Tambah resident baru");
        System.out.println("3. Lihat semua resident");
        System.out.println("4. Buat contract baru");
        System.out.println("5. Lihat semua contract");
        System.out.println("6. Bayar invoice");
        System.out.println("7. Lihat semua invoice");
        System.out.println("8. Buat invoice baru untuk contract");
        System.out.println("0. Keluar");
        System.out.println("========================");
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Input harus angka, coba lagi: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static void showKostAndRooms() {
        System.out.println("=== DATA KOST ===");
        System.out.println(kost);
        System.out.println();
        System.out.println("=== DAFTAR KAMAR ===");
        for (Room room : kost.getRooms()) {
            System.out.println(room);
        }
    }

    private static void addResident() {
        System.out.println("=== TAMBAH RESIDENT BARU ===");
        String name = readLine("Nama        : ");
        String email = readLine("Email       : ");
        String phone = readLine("No. Telepon : ");
        String idCard = readLine("No. KTP     : ");

        String residentId = String.format("RES-%03d", residentCounter++);
        Resident resident = new Resident(name, email, phone, residentId, idCard);
        residents.add(resident);

        notificationService.notifyInfo("Resident created: " + resident);
    }

    private static void showResidents() {
        System.out.println("=== DAFTAR RESIDENT ===");
        if (residents.isEmpty()) {
            System.out.println("Belum ada resident.");
            return;
        }

        int i = 1;
        for (Resident r : residents) {
            System.out.println(i + ". " + r);
            i++;
        }
    }

    private static void createContract() {
        System.out.println("=== BUAT CONTRACT BARU ===");

        if (residents.isEmpty()) {
            System.out.println("Belum ada resident. Tambah resident terlebih dahulu.");
            return;
        }

        System.out.println("Pilih resident:");
        for (int i = 0; i < residents.size(); i++) {
            System.out.println((i + 1) + ". " + residents.get(i));
        }
        int resIndex = readInt("Nomor resident: ") - 1;
        if (resIndex < 0 || resIndex >= residents.size()) {
            System.out.println("Pilihan resident tidak valid.");
            return;
        }
        Resident resident = residents.get(resIndex);

        List<Room> vacantRooms = kost.getVacantRooms();
        if (vacantRooms.isEmpty()) {
            System.out.println("Tidak ada kamar kosong.");
            return;
        }

        System.out.println("Pilih kamar kosong:");
        for (int i = 0; i < vacantRooms.size(); i++) {
            System.out.println((i + 1) + ". " + vacantRooms.get(i));
        }
        int roomIndex = readInt("Nomor kamar: ") - 1;
        if (roomIndex < 0 || roomIndex >= vacantRooms.size()) {
            System.out.println("Pilihan kamar tidak valid.");
            return;
        }
        Room selectedRoom = vacantRooms.get(roomIndex);

        System.out.println("Masukkan tanggal mulai kontrak (YYYY-MM-DD): ");
        String startStr = readLine("> ");
        System.out.println("Masukkan tanggal akhir kontrak (YYYY-MM-DD): ");
        String endStr = readLine("> ");

        LocalDate startDate;
        LocalDate endDate;
        try {
            startDate = LocalDate.parse(startStr);
            endDate = LocalDate.parse(endStr);
        } catch (Exception e) {
            System.out.println("Format tanggal tidak valid.");
            return;
        }

        String rateStr = readLine("Masukkan harga sewa per bulan: ");
        BigDecimal rate;
        try {
            rate = new BigDecimal(rateStr);
        } catch (Exception e) {
            System.out.println("Format nominal tidak valid.");
            return;
        }

        String contractId = String.format("CTR-%03d", contractCounter++);
        Contract contract = new Contract(
                contractId,
                resident,
                selectedRoom,
                startDate,
                endDate,
                rate
        );

        contracts.add(contract);
        notificationService.notifyContractCreated(contract);

        String invoiceId = String.format("INV-%03d", invoiceCounter++);
        LocalDate dueDate = startDate.plusDays(7);
        Invoice invoice = new Invoice(
                invoiceId,
                contract,
                dueDate,
                rate
        );
        contract.addInvoice(invoice);
        invoices.add(invoice);
        notificationService.notifyInvoiceCreated(invoice);

        System.out.println("Contract dan invoice pertama berhasil dibuat.");
    }

    private static void showContracts() {
        System.out.println("=== DAFTAR CONTRACT ===");
        if (contracts.isEmpty()) {
            System.out.println("Belum ada contract.");
            return;
        }

        int i = 1;
        for (Contract c : contracts) {
            System.out.println(i + ". " + c);
            i++;
        }
    }

    private static void payInvoice() {
        System.out.println("=== BAYAR INVOICE ===");
        if (invoices.isEmpty()) {
            System.out.println("Belum ada invoice.");
            return;
        }

        List<Invoice> unpaidInvoices = new ArrayList<>();
        for (Invoice inv : invoices) {
            if (!inv.isPaid()) {
                unpaidInvoices.add(inv);
            }
        }

        if (unpaidInvoices.isEmpty()) {
            System.out.println("Semua invoice sudah lunas. Tidak ada invoice yang perlu dibayar.");
            return;
        }

        for (int i = 0; i < unpaidInvoices.size(); i++) {
            Invoice inv = unpaidInvoices.get(i);
            System.out.println((i + 1) + ". " + inv +
                    " | amountDue=" + inv.getAmountDue());
        }

        int idx = readInt("Pilih nomor invoice: ") - 1;
        if (idx < 0 || idx >= unpaidInvoices.size()) {
            System.out.println("Pilihan invoice tidak valid.");
            return;
        }

        Invoice selectedInvoice = unpaidInvoices.get(idx);

        String amountStr = readLine("Masukkan jumlah pembayaran: ");
        BigDecimal amount;
        try {
            amount = new BigDecimal(amountStr);
        } catch (Exception e) {
            System.out.println("Format nominal tidak valid.");
            return;
        }

        String method = readLine("Metode pembayaran (CASH/TRANSFER/dll): ");
        String note = readLine("Catatan (opsional): ");

        String paymentId = String.format("PAY-%03d", paymentCounter++);
        Payment payment = new Payment(
                paymentId,
                LocalDate.now(),
                amount,
                method,
                note
        );

        selectedInvoice.addPayment(payment);
        notificationService.notifyPaymentReceived(payment);

        System.out.println("Sisa tagihan: " + selectedInvoice.getAmountDue());
        System.out.println("Invoice paid? " + selectedInvoice.isPaid());
    }

    private static void showInvoices() {
        System.out.println("=== DAFTAR INVOICE ===");
        if (invoices.isEmpty()) {
            System.out.println("Belum ada invoice.");
            return;
        }

        int i = 1;
        for (Invoice inv : invoices) {
            System.out.println(i + ". " + inv +
                    " | amountDue=" + inv.getAmountDue() +
                    " | paid=" + inv.isPaid());
            i++;
        }
    }

    private static void createAdditionalInvoice() {
        System.out.println("=== BUAT INVOICE BARU UNTUK CONTRACT ===");

        if (contracts.isEmpty()) {
            System.out.println("Belum ada contract.");
            return;
        }

        List<Contract> activeContracts = new ArrayList<>();
        for (Contract c : contracts) {
            if (c.isActive()) {
                activeContracts.add(c);
            }
        }

        if (activeContracts.isEmpty()) {
            System.out.println("Tidak ada contract yang masih aktif.");
            return;
        }

        System.out.println("Pilih contract:");
        for (int i = 0; i < activeContracts.size(); i++) {
            System.out.println((i + 1) + ". " + activeContracts.get(i));
        }

        int idx = readInt("Nomor contract: ") - 1;
        if (idx < 0 || idx >= activeContracts.size()) {
            System.out.println("Pilihan contract tidak valid.");
            return;
        }

        Contract selected = activeContracts.get(idx);

        String dueStr = readLine("Masukkan due date invoice (YYYY-MM-DD): ");
        LocalDate dueDate;
        try {
            dueDate = LocalDate.parse(dueStr);
        } catch (Exception e) {
            System.out.println("Format tanggal tidak valid.");
            return;
        }

        String amountStr = readLine("Masukkan nominal (kosongkan jika sesuai monthlyRate contract): ");
        BigDecimal amount;
        if (amountStr.isBlank()) {
            amount = selected.getMonthlyRate();
        } else {
            try {
                amount = new BigDecimal(amountStr);
            } catch (Exception e) {
                System.out.println("Format nominal tidak valid.");
                return;
            }
        }

        String invoiceId = String.format("INV-%03d", invoiceCounter++);
        Invoice invoice = new Invoice(
                invoiceId,
                selected,
                dueDate,
                amount
        );

        selected.addInvoice(invoice);
        invoices.add(invoice);
        notificationService.notifyInvoiceCreated(invoice);

        System.out.println("Invoice baru berhasil dibuat.");
    }
}
