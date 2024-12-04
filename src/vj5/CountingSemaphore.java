package vj5;

public class CountingSemaphore {
    private int signals = 0;
    private int bound;

    public CountingSemaphore(int bound){
        if(bound>=0){
            this.bound=bound;
        }else {
            throw new IllegalArgumentException("Initial bound must be a positive number.");
        }
    }
    public synchronized void acquire() throws InterruptedException{
        while (signals==bound) {
            wait();
        }
        signals++;
    }
    public synchronized void release(){
        signals--;
        notify();
    }
}
