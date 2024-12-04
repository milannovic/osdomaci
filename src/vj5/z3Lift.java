package vj5;

class Lift{
    private CountingSemaphore countingSemaphore=new CountingSemaphore(3);
    public void ulazak(int ime) throws InterruptedException{
        System.out.println("Osoba "+ ime + " ceka ulazak.");
        countingSemaphore.acquire();
        System.out.println("Osoba "+ ime +" je usla u lift.");
    }
    public void izlazak(int ime){
        countingSemaphore.release();
        System.out.println("Osoba "+ ime +" je izasla iz lifta.");
    }
}
class Osoba extends Thread{
    private int ime;
    private Lift lift;
    Osoba(int ime, Lift lift){
        this.ime=ime;
        this.lift=lift;
    }
    @Override
    public void run() {
        try {
            lift.ulazak(ime);
            Thread.sleep(3000);
        }catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        lift.izlazak(ime);
    }
}

public class z3Lift {
    public static void main(String[] args) {
        Lift lift=new Lift();

        for (int i=0; i<10 ;i++){
            Osoba x=new Osoba(i, lift);
            x.start();
        }
    }
}
