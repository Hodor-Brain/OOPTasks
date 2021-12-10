package Task10;

public class Pill implements Medicine {
    private int size;
    private String capsule;

    public void cure() {
        System.out.println("Treatment arrived.");
    }

    public void sideEffect() {
        System.out.println("Pain in stomach.");
    }

    public int getSize(){
        return size;
    }
}
