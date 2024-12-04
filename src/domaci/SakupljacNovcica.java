package domaci;

import java.util.Date;
import java.util.Random;

class TrafficLight extends Thread {

    private String light = RED;
    public int id;
    //boje
    public final static String RED = "crveno";
    public final static String YELLOW = "zuto";
    public final static String GREEN = "zeleno";

    public TrafficLight(int id) {
        this.id = id;
        //id % 2 omogucava da semafori ne budu u istim pocetnim stanjima
        light = (id % 2 == 0) ? RED : GREEN;
    }

    public String getLight() {
        return light;
    }

    @Override
    public void run() {
        boolean toRed = id % 2 ==0;
        while (SakupljacNovcica.NUM_OF_PLAYERS > 0) {
            System.out.println("Trenutno svjetlo na semaforu " + id + " je " + light);
            if (RED.equals(light) || GREEN.equals(light)) {
                try {
                    Thread.sleep(1800);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                try {
                    Thread.sleep(700);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }

            switch (light) {
                case RED:
                    light = YELLOW;
                    toRed = false;
                    break;
                case YELLOW:
                    light = toRed ? RED : GREEN;
                    break;
                case GREEN:
                    light = YELLOW;
                    toRed = true;
                    break;
            }
            if (GREEN.equals(light)) {
                for (Player p : SakupljacNovcica.PLAYERS) {
                    synchronized (p) {
                        p.notify();
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Semafor " + id + " ima upaljeno " + light + " svjetlo.";
    }

}
class Coin {

    private int value;

    public Coin() {
        Random rand = new Random();
        value = rand.nextInt(100);

    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Novcic " + value;
    }

}

class Player extends Thread {

    private String playerName;
    private int position;
    private boolean forward;
    private int value;
    private int endPosition;
    private int numOfFields = 0;
    private long duration;

    public Player(String playerName, boolean forward) {
        this.playerName = playerName;
        if (forward) {
            position = 0;
            endPosition = SakupljacNovcica.FIELD_NUMBERS-1;
        } else {
            position = SakupljacNovcica.FIELD_NUMBERS-1;
            endPosition = 0;
        }
        this.forward = forward;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Igrac " + playerName + " je na poziciji " + position;
    }

    @Override
    public void run() {
        duration = (new Date()).getTime(); //vrijeme kada je pocelo kretanje po mapi
        while (numOfFields != SakupljacNovcica.FIELD_NUMBERS) {
            numOfFields++;
            try {
                Thread.sleep(500);
                System.out.println(this);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }


            //provjera mape
            synchronized (this) {
                if (SakupljacNovcica.MAP[position] instanceof Coin) {
                    int val = ((Coin) SakupljacNovcica.MAP[position]).getValue();
                    value += val;
                    SakupljacNovcica.MAP[position] = null;
                    System.out.println(">> Igrac " + playerName + " je pokupio novcic " + val);
                }

                if (SakupljacNovcica.MAP[position] instanceof TrafficLight) {
                    TrafficLight tl = (TrafficLight) SakupljacNovcica.MAP[position];
                    System.out.println(">> Igrac " + playerName + " je na semaforu " + tl.id
                            + ". Trenutno svjetlo je " + tl.getLight());
                    try {
                        if (!TrafficLight.GREEN.equals(tl.getLight())) {
                            wait();
                        }

                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    System.out.println("Igrac " + playerName + " nastavlja dalje.");
                }
            }
            if (forward) {
                position += 1;
            } else {
                position -= 1;
            }
        }
        System.out.println(">>>> " + playerName + " je zavrsio simulaciju sa " + value + " novcica!"
                + " Simulacija je trajala " + ((new Date()).getTime() - duration) + " ms.");
        SakupljacNovcica.NUM_OF_PLAYERS--;

    }

}

public class SakupljacNovcica {

    public final static int NUM_OF_COINS = 5;
    public final static int NUM_OF_TRAFFIC_LIGHT = 2;
    public final static int FIELD_NUMBERS = 20;
    public static Object[] MAP = new Object[FIELD_NUMBERS];
    public static int NUM_OF_PLAYERS = 2;
    public static Player[] PLAYERS = new Player[NUM_OF_PLAYERS];

    public static void printMap() {
        System.out.println("__________________MAPA_____________________");
        for (int i = 0; i < MAP.length; i++) {
            System.out.println("MAP[" + i + "] " + MAP[i]);
        }
        System.out.println("___________________________________________");
    }

    public static void main(String[] args) {


        Random rand = new Random();

        //postavljanje 5 novcica
        int i = NUM_OF_COINS;
        while (i > 0) {
            int val = rand.nextInt(FIELD_NUMBERS);
            if (MAP[val] == null) {
                MAP[val] = new Coin();
                i--;

            }
        }

        //postavljanje 2 semafora
        i = NUM_OF_TRAFFIC_LIGHT;
        while (i > 0) {
            int val = rand.nextInt(FIELD_NUMBERS);
            if (MAP[val] == null) {
                TrafficLight tl = new TrafficLight(i);
                tl.start();
                MAP[val] = tl;
                i--;

            }
        }
        printMap();
        for(i=0;i<NUM_OF_PLAYERS;i++){
            //i%2==0 omogucava da igraci idu u suprotnim smjerovima
            PLAYERS[i]=new Player("Test "+i, (i%2==0));
            PLAYERS[i].start();
        }
    }
}