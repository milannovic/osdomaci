package vj3;

//Kreirajte program sa dva thread-a koji imaju zajednički brojač (resurs).

class Counter{
    private int count=0;
    public void increment(){count++;}
    public int getCount(){return count;}
}
class zad2Runnable implements Runnable{
    int num;
    Counter c;
    zad2Runnable(int num, Counter c){
        this.num=num;
        this.c=c;
    }

    @Override
    public void run() {
        for(int i=0;i<100000;i++)
            c.increment();
    }
}

public class zad2 {
    public static void main(String[] args) {
        Counter c=new Counter();
        Thread t1=new Thread(new zad2Runnable(1,c));
        Thread t2=new Thread(new zad2Runnable(2,c));
        t1.start();
        t2.start();
        try{
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(c.getCount());
    }
}
