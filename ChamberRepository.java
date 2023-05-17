import java.util.List;

public interface ChamberRepository {
    void addChamber(Building building, Chamber chamber);
    void removeChamber(Building building, int chamberNumber);
    int getEmptyRoomCount(Building building);
    List<Chamber> getAllChambers(Building building);
}
