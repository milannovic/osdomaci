package vj7;

public class MemoryBlock {
    private int address;
    private int size;
    private boolean allocated;

    public MemoryBlock(int address, int size, boolean allocated){
        this.address = address;
        this.size = size;
        this.allocated = allocated;
    }

    public int getAdress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isAllocated() {
        return allocated;
    }

    public void setAllocated(boolean allocated) {
        this.allocated = allocated;
    }

    @Override
    public String toString() {
        return "Adresa: " + address + ", velicina: " + size + ", status alokacije >> " + allocated;
    }
}
