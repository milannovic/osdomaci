package vj6;

public class Process {
    private static volatile int counter=0;
    private int id;
    private float bt;
    private float rt;
    private String code;
    private ProcessState state;

    Process(float bt){
        this.id=counter++;
        this.bt=bt;
        this.rt=rt;
        this.state=ProcessState.NEW;
    }

    Process(float bt, String code){
        this(bt);
        this.code=code;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Process.counter = counter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getBt() {
        return bt;
    }

    public void setBt(float bt) {
        this.bt = bt;
    }

    public float getRt() {
        return rt;
    }

    public void setRt(float rt) {
        this.rt = rt;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ProcessState getState() {
        return state;
    }

    public void setState(ProcessState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Process " + id + " preostalo: " + rt;
    }
}
