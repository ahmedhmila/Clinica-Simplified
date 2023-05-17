import java.util.*;

public class Building {
    private String name;
    private List<Integer> chambers;

    public Building(String name, int capacity) {
        this.name = name;
        this.chambers = new ArrayList<>();
        for (int i = 1; i <= capacity; i++) {
            this.chambers.add(i);
        }
    }

    public String getName() {
        return name;
    }

    public List<Integer> getChambers() {
        return chambers;
    }

    public void addChamber(int chamberNumber) {
        chambers.add(chamberNumber);
    }

    public int getEmptyRoomsCount() {
        return chambers.size();
    }
}
