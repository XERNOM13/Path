import java.util.Random;
import java.util.Scanner;

public class Path {

    static Scanner input = new Scanner(System.in);
    static Random rand = new Random();

    static String nama;
    static int playerHP;
    static int playerMana;

    public static void jeda(int milidetik) {
        try { Thread.sleep(milidetik); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    public static boolean battle(String monsterNama, int monsterHP, int monsterAtk) {
        while (monsterHP > 0 && playerHP > 0) {
            System.out.println("\n========================================================");
            System.out.println(String.format("%-20s HP: %-10d MP: %-10d", nama, playerHP, playerMana));
            System.out.println(String.format("%-20s HP: %-10d", monsterNama, monsterHP));
            System.out.println("========================================================");
            System.out.println("1. Tebasan Pedang  | 2. Ice Javelin (30 MP) | 3. Fireball (60 MP)");
            System.out.print(">> ");
            int aksi = input.nextInt();

            int dmgKeMonster = 0;
            if (aksi == 1) {
                dmgKeMonster = rand.nextInt(35) + 45;
                System.out.println("\nKau menghujamkan baja dingin ke tubuh " + monsterNama + "!");
            } else if (aksi == 2 && playerMana >= 30) {
                dmgKeMonster = rand.nextInt(50) + 80;
                playerMana -= 30;
                System.out.println("\nUdara membeku. Tombak es raksasa menembus jantung " + monsterNama + "!");
            } else if (aksi == 3 && playerMana >= 60) {
                dmgKeMonster = rand.nextInt(120) + 160;
                playerMana -= 60;
                System.out.println("\nBola api muncul ! tubuh " + monsterNama + " dilahap pijar kemerahan!");
            } else {
                System.out.println("\nKau kehilangan fokus! Seranganmu meleset sia-sia.");
            }

            monsterHP -= dmgKeMonster;
            jeda(1200);

            if (monsterHP <= 0) break;

            int dmgKePlayer = rand.nextInt(monsterAtk) + (monsterAtk / 2);
            playerHP -= dmgKePlayer;
            System.out.println(monsterNama + " mengamuk! Serangan baliknya merobek kulitmu: -" + dmgKePlayer + " HP.");
            jeda(1200);
        }
        return playerHP > 0;
    }

    public static void main(String[] args) {
        boolean gameRunning = true;

        while (gameRunning) {
            playerHP = 400;
            playerMana = 150;

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
            if (nama == null) nama = input.next(); else input.next(); 

            System.out.println("\n--------------------------------------------------------");
            System.out.println(nama + ". Ingatanmu kabur, namun instingmu menjerit untuk segera pergi.");
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

            if (!battle("Golem Batu", 800, 45)) {
                System.out.println("\nTubuhmu remuk di bawah kepalan tinju batu. Kesadaranmu lenyap...");
                jeda(3000);
                continue;
            }

            // RECOVERY
            System.out.println("\nGolem itu hancur. Di reruntuhannya, kau menemukan sisa energi magis.");
            System.out.println("Luka-lukamu menutup perlahan. Kau merasakan kekuatan baru.");
            playerHP += 150; playerMana += 100;
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
                if (battle("Naga Merah", 1200, 85)) {
                    System.out.println("\n[GOOD ENDING]");
                    System.out.println("Naga itu tumbang, memuntahkan darah hitam sebelum akhirnya menjadi abu.");
                    System.out.println("Gerbang hutan terbuka lebar. Sinar mentari menyambutmu di luar sana.");
                    System.out.println("Kau telah lolos dari Hutan Iblis. Kau bebas, " + nama + ".");
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