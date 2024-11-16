package vj2;


import java.util.*;

public class Domaci {
    static ArrayList<Process> listaProcesa = new ArrayList<Process>(Arrays.<Process>asList(
            new Process(1, 1,5),
            new Process(2,4,2),
            new Process(3, 10,1),
            new Process(4, 7,4),
            new Process(5, 4,3)
    ));
    public static void main(String[] args) {
        roundPriorityRobinScheduling(4);
    }
    static void roundPriorityRobinScheduling(int quantum){

        LinkedList<Process> lista = new LinkedList<>(listaProcesa);
        lista.sort(Comparator.comparingInt(p -> p.priority));
        Queue<Process> queue = new LinkedList<>(lista);

        while (!queue.isEmpty()){
            Process current=queue.poll();
            float runningTime=Math.min(quantum,current.remaining);
            if(runningTime>0)
                System.out.println("Izvrsava se proces: "+current+" "+runningTime+" s");
            try{
                Thread.sleep((int)runningTime*500);
            }catch (Exception e){
                e.printStackTrace();
            }
            current.remaining-=runningTime;

            if(current.remaining>0) {
                queue.add(current);
            }
            else {
                System.out.println("Proces " + current.id + " je zavrsen.");
            }
        }

    }
}
