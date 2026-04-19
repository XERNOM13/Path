import java.util.Scanner;

public class Path {

    static Scanner input = new Scanner(System.in);

    public static void jeda(int milidetik) {
        try { Thread.sleep(milidetik); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    // Fungsi Battle kini menerima Objek, bukan variabel lepas
// Fungsi Battle dengan Algoritma Terintegrasi
    public static boolean battle(Player player, Monster monster) {
        while (monster.isAlive() && player.isAlive()) {
            System.out.println("\n========================================================");
            System.out.println(String.format("%-20s HP: %-10d MP: %-10d", player.getNama(), player.getHp(), player.getMana()));
            System.out.println(String.format("%-20s HP: %-10d", monster.getNama(), monster.getHp()));
            System.out.println("========================================================");
            
            // 1. Eksekusi Algoritma Efek Status Monster (Pre-Turn)
            int lukaBakar = monster.prosesEfekStatus();
            if (lukaBakar > 0) {
                System.out.println("🔥 Sisa api neraka menggerogoti " + monster.getNama() + "! -" + lukaBakar + " HP.");
                jeda(1000);
                if (!monster.isAlive()) {
                    System.out.println(monster.getNama() + " mati hangus menjadi abu sebelum sempat menyerang!");
                    break;
                }
            }

            // Status UI tambahan jika ada combo
            String infoCombo = (player.getCombo() > 0) ? " [COMBO: x" + player.getCombo() + "]" : "";

            System.out.println("1. Tebasan Pedang Terkutuk" + infoCombo + " | 2. Ice Javelin (30 MP) | 3. Fireball (60 MP)");
            System.out.print(">> ");
            int aksi = input.nextInt();

            int dmgKeMonster = 0;
            if (aksi == 1) {
                dmgKeMonster = player.serangPedang();
                System.out.println("\nKau menghujamkan pedang yang haus darah ke daging " + monster.getNama() + "!");
                if (player.getCombo() > 1) {
                    System.out.println("Pedang itu bereaksi! Seranganmu semakin brutal!");
                }
            } else if (aksi == 2) {
                dmgKeMonster = player.serangIceJavelin();
                if (dmgKeMonster != -1) {
                    System.out.println("\nUdara membeku. Tombak es raksasa menembus jantung " + monster.getNama() + "!");
                } else {
                    System.out.println("\nMana tidak cukup! Fokusmu buyar.");
                    player.resetCombo();
                    dmgKeMonster = 0;
                }
            } else if (aksi == 3) {
                dmgKeMonster = player.serangFireball();
                if (dmgKeMonster != -1) {
                    System.out.println("\nApi neraka meledak! Tubuh " + monster.getNama() + " dilahap pijar kemerahan!");
                    monster.tambahEfekTerbakar(3); // 2. Trigger Algoritma Efek Status selama 3 Giliran
                } else {
                    System.out.println("\nMana tidak cukup! Api padam di tanganmu.");
                    player.resetCombo();
                    dmgKeMonster = 0;
                }
            } else {
                System.out.println("\nKau kebingungan dan membuang waktu!");
                player.resetCombo();
            }

            // Kurangi HP Monster
            if (dmgKeMonster > 0) monster.terimaDamage(dmgKeMonster);
            jeda(1200);

            if (!monster.isAlive()) break; 

            // Giliran Monster
            int dmgKePlayer = monster.serangBalik();
            player.terimaDamage(dmgKePlayer);
            System.out.println(monster.getNama() + " mengamuk! Serangan baliknya merobek kulitmu: -" + dmgKePlayer + " HP.");
            jeda(1200);
        }
        player.resetCombo(); // Reset combo setelah battle selesai
        return player.isAlive(); 
    }

    public static void main(String[] args) {
        boolean gameRunning = true;
        Player player = new Player(); // Menciptakan objek Player

        while (gameRunning) {
            player.resetStat(); // Reset stat saat loop berulang

            System.out.println("\n=== PATH ===");
            System.out.println("Buatlah keputusan yang tidak akan kamu sesali");
            jeda(2000);

            System.out.println("\nKelopak matamu terasa sangat berat...");
            jeda(2000);
            System.out.println("Sinar bulan menyoroti tempatmu tergeletak dan perlahan membangunkan kesadaranmu.");
            jeda(2500);
            System.out.println("Hawa dingin menusuk tulang. Kau terbangun di atas tanah lembab, dikelilingi pohon-pohon mati.");
            jeda(2500);
            System.out.print("Kau berusaha mengingat namamu... 'Namaku adalah... ");
            
            if (player.getNama() == null) {
                player.setNama(input.next());
            } else {
                input.next(); 
            }

            System.out.println("\n--------------------------------------------------------");
            System.out.println(player.getNama() + ". Ingatanmu kabur, namun instingmu menjerit untuk segera pergi.");
            jeda(2000);

            // ACT 1
            System.out.println("\n[ACT 1 : PATH AND CHOICE]");
            System.out.println("Kegelapan hutan mulai berbisik. Geraman Babi Hutan raksasa terdengar dari balik semak.");
            System.out.println("1. Jalan berlumpur dengan jejak kaki besar.\n2. Memanjat pohon besar untuk mengamati situasi.");
            System.out.println("3. Berlari lurus menembus semak berduri.\n4. Bersembunyi di balik akar pohon raksasa.");
            System.out.print(">> ");
            if (input.nextInt() != 2) {
                System.out.println("\nGelap menyergap. Seekor babi hutan berukuran gila menyerudukmu hingga hancur.");
                jeda(3000);
                System.out.println("(Pikiranmu pulih dan Kamu kembali ke titik awal...)");
                continue;
            }

            System.out.println("\nDari ketinggian, kau melihat setitik cahaya di ufuk timur. Kau merayap turun.");
            jeda(2000);

            // ACT 2
            System.out.println("\n[ACT 2 : STONE GUARD]");
            System.out.println("Tanah bergetar hebat. Bongkahan batu besar menyatu membentuk sosok raksasa.");
            System.out.println("Sang Golem bangkit dengan mata merah yang menyala, menghalangi satu-satunya jalan.");
            jeda(1500);

            // Menciptakan Objek Golem
            Monster golem = new Monster("Golem Batu", 800, 45);

            if (!battle(player, golem)) {
                System.out.println("\nTubuhmu remuk di bawah kepalan tinju batu. Kesadaranmu lenyap...");
                jeda(3000);
                continue;
            }

            // RECOVERY
            System.out.println("\nGolem itu hancur. Di reruntuhannya, kau menemukan sisa energi magis.");
            System.out.println("Luka-lukamu menutup perlahan. Kau merasakan kekuatan baru.");
            player.tambahStat(150, 100);
            jeda(2500);

            // ACT 3
            System.out.println("\n[ACT 3 : HOPE AND FIRE]");
            System.out.println("Bau belerang menyengat. Langit berubah menjadi merah darah saat kepak sayap besar terdengar.");
            System.out.println("Naga Merah legendaris turun, menutup gerbang keluar dengan tubuhnya yang berlapis api.");
            jeda(2500);
            System.out.println("Naga: 'Tidak ada yang boleh melewati gerbang ini tanpa menjawab pertanyaanku, manusia.'");
            System.out.println("'Jawab! Apa yang lebih berat dari gunung namun bisa dibawa oleh angin?'");
            System.out.println("1. Awan | 2. Rahasia | 3. Dosa | 4. Kebohongan");
            System.out.print(">> ");

            if (input.nextInt() == 3) {
                System.out.println("\nNaga itu tertawa parau, sebuah suara yang menggetarkan isi dadamu.");
                System.out.println("Naga: 'Jawaban yang bagus... Namun aku ingin melihat keberanianmu sebelum kau bebas!'");
                jeda(2000);

                // Menciptakan Objek Naga
                Monster naga = new Monster("Naga Merah", 1200, 85);

                if (battle(player, naga)) {
                    System.out.println("\n[GOOD ENDING]");
                    System.out.println("Naga itu tumbang, memuntahkan darah hitam sebelum akhirnya menjadi abu.");
                    System.out.println("Gerbang hutan terbuka lebar. Sinar mentari menyambutmu di luar sana.");
                    System.out.println("Kau telah lolos dari Hutan Iblis. Kau bebas, " + player.getNama() + ".");
                    gameRunning = false;
                } else {
                    System.out.println("\nApi abadi melahap jiwamu. Hutan ini kembali menarikmu ke dalam pelukannya.");
                    jeda(3000);
                }
            } else {
                System.out.println("\nNaga: 'Salah! Hutan ini adalah penjara abadimu!'");
                System.out.println("Ia membuka mulutnya dan menyemburkan api yang melahap segalanya...");
                jeda(3000);
            }
        }
        input.close();
    }
}