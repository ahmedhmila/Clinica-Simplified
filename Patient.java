public class Patient {
    private String nom;
    private String sexe;
    private String nCIN;
    private String maladie;
    private int chamber;
    private String phoneNumber;

    // Constructor
    public Patient(String nom, String sexe, String nCIN, String maladie, int chamber, String phoneNumber) {
        this.nom = nom;
        setSexe(sexe);
        this.nCIN = nCIN;
        this.maladie = maladie;
        this.chamber = chamber;
        this.phoneNumber = phoneNumber;
    }

    // Getters and setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        if (sexe.equalsIgnoreCase("M") || sexe.equalsIgnoreCase("F")) {
            this.sexe = sexe.toUpperCase();
        } else {
            throw new IllegalArgumentException("Invalid sexe value. Must be 'M' or 'F'.");
        }
    }

    public String getNCIN() {
        return nCIN;
    }

    public void setNCIN(String nCIN) {
        this.nCIN = nCIN;
    }

    public String getMaladie() {
        return maladie;
    }

    public void setMaladie(String maladie) {
        this.maladie = maladie;
    }

    public int getChamber() {
        return chamber;
    }

    public void setChamber(int chamber) {
        this.chamber = chamber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String toString() {
        String border = "╔═══════════════════════════════════════════════════════╗";
        String header = "║                    Patient Information                ║";
        String footer = "╚═══════════════════════════════════════════════════════╝";
    
        return border + "\n" +
               header + "\n" +
               "╠═══════════════════════════════════════════════════════╣" + "\n" +
               "║ Name: " + nom    +"\n" +                            
               "║ Sex: " + sexe+   "\n" +                             
               "║ nCIN: " + nCIN +  "\n" +                            
               "║ Maladies: " + maladie + "\n" +                     
               "║ Assigned Chamber: " + chamber + "\n" +             
               "║ Phone Number: " + phoneNumber +  "\n" +             
               footer;
    }
    
    

}
