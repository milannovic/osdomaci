package vj6;

import java.util.LinkedList;
import java.util.Queue;

public class ProcessManager implements Scheduler{

    private Queue<Process> pm;
    ProcessManager(){
        pm=new LinkedList<>();
    }
    public synchronized void add(Process p){
        pm.add(new Process(100));
        pm.add(new Process(200));
        pm.add(new Process(300));
        pm.add(new Process(400));
        pm.add(new Process(500));
        pm.add(new Process(600));
    }
    public synchronized boolean isEmpty(){
        return pm.isEmpty();
    }
    @Override
    public synchronized Process nextProcess() {
        return pm.poll();
    }

    @Override
    public String toString() {
        String result="";
        for(Process p:pm){
            result+=p+"\n";
        }
        return result;
    }
}
