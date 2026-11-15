package src;

import java.time.DayOfWeek;
import java.util.Set;

//Represents a gereral practitioner(GP).

public class GeneralPractitioner extends HealthProfessional{
    private final String clinicName;

    //Constructs a GP with specified working days.
    public GeneralPractitioner(String id, String name,
                               Set<DayOfWeek> workingDays, 
                               String clinicName){

        super(id, name, workingDays);

        if (clinicName == null || clinicName.isBlank()){
            throw new IllegalArgumentException(
                "Clinic name cannot be null or empty.");
        }

        this.clinicName = clinicName.trim();
    }
    
    //Constructs a GP using default working days.
    public GeneralPractitioner(String id, String name,
                                 String clinicName){
          
          super(id, name);
          
          if (clinicName == null || clinicName.isBlank()){
              throw new IllegalArgumentException(
                  "Clinic name cannot be null or empty.");
          }
          this.clinicName = clinicName.trim();
    }

    //Gets the clinic name.
    public String getClinicName(){
        return clinicName;
    }

    //Simulates recording a medication prescription.
    public String prescribeMedication(String medicationName){

        if (medicationName == null || medicationName.isBlank()){
            throw new IllegalArgumentException(
                "Medication name cannot be null or empty.");
        }
        return "GP " + getName()
                + " recorded a prescription for " 
                + medicationName.trim() + ".";
    }

    //Describes the GP's professional duty.
    @Override
    public String performProfessionalDuty(){
        return "Provides general medical consultations at "
                + clinicName + ".";
    }

    //Displays common and GP-specific information.
    @Override
    public String toString(){
        return super.toString() + ", Clinic: " + clinicName;
    }                              
}
