package src;

import java.time.DayOfWeek;
import java.util.Set;

//Represents a nurise working in the health service.
public class Nurse extends HealthProfessional{
    
    private final String nursingLevel;

    //Constructs a nurse with specified working days.
    public Nurse(String id,String name,
                    Set<DayOfWeek> workingDays,
                    String nursingLevel){
    
            super(id, name, workingDays);
    
            if (nursingLevel == null || nursingLevel.isBlank()){
                throw new IllegalArgumentException(
                    "Nursing level cannot be null or empty.");
            }
    
            this.nursingLevel = nursingLevel.trim();
        }

        //Constructs a nurse using default working days.
        public Nurse(String id, String name,
                        String nursingLevel){
            super(id, name);

            if (nursingLevel == null || nursingLevel.isBlank()){
                throw new IllegalArgumentException(
                    "Nursing level cannot be null or empty.");
            }

            this.nursingLevel = nursingLevel.trim();
        }

        //Gets the nursing Level.
        public String getNursingLevel(){
            return nursingLevel;
        }

        //Simulates recording a blood sample collection.
        public String takeBloodSample(String patientName){

            if (patientName == null || patientName.isBlank()){
                throw new IllegalArgumentException(
                    "Patient name cannot be null or empty.");
            }
            return "Nurse " + getName()
                    + " recorded a blood sample collection for "
                    + patientName.trim() + ".";
        }

        //Displays common and nurse-specific information.
        @Override
        public String toString(){
            return super.toString() + ", Nursing Level: " + nursingLevel;
        }
        
    

}
