package vj6;

import java.util.Scanner;

class CPU extends Thread{

    private ProcessManager pm;
    private float quantum;
    CPU(ProcessManager pm, float quantum){
        this.pm=pm;
        this.quantum=quantum;
    }

    @Override
    public void run() {
        while (!pm.isEmpty()){
            Process p=pm.nextProcess();
            if(p.getCode()!=null && p.getCode().compareTo("ps")==0){
                System.out.println(pm);
                OS.waiting=false;
            }
            else {
                p.setRt(p.getRt()+9);
            }
            try{
                Thread.sleep((long)quantum);
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
            p.setRt(p.getRt()-quantum);
            System.out.println(pm);
            if(p.getRt()>0)
                pm.add(p);
        }
    }
}

class OS{
    CPU cpu;
    ProcessManager pm;
    private  boolean power = false;
    private String cmd;
    protected static volatile boolean waiting = false;
    OS(){
        power=true;
        pm=new ProcessManager();
        cpu=new CPU(pm,10);
        cpu.start();
        Scanner scan=new Scanner(System.in);

        while(power){
            if(!waiting){
                System.out.println("cmd>>");
                cmd=scan.next();
            }else {
                cmd="";
            }
            switch (cmd){
                case "exit":
                    power=false;
                    break;
                case "ps": //it does not work
                    pm.add(new Process(10,"ps"));
                    waiting=true;
                    break;
                default:
                    break;
            }
        }
    }
}

public class zadOS {
    public static void main(String[] args) {
        OS operativniSistem=new OS();
    }
}
