package vj4;

class Counter{
    private int counter = 0;
    public void increment(){ counter++; }
    public int getCounter(){ return counter; }

}
class classRunnable implements Runnable{
    int num;
    Counter c;
    static boolean[] flag=new boolean[2];
    static int turn=0;
    classRunnable(int n, Counter c){
        this.num=n;
        this.c=c;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10000000; i++) {

            flag[num] = true;
            int otherThread = (num+1) % 2;

            while (flag[otherThread]){
                if(turn==otherThread){
                    flag[num] = false;
                    while (turn==otherThread){
                        System.out.println("Thread " + num +" ceka");
                        Thread.yield();
                    }
                    flag[num] = true;
                }
                System.out.println("Thread " + num +" ceka");
            }
            // kriticna sekcija
            //System.out.println("Thread " + num +" je u kriticnoj sekciji");
            c.increment();

            turn = otherThread;
            flag[num] = false;

        }
    }
}
public class Deker {
    public static void main(String[] args) {
        Counter c=new Counter();
        Thread t1=new Thread(new classRunnable(0,c));
        Thread t2=new Thread(new classRunnable(1,c));
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        System.out.println(String.format("Brojac: %d",c.getCounter()));

    }
}
