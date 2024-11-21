package vj4;

class CounterSynchro{
    private int counter=0;
    public synchronized void increment(){counter++;}
    public int getCounter(){return counter;}

}
class z2Runnable implements Runnable{
    int num;
    CounterSynchro c;
    z2Runnable(int num, CounterSynchro c){
        this.num=num;
        this.c=c;
    }
    @Override
    public void run() {
        for(int i=0;i<10000;i++){
            //kriticna sekcija
            //System.out.println(num);
            c.increment();
        }
    }
}

public class z2 {
    public static void main(String[] args) {
        CounterSynchro cs=new CounterSynchro();
        Thread a1=new Thread(new z2Runnable(0,cs));
        Thread a2=new Thread(new z2Runnable(1,cs));
        a1.start();
        a2.start();
        try {
            a1.join();
            a2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(String.format("Brojac: %d", cs.getCounter()));
    }
}
