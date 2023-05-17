import java.util.List;

public interface BuildingRepository {
    void addBuilding(Building building);
    void removeBuilding(Building building);
    Building getBuildingByName(String name);
    List<Building> getAllBuildings();
}
