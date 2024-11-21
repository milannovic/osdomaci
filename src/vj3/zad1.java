package vj3;
//1: Napišite program koji kreira i pokreće dva thread-a koristeći Runnable interfejs.
//Svaki thread treba da ispisuje broj od 1 do 5 sa pauzom od 500 milisekundi između ispisa.


class classRunnable implements Runnable{
    String str;
    classRunnable(String str){
        this.str=str;
    }
    @Override
    public void run() {
    for(int i=1;i<6;i++){
        System.out.println(i);
    }
    try {
        Thread.sleep(500);
    }catch (InterruptedException e){
        System.out.println("InterruptedException.");
    }
    }
}
public class zad1 {

    public static void main(String[] args) {
        Thread t1=new Thread(new classRunnable("west"));
        Thread t2=new Thread(new classRunnable("22"));
        t1.start();
        t2.start();
        try{
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end");
    }
}
