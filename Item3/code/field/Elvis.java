package field;

public class Elvis {
    public static final Elvis INSTANCE = new Elvis();

    private Elvis(){}

    public void leaveTheBuilding() {
        System.out.println("Whoa baby, I'm outta here!");
    }

    public static void main(String[] args){
        Elvis elvis = Elvis.INSTANCE;
        elvis.leaveTheBuilding();
    }
}
