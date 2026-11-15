package src;

import java.time.DayOfWeek;
import java.util.Set;

//Represents a specialist health professional.
public class Specialist extends HealthProfessional{

    private final String specialtyArea;

    //Constructs a specialist with specified working days.
    public Specialist(String id, String name,
                      Set<DayOfWeek> workingDays,
                      String specialtyArea){

        super(id, name, workingDays);

        if (specialtyArea == null || specialtyArea.isBlank()){
            throw new IllegalArgumentException(
                "Specialty area cannot be null or empty.");
        }

        this.specialtyArea = specialtyArea.trim();
    }

    //Constructs a specialist using default working days.
    public Specialist(String id, String name,
                      String specialtyArea){

        super(id, name);

        if (specialtyArea == null || specialtyArea.isBlank()){
            throw new IllegalArgumentException(
                "Specialty area cannot be null or empty.");
        }

        this.specialtyArea = specialtyArea.trim();
    }

    //Gets the specialist's area of expertise.
    public String getSpecialtyArea(){
        return specialtyArea;
    }

    //Simulates a specialist consulation.
    public String conductSpecialistConsulation(){
        return "Specialist " + getName()
                + " conducted a consultation in "
                + specialtyArea + ".";
    }

    //Displays common and specialist-specific information.
    @Override
    public String toString(){
        return super.toString() + ", Specialty Area: " + specialtyArea;
    }
}
