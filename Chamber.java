public class Chamber {
    private int number;
    private boolean occupied;

    public Chamber(int number) {
        this.number = number;
        this.occupied = false;
    }

    public int getNumber() {
        return number;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}