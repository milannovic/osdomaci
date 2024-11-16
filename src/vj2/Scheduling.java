package vj2;

import java.util.*;

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
    Process(int id,float burst, int priority){
        this.id=id;
        this.burst=burst;
        this.remaining=burst;
        this.priority=priority;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
public class Scheduling{
    static ArrayList<Process> listProcess = new ArrayList<Process>(Arrays.<Process>asList(
            new Process(3, 8),
            new Process(4,4),
            new Process(1, 7),
            new Process(5, 5),
            new Process(2, 2)
    ));
    static ArrayList<Process> listProcess2 = new ArrayList<Process>(Arrays.<Process>asList(
            new Process(1, 10,4),
            new Process(2,5,2),
            new Process(3, 4,2),
            new Process(4,2,7)
    ));

    public static void main(String[] args) {
        sjfScheduling();
        rrScheduling(4);
        priorityScheduling();
    }
    static void sjfScheduling(){
        Queue<Process> queue=new PriorityQueue<>(
                (p1,p2)-> Float.compare(p1.burst, p2.burst));
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
    static void rrScheduling(int quantum){
        Queue<Process> queue=new LinkedList<>();
        for (Process p : listProcess)
            queue.add(p);
        //int numAdd=1;
        while (!queue.isEmpty()){
            Process current=queue.poll();
            float runningTime=Math.min(quantum,current.remaining);
            System.out.println("Izvrsava se proces: "+current+" "+runningTime+" s");
            try{
                Thread.sleep((int)runningTime*500);
            }catch (Exception e){
                e.printStackTrace();
            }
            current.remaining-=runningTime;
            /*if(numAdd>0){
                numAdd--;
                listProcess = new ArrayList<Process>(Arrays.asList(
                        new Process(3, 8),
                        new Process(4, 4)
                        ));
                for(Process p: listProcess)
                    queue.add(p);
            }*/
            if(current.remaining>0)
                queue.add(current);
        }

    }
    static void priorityScheduling(){
        Queue<Process> queue=new PriorityQueue<>((p1,p2)->Float.compare(p1.priority,p2.priority));
        for(Process p: listProcess2)
            queue.add(p);
        while(!queue.isEmpty()){
            Process current=queue.poll();
            System.out.println("Izvrsava se proces: " + current);
            try{
                Thread.sleep((int)current.burst*500);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

}

