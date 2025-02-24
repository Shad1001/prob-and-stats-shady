public class Tester {
    public static void main(String[] args) {
        DoorsMonte dm = new DoorsMonte();
        System.out.println("No swap win percentage: " + dm.monteDoors(10000, false));
        System.out.println("Swap win percentage: " + dm.monteDoors(10000, true));
    }
}
