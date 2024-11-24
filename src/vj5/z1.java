package vj5;

class Stack{
    private int[] elementi;
    private int vrhSteka;
    Stack(int kapacitet){
        elementi=new int[kapacitet];
        vrhSteka=-1;
    }
    public boolean isEmpty(){
        if(vrhSteka==-1)
            return true;
        return false;
    }
    public boolean isFull(){
        if(elementi.length-1 == vrhSteka)
            return true;
        return false;
    }
    public synchronized void push(int e){
        while (this.isFull()){
            System.out.println("Stek je pun.");
            try{
                wait();
            }catch (InterruptedException exception) {
                throw new RuntimeException(exception);
            }
        }
        System.out.println("Dodajem: "+e);
        elementi[++vrhSteka]=e;
        notify();
    }
    public synchronized int pop(){
        while (this.isEmpty()){
            System.out.println("Stek je prazan.");
            try{
                wait();
            }catch (InterruptedException exception){
                throw new RuntimeException(exception);
            }
        }
        int element=elementi[vrhSteka--];
        System.out.println("Skidamo sa steka: "+element);
        notify();
        return element;
    }

}
class FirstThread extends Thread{
    private Stack st;
    FirstThread(Stack st){
        this.st=st;
    }

    @Override
    public void run() {
        for (int i=0;i<100;i++)
            st.push(i);
    }
}
class SecondThread extends Thread{
    private Stack st;
    SecondThread(Stack st){
        this.st=st;
    }

    @Override
    public void run() {
        for (int i=0;i<100;i++) {
            int t = st.pop();
        }
    }
}

public class z1 {
    public static void main(String[] args) {
        Stack stack=new Stack(10);
        FirstThread a=new FirstThread(stack);
        SecondThread b=new SecondThread(stack);
        a.start();
        b.start();
        try{
            a.join();
            b.join();
        }catch (InterruptedException exception){
            throw new RuntimeException(exception);
        }
    }
}
