package vj7;


import java.util.ArrayList;
import java.util.Objects;

class MemoryManager{
    private int total;
    private ArrayList<MemoryBlock> memory;

    MemoryManager(int total, int sizeBlock){
        this.total=total;
        this.memory=new ArrayList<>();
        for(int i=0;i<total;i+=sizeBlock)
            memory.add(new MemoryBlock(i,sizeBlock,false));
    }
    public boolean allocate(int size){
        for(MemoryBlock mb : memory){
            if(!mb.isAllocated() && mb.getSize()>=size){
                mb.setAllocated(true);
                return true;
            }

        }
        return false;
    }
    public boolean deallocate(int address){
        for(MemoryBlock mb : memory){
            if(mb.getAdress() == address){
                if(!mb.isAllocated()){
                    System.out.println("Blok je vec slobodan.");
                }else{
                    mb.setAllocated(false);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String str="Stanje memorije: \n";
        for(MemoryBlock mb : memory){
            str+=mb+"\n";
        }
        return str;
    }
}

public class zad1 {
    public static void main(String[] args) {
        MemoryManager manager=new MemoryManager(100,20);
        boolean status;
        status=manager.allocate(15);
        System.out.println(status);
        status=manager.allocate(20);
        System.out.println(status);
        status=manager.deallocate(0);
        System.out.println(manager);
    }
}
