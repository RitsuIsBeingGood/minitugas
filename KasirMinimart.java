import java.util.Scanner;

public class KasirMinimart {

    // ======================= GLOBAL ARRAY ==========================
    static String[] merkBarang = {"Shampo", "Sunlight", "Pepsodent", "Rinso", "Baygon"};

    // dataBarang[row][col]
    // col: 0=Jenis, 1=Merk, 2=Harga, 3=Jumlah, 4=PPN Total
    static String[][] dataBarang = new String[20][5];
    static int jumlahData = 0;

    static Scanner input = new Scanner(System.in);

    // ======================= FUNCTION ==========================

    // Hitung PPN 12%
    static double hitungPPN(double harga) {
        return harga * 0.12;
    }

    // ======================= PROCEDURE ==========================

    // Tambah data barang
    static void tambahData() {
        System.out.println("\n--- Tambah Data Barang ---");

        System.out.print("Masukkan Jenis Barang: ");
        String jenis = input.nextLine();

        System.out.print("Masukkan Merk Barang: ");
        String merk = input.nextLine();

        System.out.print("Masukkan Harga Barang (per item): ");
        double harga = Double.parseDouble(input.nextLine());

        System.out.print("Masukkan Jumlah Barang: ");
        int jumlah = Integer.parseInt(input.nextLine());

        double ppnTotal = hitungPPN(harga) * jumlah;

        dataBarang[jumlahData][0] = jenis;
        dataBarang[jumlahData][1] = merk;
        dataBarang[jumlahData][2] = String.valueOf(harga);
        dataBarang[jumlahData][3] = String.valueOf(jumlah);
        dataBarang[jumlahData][4] = String.valueOf(ppnTotal);

        jumlahData++;

        System.out.println("Data berhasil ditambahkan!");
    }

    // Hapus data
    static void hapusData() {
        System.out.println("\n======= DAFTAR BARANG =======");
        System.out.printf("%-5s %-12s %-12s %-10s %-10s %-10s\n",
                          "No", "Jenis", "Merk", "Harga", "Jumlah", "PPN Total");

        for (int i = 0; i < jumlahData; i++) {
            System.out.printf("%-5d %-12s %-12s %-10s %-10s %-10s\n",
                              (i + 1),
                              dataBarang[i][0],
                              dataBarang[i][1],
                              dataBarang[i][2],
                              dataBarang[i][3],
                              dataBarang[i][4]);
        }
        System.out.print("\nMasukkan nomor data yang ingin dihapus: ");
        int index = Integer.parseInt(input.nextLine());

        if (index < 1 || index > jumlahData) {
            System.out.println("Nomor data tidak valid!");
            return;
        }

        for (int i = index - 1; i < jumlahData - 1; i++) {
            dataBarang[i] = dataBarang[i + 1];
        }

        jumlahData--;
        System.out.println("Data berhasil dihapus!");
    }

    // Edit harga & jumlah berdasarkan merk
    static void editData() {
        System.out.println("\n======= DAFTAR BARANG =======");
        System.out.printf("%-5s %-12s %-12s %-10s %-10s %-10s\n",
                          "No", "Jenis", "Merk", "Harga", "Jumlah", "PPN Total");

        for (int i = 0; i < jumlahData; i++) {
            System.out.printf("%-5d %-12s %-12s %-10s %-10s %-10s\n",
                              (i + 1),
                              dataBarang[i][0],
                              dataBarang[i][1],
                              dataBarang[i][2],
                              dataBarang[i][3],
                              dataBarang[i][4]);
        }
        System.out.print("\nMasukkan merk barang yang ingin diedit: ");
        String cariMerk = input.nextLine();

        int found = -1;
        for (int i = 0; i < jumlahData; i++) {
            if (dataBarang[i][1].equalsIgnoreCase(cariMerk)) {
                found = i;
                break;
            }
        }

        if (found == -1) {
            System.out.println("Merk tidak ditemukan!");
            return;
        }

        System.out.print("Masukkan harga baru: ");
        double hargaBaru = Double.parseDouble(input.nextLine());

        System.out.print("Masukkan jumlah baru: ");
        int jumlahBaru = Integer.parseInt(input.nextLine());

        double ppnBaru = hitungPPN(hargaBaru) * jumlahBaru;

        dataBarang[found][2] = String.valueOf(hargaBaru);
        dataBarang[found][3] = String.valueOf(jumlahBaru);
        dataBarang[found][4] = String.valueOf(ppnBaru);

        System.out.println("Data berhasil diperbarui!");
    }

    // Tampilkan seluruh data
    static void tampilData() {
        System.out.println("\n======= DAFTAR BARANG =======");
        System.out.printf("%-5s %-12s %-12s %-10s %-10s %-10s\n",
                          "No", "Jenis", "Merk", "Harga", "Jumlah", "PPN Total");

        for (int i = 0; i < jumlahData; i++) {
            System.out.printf("%-5d %-12s %-12s %-10s %-10s %-10s\n",
                              (i + 1),
                              dataBarang[i][0],
                              dataBarang[i][1],
                              dataBarang[i][2],
                              dataBarang[i][3],
                              dataBarang[i][4]);
        }
    }

    // Tampilkan barang dengan harga per item tertinggi
    static void tampilHargaTertinggi() {
        if (jumlahData == 0) {
            System.out.println("\nBelum ada data.");
            return;
        }

        int maxIndex = 0;
        for (int i = 1; i < jumlahData; i++) {
            if (Double.parseDouble(dataBarang[i][2]) >
                Double.parseDouble(dataBarang[maxIndex][2])) {
                maxIndex = i;
            }
        }

        System.out.println("\n=== Barang dengan Harga Tertinggi ===");
        System.out.println("Jenis : " + dataBarang[maxIndex][0]);
        System.out.println("Merk  : " + dataBarang[maxIndex][1]);
        System.out.println("Harga (per item): " + dataBarang[maxIndex][2]);
        System.out.println("Jumlah: " + dataBarang[maxIndex][3]);
        System.out.println("PPN Total: " + dataBarang[maxIndex][4]);
    }

    // ======================= MENU UTAMA ==========================
    public static void main(String[] args) {
        int pilihan;

        do {
            System.out.println("\n=== Aplikasi Kasir Minimart ===");
            System.out.println("1. Tambah Data Barang");
            System.out.println("2. Hapus Data Barang");
            System.out.println("3. Edit Data Barang");
            System.out.println("4. Tampilkan Semua Data");
            System.out.println("5. Tampilkan Harga Tertinggi");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            
            pilihan = Integer.parseInt(input.nextLine());

            switch (pilihan) {
                case 1 -> tambahData();
                case 2 -> hapusData();
                case 3 -> editData();
                case 4 -> tampilData();
                case 5 -> tampilHargaTertinggi();
                case 0 -> System.out.println("Keluar...");
                default -> System.out.println("Pilihan tidak valid!");
            }

        } while (pilihan != 0);
    }
}
