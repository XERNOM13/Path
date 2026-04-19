import java.util.Random;

public class Player {
    private String nama;
    private int hp;
    private int mana;
    private int comboPedang; // Variabel Algoritma Combo
    private Random rand = new Random();

    public void setNama(String nama) { this.nama = nama; }
    public String getNama() { return this.nama; }
    public int getHp() { return this.hp; }
    public int getMana() { return this.mana; }
    public int getCombo() { return this.comboPedang; }

    public void resetStat() {
        this.hp = 400;
        this.mana = 150;
        this.comboPedang = 0;
    }

    public void tambahStat(int hp, int mana) {
        this.hp += hp;
        this.mana += mana;
    }

    public void terimaDamage(int damage) {
        this.hp -= damage;
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    public void resetCombo() {
        this.comboPedang = 0;
    }

    // --- ALGORITMA SKILL & DAMAGE ---
    public int serangPedang() {
        this.comboPedang++; // Algoritma Counter: Combo naik 1 setiap digunakan berturut-turut
        int baseDamage = rand.nextInt(35) + 45;
        // Algoritma Linear Scaling: Damage bertambah 15 poin setiap stack combo
        return baseDamage + (this.comboPedang * 15); 
    }

    public int serangIceJavelin() {
        this.resetCombo(); // Memutus combo pedang
        if (this.mana >= 30) {
            this.mana -= 30;
            return rand.nextInt(50) + 80;
        }
        return -1; 
    }

    public int serangFireball() {
        this.resetCombo(); // Memutus combo pedang
        if (this.mana >= 60) {
            this.mana -= 60;
            return rand.nextInt(120) + 160;
        }
        return -1; 
    }
}