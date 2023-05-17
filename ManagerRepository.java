public interface ManagerRepository {
    void addManager(Manager manager);
    Manager getManagerByName(String name);
}
