package vj4;

import java.util.Random;

class Automobil extends Thread {
    private int id;
    private String name;
    private int[][] staza;
    private int red;
    private int pozicija = 0;
    private int ostatak;
    static boolean kraj = false;
    static boolean winnerAnnounced = false;

    Automobil(int id, String str, int[][] staza, int red) {
        this.id = id;
        this.name = str;
        this.staza = staza;
        this.red = red;
        this.ostatak = staza[0].length - 1;
    }

    @Override
    public String toString() {
        if (pozicija == 0)
            return "Auto " + name + " jos nije pocelo trku";
        return "Auto " + name + " je na poziciji " + pozicija;
    }

    @Override
    public void run() {
        for (pozicija = 0; pozicija < 15; pozicija++) {
            if (kraj)
                break;
            synchronized (this) {
                System.out.println(this);
                if (staza[red][pozicija] == 0) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else if (staza[red][pozicija] == 1) {
                    System.out.println(name + " je na STOP");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (pozicija == 14 && !winnerAnnounced) {
                    kraj = true;
                    winnerAnnounced = true;
                    System.out.println("Pobjednik trke je: " + this);
                } else if (pozicija == 14 && winnerAnnounced) {
                    System.out.println(name + " je zavrsio trku, ali nije pobjednik.");
                }
            }
            ostatak--;
            if (kraj==true && pozicija != 14) {
                System.out.println(name + " preostalo vrijeme do pobjede: " + (15 - pozicija - 1) * 2 + " sekundi.");
            }
        }
    }
}
public class Trka {
    public static void main(String[] args) {
        int[][] staza = new int[3][15];
        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            staza[rand.nextInt(3)][rand.nextInt(15)] = 1;
        }

        Automobil a1 = new Automobil(0, "pes", staza, 0);
        Automobil a2 = new Automobil(1, "tambo", staza, 1);
        Automobil a3 = new Automobil(2, "endi", staza, 2);
        a1.start();
        a2.start();
        a3.start();
        try {
            a1.join();
            a2.join();
            a3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
