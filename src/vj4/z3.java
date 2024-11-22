package vj4;

class SharedResourse{
    private int value;
    private boolean isSet=false;

    public synchronized void setValue(int value){
        this.value=value;
        isSet=true;
        notify();
    }
    public synchronized int getValue(){
        if(!isSet){
            try {
                wait();
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
        return value;
    }
}

class ThreadA extends Thread{
    public SharedResourse sharedResourse;
    ThreadA(SharedResourse sr){
        this.sharedResourse=sr;
    }

    @Override
    public void run() {
        System.out.println("Cekamo da se postavi vrijednost");
        int t=sharedResourse.getValue();
        System.out.println(t);
    }
}

class ThreadB extends Thread{
    public SharedResourse sharedResourse;
    ThreadB(SharedResourse sr){
        this.sharedResourse=sr;
    }

    @Override
    public void run() {
        System.out.println("Postavljamo vrijednost");
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            System.out.println(e);
            sharedResourse.setValue(10);
            System.out.println("Vrijednost je postavljena");
        }
    }
}
public class z3 {
    public static void main(String[] args) {
        SharedResourse sr=new SharedResourse();
        ThreadA a=new ThreadA(sr);
        ThreadB b=new ThreadB(sr);
        a.start();
        b.start();
        try{
            a.join();
            b.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
