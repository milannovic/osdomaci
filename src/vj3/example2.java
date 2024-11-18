package vj3;

class myRunnable implements Runnable{
    String name;
    myRunnable(String name){
        this.name=name;
    }
    @Override
    public void run() {
        System.out.println("My name is: "+name);
    }
}


public class example2 {
    public static void main(String[] args) {
        Thread r1=new Thread(new myRunnable("green"));
        Thread r2=new Thread(new myRunnable("orange"));
        r1.start();
        r2.start();
    }
}
