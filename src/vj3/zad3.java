package vj3;

class zad3Runnable implements Runnable{
    int num;
    Counter c;
    static boolean[] flag=new boolean[2];
    static int turn=0;
    zad3Runnable(int num, Counter c){
        this.num=num;
        this.c=c;
    }
    @Override
    public void run() {
        for(int i=0;i<10000;i++){
            flag[num]=true;
            int otherThread=(num+1)%2;
            turn=otherThread;
            while(flag[otherThread] && turn==otherThread){
                Thread.yield();
            }
            //kriticna sekcija
            c.increment();
            flag[num]=false;
        }
    }
}

public class zad3 {
    public static void main(String[] args) {
        Counter c=new Counter();
        Thread t1=new Thread(new zad3Runnable(0,c));
        Thread t2=new Thread(new zad3Runnable(1,c));
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
