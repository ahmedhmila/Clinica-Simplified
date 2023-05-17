import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Manager manager;
    private static Building urgenceBuilding;
    private static Building mainBlocBuilding;

    public static void main(String[] args) {
        initializeBuildings();

        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║             Welcome to           ║");
        System.out.println("║           CLINICA EJDIDA!        ║");
        System.out.println("╚══════════════════════════════════╝");
        
        // Check if the user has an account
        System.out.println("-------------------------------");
        System.out.println("|  Do you have an account?   |");
        System.out.println("-------------------------------");
        System.out.print("Enter your choice (yes/no): ");
                String hasAccount = scanner.nextLine().trim().toLowerCase();

        if (hasAccount.equals("yes")) {
            // Prompt for manager's name and password
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

// Check manager credentials and retrieve manager data from the file
ManagerFileRepository managerRepository = new ManagerFileRepository();
Manager retrievedManager = managerRepository.getManagerByName(name);
while (retrievedManager == null || !retrievedManager.getMdp().equals(password)) {
    System.out.println("Invalid credentials. Please try again or enter 'exit' to terminate the program.");
    System.out.print("Enter your name: ");
    name = scanner.nextLine();

    if (name.equalsIgnoreCase("exit")) {
        System.out.println("Exiting the program.");
        return; // Terminate the program
    }

    System.out.print("Enter your password: ");
    password = scanner.nextLine();

    retrievedManager = managerRepository.getManagerByName(name);
}

System.out.println("Logged in successfully.");
manager = retrievedManager;

        } else {
          // Prompt for manager's name and generate codeEmp
          System.out.print("Enter your name: ");
          String name = scanner.nextLine();
          String codeEmp = generateEmployeeCode(); // Generate a random codeEmp

          // Prompt for manager's password
          System.out.print("Enter your password: ");
          String password = scanner.nextLine();

          // Add the manager to the database
          ManagerFileRepository managerRepository = new ManagerFileRepository();
          managerRepository.addManager(new Manager(name, password));

          manager = new Manager(name, password);
          System.out.println("Your codeEmp for this session is: " + codeEmp);
      }
        boolean isLoggedIn = true;

        while (isLoggedIn) {
            displayManagerMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    evacuatePatient();
                    break;
                case 3:
                    addChamber();
                    break;
                case 4:
                    checkEmptyRooms();
                    break;
                case 5:
                    viewPatientInfo();
                    break;
                case 6:
                    addMaladie();
                    break;
                case 7:
                    viewMaladiesList();
                    break;
                case 8:
                viewAllPatientsInfo();
                break;
                case 9:
                    isLoggedIn = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        System.out.println("Logged out. Restarting the program...");
        main(null); // Restart the program
    }
    private static void initializeBuildings() {
        BuildingFileRepository buildingRepository = new BuildingFileRepository();
    
        // Check if the buildings already exist in the database
        Building urgence = buildingRepository.getBuildingByName("urgence");
        Building mainBloc = buildingRepository.getBuildingByName("main bloc");
    
        if (urgence == null) {
            urgence = new Building("urgence", 15);
            buildingRepository.addBuilding(urgence);
            System.out.println("Building 'urgence' added successfully.");
        }
    
        if (mainBloc == null) {
            mainBloc = new Building("main bloc", 40);
            buildingRepository.addBuilding(mainBloc);
            System.out.println("Building 'main bloc' added successfully.");
        }
    
        buildingRepository.closeConnection();
    
        urgenceBuilding = urgence;
        mainBlocBuilding = mainBloc;
    }
    
    private static void displayManagerMenu() {
        System.out.println("╔═════════════════════════╗");
        System.out.println("║       Manager Menu      ║");
        System.out.println("╠═════════════════════════╣");
        System.out.println("║ 1. Add a patient        ║");
        System.out.println("║ 2. Evacuate a patient   ║");
        System.out.println("║ 3. Add a chamber        ║");
        System.out.println("║ 4. Check empty rooms    ║");
        System.out.println("║ 5. View a patient's info║");
        System.out.println("║ 6. Add a maladie        ║");
        System.out.println("║ 7. View maladies list   ║");
        System.out.println("║ 8. View all patients'   ║");
        System.out.println("║    info                 ║");
        System.out.println("║ 9. Logout               ║");
        System.out.println("╚═════════════════════════╝");
        System.out.print("Enter your choice: ");
    }
    
    private static void viewAllPatientsInfo() {
        // Retrieve all patients from the file repository
        PatientFileRepository patientRepository = new PatientFileRepository();
        List<Patient> patients = patientRepository.getAllPatients();
    
        if (!patients.isEmpty()) {
            System.out.println("All Patients' Information:");
            for (Patient patient : patients) {
                System.out.println(patient.toString());
                System.out.println("-----------------");
            }
        } else {
            System.out.println("No patients found.");
        }
    }
    
    private static void addPatient() {
    // Prompt for patient's attributes
    System.out.println("╔═════════════════════════╗");
    System.out.println("║    Ajout d'un patient   ║");
    System.out.println("╚═════════════════════════╝");
System.out.print("Enter patient's name: ");
String nom = scanner.nextLine();
System.out.print("Enter patient's gender (M/F): ");
String sexe = scanner.nextLine();
System.out.print("Enter patient's nCIN: ");
String nCIN = scanner.nextLine();

// Display the available buildings
System.out.println("╔════════════════════════════╗");
System.out.println("║      Available Buildings   ║");
System.out.println("╠════════════════════════════╣");
System.out.println("║ 1. Urgence                 ║");
System.out.println("║ 2. Main Bloc               ║");
System.out.println("╚════════════════════════════╝");
System.out.print("Enter the building number: ");
int buildingChoice = Integer.parseInt(scanner.nextLine());

Building building;
if (buildingChoice == 1) {
    building = urgenceBuilding;
} else if (buildingChoice == 2) {
    building = mainBlocBuilding;
} else {
    System.out.println("Invalid building choice.");
    return;
}

// Retrieve the list of chambers from the selected building
List<Integer> chambers = building.getChambers();

if (chambers.isEmpty()) {
    System.out.println("No chambers found in the selected building.");
    return;
}

// Randomly select a chamber
Random random = new Random();
int chamberIndex = random.nextInt(chambers.size());
int chamber = chambers.get(chamberIndex);

// Display the available maladies list
List<Maladie> maladies = viewMaladiesList();

System.out.println("╔════════════════════════════╗");
System.out.println("║      Available Maladies    ║");
System.out.println("╠════════════════════════════╣");
for (int i = 0; i < maladies.size(); i++) {
    Maladie maladie = maladies.get(i);
    System.out.println("║ " + (i + 1) + ". " + maladie.getName());
}
System.out.println("╚════════════════════════════╝");
System.out.print("Enter patient's maladie (choose from the above list): ");
int maladieIndex = Integer.parseInt(scanner.nextLine());

if (maladieIndex >= 1 && maladieIndex <= maladies.size()) {
    String maladie = maladies.get(maladieIndex - 1).getName();

    System.out.print("Enter patient's phone number: ");
    String phoneNumber = scanner.nextLine();

    // Create a new Patient object
    Patient patient = new Patient(nom, sexe, nCIN, maladie, chamber, phoneNumber);

    // Add the patient to the file repository
    PatientFileRepository patientRepository = new PatientFileRepository();
    patientRepository.addPatient(patient);

    System.out.println("Patient added successfully.");
} else {
    System.out.println("Invalid maladie index.");
}

    
    }
private static void viewPatientInfo() {
    // Prompt for nCIN to search for a patient
    System.out.print("Enter patient's nCIN: ");
    String nCIN = scanner.nextLine();

    // Retrieve the patient from the file repository
    PatientFileRepository patientRepository = new PatientFileRepository();
    Patient patient = patientRepository.getPatientByNCIN(nCIN);

    if (patient != null) {
        System.out.println(patient.toString());
    } else {
        System.out.println("Patient not found.");
    }
}

// Implement other methods

private static void evacuatePatient() {
    // Prompt for patient's nCIN
    System.out.print("Enter patient's nCIN: ");
    String nCIN = scanner.nextLine();

    // Retrieve the patient from the file repository
    PatientFileRepository patientRepository = new PatientFileRepository();
    Patient patient = patientRepository.getPatientByNCIN(nCIN);

    if (patient != null) {
        // Release the patient by setting the chamber to not occupied
        patient.setChamber(0);

        // Update the patient in the file repository
        patientRepository.updatePatient(patient);

        System.out.println("Patient evacuated successfully.");
    } else {
        System.out.println("Patient not found.");
    }
}

private static void addChamber() {
    // Prompt for building name
    System.out.print("Enter building name (Urgence/Main Bloc): ");
    String buildingName = scanner.nextLine();

    // Retrieve the building from the file repository
    BuildingFileRepository buildingRepository = new BuildingFileRepository();
    Building building = buildingRepository.getBuildingByName(buildingName);

    if (building != null) {
        // Prompt for chamber number
        System.out.print("Enter chamber number: ");
        int chamberNumber = Integer.parseInt(scanner.nextLine());

        // Add the chamber to the building
        building.addChamber(chamberNumber);

        // Update the building in the file repository
        buildingRepository.updateBuilding(building);

        System.out.println("Chamber added successfully.");
    } else {
        System.out.println("Building not found.");
    }
}

private static void checkEmptyRooms() {
    // Prompt for building name
    System.out.print("Enter building name (Urgence/Main Bloc): ");
    String buildingName = scanner.nextLine();

    // Retrieve the building from the file repository
    BuildingFileRepository buildingRepository = new BuildingFileRepository();
    Building building = buildingRepository.getBuildingByName(buildingName);

    if (building != null) {
        // Get the count of empty rooms in the building
        int emptyRooms = building.getEmptyRoomsCount();

        System.out.println("Empty rooms in " + buildingName + ": " + emptyRooms);
    } else {
        System.out.println("Building not found.");
    }
}

private static void addMaladie() {
    // Prompt for maladie name
    System.out.print("Enter maladie name: ");
    String maladieName = scanner.nextLine();

    // Create a new Maladie object
    Maladie maladie = new Maladie(maladieName);

    // Add the maladie to the database
    MaladieFileRepository maladieRepository = new MaladieFileRepository();
    maladieRepository.addMaladie(maladie);

    System.out.println("Maladie added successfully.");
}

private static List<Maladie> viewMaladiesList() {
    // Retrieve all maladies from the file repository
    MaladieFileRepository maladieRepository = new MaladieFileRepository();
    List<Maladie> maladies = maladieRepository.getAllMaladies();

    if (!maladies.isEmpty()) {
        System.out.println("╔════════════════════════════╗");
        System.out.println("║      Maladies List:        ║");
        System.out.println("╠════════════════════════════╣");
        for (int i = 0; i < maladies.size(); i++) {
            Maladie maladie = maladies.get(i);
            System.out.println("║ " +(i + 1) + ". " + maladie.getName());
        }System.out.println("╚════════════════════════════╝");
    } else {
        System.out.println("No maladies found.");
    }

    return maladies;
}

    
    private static String generateEmployeeCode() {
        Random random = new Random();
        int code = 100 + random.nextInt(900); // Generate a random 3-digit number
        return "EMP" + code;
    }
}    
