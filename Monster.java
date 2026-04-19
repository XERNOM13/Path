import java.util.Random;

public class Monster {
    private String nama;
    private int hp;
    private int baseAtk;
    private int durasiTerbakar; // Variabel State Algoritma DoT
    private Random rand = new Random();

    public Monster(String nama, int hp, int baseAtk) {
        this.nama = nama;
        this.hp = hp;
        this.baseAtk = baseAtk;
        this.durasiTerbakar = 0; // Default tidak terbakar
    }

    public String getNama() { return this.nama; }
    public int getHp() { return this.hp; }

    public boolean isAlive() {
        return this.hp > 0;
    }

    public void terimaDamage(int damage) {
        this.hp -= damage;
    }

    // --- ALGORITMA STATE (EFEK STATUS) ---
    public void tambahEfekTerbakar(int turn) {
        this.durasiTerbakar += turn; // Menumpuk durasi terbakar
    }

    public int prosesEfekStatus() {
        // Algoritma State/Countdown: Mengecek apakah monster sedang terbakar
        if (this.durasiTerbakar > 0) {
            int damageApi = 20; // Konstan damage luka bakar
            this.hp -= damageApi;
            this.durasiTerbakar--; // Hitung mundur durasi
            return damageApi;
        }
        return 0;
    }

    // --- LOGIKA SERANGAN MONSTER ---
    public int serangBalik() {
        return rand.nextInt(this.baseAtk) + (this.baseAtk / 2);
    }
}