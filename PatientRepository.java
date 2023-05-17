import java.util.List;
public interface PatientRepository {
    void addPatient(Patient patient);
    void evacuatePatient(String nCIN);
    Patient getPatientByNCIN(String nCIN);
    List<Patient> getAllPatients();
}
