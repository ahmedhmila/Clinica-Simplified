import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BuildingFileRepository {
    private static final String TABLE_NAME = "buildings";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_NUMBER_OF_ROOMS = "number_of_rooms";

    private Connection connection;

    public BuildingFileRepository() {
        try {
            String DB_URL = "jdbc:mysql://localhost:3306/java";
            String DB_USERNAME = "root";
            String DB_PASSWORD = "";

            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBuilding(Building building) {
        try {
            String query = "INSERT INTO " + TABLE_NAME + " (" +
                    COLUMN_NAME + ", " +
                    COLUMN_NUMBER_OF_ROOMS +
                    ") VALUES (?, ?)";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, building.getName());
            statement.setInt(2, building.getChambers().size());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Building getBuildingByName(String name) {
        try {
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int numberOfRooms = resultSet.getInt(COLUMN_NUMBER_OF_ROOMS);
                return new Building(name, numberOfRooms);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateBuilding(Building building) {
        try {
            String query = "UPDATE " + TABLE_NAME +
                    " SET " + COLUMN_NUMBER_OF_ROOMS + " = ?" +
                    " WHERE " + COLUMN_NAME + " = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, building.getChambers().size());
            statement.setString(2, building.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Building> getAllBuildings() {
        List<Building> buildings = new ArrayList<>();

        try {
            String query = "SELECT * FROM " + TABLE_NAME;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String name = resultSet.getString(COLUMN_NAME);
                int numberOfRooms = resultSet.getInt(COLUMN_NUMBER_OF_ROOMS);
                buildings.add(new Building(name, numberOfRooms));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return buildings;
    }

    // Implement other methods as needed
    
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
