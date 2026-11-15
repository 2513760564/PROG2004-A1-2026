package src;

/**Represents a patient using the health service.
 * Store the patient's name and mobile number.
 */
public class Patient {

    //Patient information.
    private final String name;
    private final String mobileNumber;

    //Constructs a patient with specified name and mobile number.
    public Patient(String name, String mobileNumber){

        if (name == null || name.isBlank()){
            throw new IllegalArgumentException(
                "Patient name cannot be null or empty.");
        }

        if (mobileNumber == null || mobileNumber.isBlank()){
            throw new IllegalArgumentException(
                "Mobile number cannot be null or empty.");
        }

        //Allow international prefix, spaces, brackets and hyphens.
        String cleanedNumber = mobileNumber.replaceAll("[\\s\\-()]", "");

        if (!cleanedNumber.matches("\\+?\\d+")){
            throw new IllegalArgumentException(
                "Please enter a valid mobile number.");
        }

        this.name = name.trim();
        this.mobileNumber = mobileNumber.trim();   
    }

    //Returns the patient's name.
    public String getName(){
        return name;
    }

    //Returns the patient's mobile number.
    public String getMobileNumber(){
        return mobileNumber;
    }

    //Returns a readable description of the patient.
    @Override 
    public String toString(){
        return "Patient [Name: " + name
                + ", Mobile: " + mobileNumber + "]";
    }
}   