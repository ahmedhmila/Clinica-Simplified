import java.sql.*;


public class ManagerFileRepository {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/java";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    public void addManager(Manager manager) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO managers (name, mdp) VALUES (?, ?)")) {

            statement.setString(1, manager.getNom());
            statement.setString(2, manager.getMdp());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Manager getManagerByName(String name) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM managers WHERE name = ?")) {

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nom = resultSet.getString("name");
                String mdp = resultSet.getString("mdp");

                return new Manager(nom, mdp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Implement other methods as needed
}
