package vj3;

class myThread extends Thread{
    String name;
    myThread(String name){
        this.name=name;
    }

    @Override
    public void run() {
        System.out.println("my thread's name is: " + name);
    }
}

public class example1 {
    public static void main(String[] args) {
        myThread t1 = new myThread("blue");
        myThread t2 = new myThread("red");
        t1.start();
        t2.start();
        System.out.println("main println");
        for (int i = 0; i < 100; i++){
            i++;
        }
        System.out.println("loop has been terminated.");
    }
}
