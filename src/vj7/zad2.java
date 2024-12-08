package vj7;


import java.util.ArrayList;
import java.util.Arrays;

class classMemoryManager{
    private int total;
    private ArrayList<MemoryBlock> memory;

    public classMemoryManager(int total){
        this.total=total;
        memory=new ArrayList<MemoryBlock>();
        memory.add(new MemoryBlock(0,total,false));
    }
    public void allocate(int size){
        for(MemoryBlock block : memory){
            if(!block.isAllocated() && block.getSize()>=size)
                block.setAllocated(true);
            if(block.getSize() > size){
                memory.add(new MemoryBlock(block.getAdress()+size, block.getSize()-size,false ));
                block.setSize(size);
                /*MemoryBlock newBlock=new MemoryBlock(block.getAdress()+size,block.getSize()-size,false);
                block.setSize(size);
                memory.add(memory.indexOf(block)+1,newBlock);*/
            }
            return;
        }
        System.out.println("Nije uspjela alokacija");
    }
    public void deallocate(int address){
        for(MemoryBlock block: memory){
            if(block.getAdress() == address){
                block.setAllocated(false);
                mergeFreeBlocks();
                return;
            }
        }
        System.out.println("Nije moguce osloboditi blok na toj adresi.");
    }
    public void mergeFreeBlocks(){
        for(int i=0;i<memory.size()-1;i++){
            MemoryBlock mb1=memory.get(i);
            MemoryBlock mb2=memory.get(i+1);
            if(!mb1.isAllocated() && !mb2.isAllocated()){
                MemoryBlock novi=new MemoryBlock(mb1.getAdress(),mb1.getSize()+ mb2.getSize(),false);
                memory.add(i,novi);
                memory.remove(i+1);
                memory.remove(i+1);
                i--;
            }
        }
    }

    @Override
    public String toString() {
        String str="Stanje memorije:\n";
        for(MemoryBlock mb : memory){
            str+=mb+"\n";
        }
        return str;
    }
}


public class zad2 {
    public static void main(String[] args) {
        classMemoryManager manager=new classMemoryManager(1024);
        manager.allocate(128);
        System.out.println(manager);
        manager.allocate(64);
        System.out.println(manager);
        manager.allocate(50);
        manager.allocate(100);
        manager.allocate(16);
        manager.allocate(512);

        manager.deallocate(192);
        manager.deallocate(342);
        manager.deallocate(500);
        System.out.println(manager);
    }
}
