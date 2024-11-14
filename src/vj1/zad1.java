package vj1;

public class zad1 {
    public static void main(String[] args) {
        int n =3;
        int[] bt={4, 3, 1};
        int[] ct={0, 0, 0};
        int[] tt={0, 0, 0};
        int[] wt={0, 0, 0};
        ct[0]=bt[0];
        for(int i=1;i<n;i++)
            ct[i]=ct[i-1]+bt[i];

        for(int i=0;i<n;i++)
            tt[i]=ct[i];
        for(int i=0;i<n;i++)
            wt[i]=tt[i]-bt[i];
        System.out.println("P.\tBT\tCT\tTT\tWT\n");
        for(int i=0;i<n;i++)
            System.out.println((i+1)+"\t"+bt[i]+"\t"+ct[i]+"\t"+tt[i]+"\t"+wt[i]);
    }

}
