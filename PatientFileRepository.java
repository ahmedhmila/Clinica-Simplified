
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class PatientFileRepository {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/java";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    // ...

    public void addPatient(Patient patient) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO patients (nom, sexe, nCIN, maladie, chamber, phoneNumber) VALUES (?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, patient.getNom());
            stmt.setString(2, patient.getSexe());
            stmt.setString(3, patient.getNCIN());
            stmt.setString(4, patient.getMaladie());
            stmt.setInt(5, patient.getChamber());
            stmt.setString(6, patient.getPhoneNumber());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Patient getPatientByNCIN(String nCIN) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM patients WHERE nCIN = ?")) {
            stmt.setString(1, nCIN);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nom = rs.getString("nom");
                    String sexe = rs.getString("sexe");
                    String maladie = rs.getString("maladie");
                    int chamber = rs.getInt("chamber");
                    String phoneNumber = rs.getString("phoneNumber");

                    return new Patient(nom, sexe, nCIN, maladie, chamber, phoneNumber);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM patients");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nom = rs.getString("nom");
                String sexe = rs.getString("sexe");
                String nCIN = rs.getString("nCIN");
                String maladie = rs.getString("maladie");
                int chamber = rs.getInt("chamber");
                String phoneNumber = rs.getString("phoneNumber");

                patients.add(new Patient(nom, sexe, nCIN, maladie, chamber, phoneNumber));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }

    public void updatePatient(Patient patient) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("UPDATE patients SET nom = ?, sexe = ?, maladie = ?, chamber = ?, phoneNumber = ? WHERE nCIN = ?")) {
            stmt.setString(1, patient.getNom());
            stmt.setString(2, patient.getSexe());
            stmt.setString(3, patient.getMaladie());
            stmt.setInt(4, patient.getChamber());
            stmt.setString(5, patient.getPhoneNumber());
            stmt.setString(6, patient.getNCIN());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ...
}
