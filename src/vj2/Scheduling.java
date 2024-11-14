package vj2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

class Process{
    int id;
    float burst;
    float remaining;
    int priority=0;
    Process(int id, float burstTime){
        this.id = id;
        this.burst = burstTime;
        this.remaining = burstTime;
    }
    /*Process(int id,float burst,float remaining, int priority){
        this.id=id;
        this.burst=burst;
        this.remaining=remaining;
        this.priority=priority;
    }*/

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
public class Scheduling{
    static ArrayList<Process> listProcess = new ArrayList<Process>(Arrays.<Process>asList(
            new Process(3, 5),
            new Process(4,8),
            new Process(1, 7),
            new Process(5, 5),
            new Process(2, 3)
    ));

    public static void main(String[] args) {
        jsfScheduling();
    }
    static void jsfScheduling(){
        Queue<Process> queue=new PriorityQueue<>(
                (p1,p2)->{
                    return Float.compare(p1.burst, p2.burst);
                });
        queue.addAll(listProcess);
        /*for (Process p : listProcess)
            queue.add(p);
        */
        while(!queue.isEmpty()){
            Process current = queue.poll();
            System.out.println("Izvrsava se proces: "+current);
            /*try{
                Thread.sleep((int)current.burst*1000);
            }catch (Exception e){
                System.out.println(e);
            }*/
        }
    }

}

