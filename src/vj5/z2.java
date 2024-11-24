package vj5;

class Counter{
    private int counter=0;
    public void increment(){counter++;}
    public int getCounter(){return counter;}
}

class runRunnable implements Runnable{

    int num;
    Counter c;
    static BinarySemaphore sem=new BinarySemaphore();
    runRunnable(int num, Counter c){
        this.num=num;
        this.c=c;
    }

    @Override
    public void run() {
        for(int i=0;i<100000;i++){
            try{
                sem.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            c.increment();
            sem.release();
        }
    }
}

public class z2 {
    public static void main(String[] args) {
        Counter c=new Counter();
        Thread x=new Thread(new runRunnable(0,c));
        Thread y=new Thread(new runRunnable(1,c));
        x.start();
        y.start();
        try{
            x.join();
            y.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(String.format("Brojac: %d",c.getCounter()));
    }
}
