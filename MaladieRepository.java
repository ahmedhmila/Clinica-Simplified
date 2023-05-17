import java.util.List;

public interface MaladieRepository {
    void addMaladie(Maladie maladie);
    List<Maladie> getAllMaladies();
}
