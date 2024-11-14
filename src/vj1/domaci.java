package vj1;

public class domaci {
    public static void main(String[] args) {
        int[] burst_time = {5, 9, 6};
        int[] arrival_time = {0, 3, 6};
        int dimension=burst_time.length;
        int[] process_array=new int[dimension];
        for(int i=0;i<burst_time.length;i++)
            process_array[i]=i+1;

        int[] completion_time=new int[dimension];
        int[] turnaround_time=new int[dimension];
        int[] waiting_time=new int[dimension];
        int sum_wt=0;
        int sum_tt=0;
        for(int i=0;i<dimension;i++){
            if(i==0)
                completion_time[i]=burst_time[i];
            else
                completion_time[i]=completion_time[i-1]+burst_time[i];
            }
        for(int i=0;i<dimension;i++){
            turnaround_time[i]=completion_time[i]-arrival_time[i];
            sum_tt+=turnaround_time[i];
        }
        for(int i=0;i<dimension;i++){
            waiting_time[i]=turnaround_time[i]-burst_time[i];
            sum_wt+=waiting_time[i];
        }
        double average_wt= (double) sum_wt /dimension;
        double average_tt= (double) sum_tt /dimension;
        for(int i=0;i<dimension;i++){
            System.out.println("Process: "+process_array[i]+" BT: "+burst_time[i]+" AT: "+
                    arrival_time[i]+" WT: "+waiting_time[i]+" TT: "+turnaround_time[i]+
                    " CT: "+completion_time[i]);
        }
        //System.out.println("Average waiting time: "+ String.format("%.3f", average_wt));
        System.out.println("Average waiting time: "+ average_wt);
        System.out.println("Average turn around time: "+average_tt);
    }
}
