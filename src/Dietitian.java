package src;

import java.time.DayOfWeek;
import java.util.Set;

//Represents a dietitian working in the health service.
public class Dietitian extends HealthProfessional{

    private final String focusArea;

    //Constructs a dietitian with specified working days.
    public Dietitian(String id, String name,
                    Set<DayOfWeek> workingDays,
                    String focusArea){

        super(id, name, workingDays);

        if (focusArea == null || focusArea.isBlank()){
            throw new IllegalArgumentException(
                "Focus area cannot be null or empty.");
        }

        this.focusArea = focusArea.trim();
    }
    
    //Constructs a dietitian using default working days.

    public Dietitian(String id, String name,
                    String focusArea){
        super(id, name);

        if (focusArea == null || focusArea.isBlank()){
            throw new IllegalArgumentException(
                "Focus area cannot be null or empty.");
        }

        this.focusArea = focusArea.trim();
    }

    //Gets the divetitian's focus area.
    public String getFocusArea(){
        return focusArea;
    }

    //Simulates a nutrition consultation.
    public String conductNutritionConsultation(
            String patientName){

        if (patientName == null || patientName.isBlank()){
            throw new IllegalArgumentException(
                    "Patient name cannot be null or empty.");
                    
        }

        return "Dietitian " + getName()
                + " is conducting a nutrition consultation for "
                + patientName.trim()
                + " in the area of " + focusArea + ".";
    }

    //Displays common and dietitian details.
    @Override
    public String toString(){
        return super.toString()
                + ", Focus Area=" + focusArea;
    }  

    //Describes the dietitian's professional duty.
    @Override
    public String performProfessionalDuty(){
        return "Provides nutrition consultations in "
                + focusArea + ".";
    }
}
 