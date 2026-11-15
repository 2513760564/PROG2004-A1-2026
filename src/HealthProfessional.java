package src;

import java.time.DayOfWeek;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;



public abstract class HealthProfessional {
    //Common attribuutes shared by all health professionals.
    private final String id;
    private final String name;
    private final Set<DayOfWeek> workingDays;

    //Constructs a health professional with specified working days.
    protected HealthProfessional(String id, String name, Set<DayOfWeek> workingDays){
        
        if (id == null || !id.trim().matches("[0-9]+")){
            throw new IllegalArgumentException(
                "Professinal ID must contain numbers only.");
        }

        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException(
                "Professinal name cannot be empty.");
        }

        if (workingDays == null || workingDays.isEmpty()){
            throw new IllegalArgumentException(
                "Working days cannot be null or empty.");
        }

        if (workingDays.contains(null)){
            throw new IllegalArgumentException(
                "Working days cannot contain null values.");
        }

        this.id = id.trim();
        this.name = name.trim();
 
        //Create a defensive copy and prevent external modification.
        this.workingDays = Collections.unmodifiableSet(
            EnumSet.copyOf(workingDays));
    }

    //Constructs a health professional with Monday to Friday as the default working days.
    protected HealthProfessional(String id, String name) {
        this(id, name,
                EnumSet.range(
                        DayOfWeek.MONDAY,
                        DayOfWeek.FRIDAY));   
    }
    //Get the professional's unique ID.
    public String getId() {
        return id;
    }

    //Get the professional's name.
    public String getName() {
        return name;
    }

    //Get the professional's working days.
    public Set<DayOfWeek> getWorkingDays() {
        return workingDays;
    }

    //Performs a profesional duty specific to each health professional type.
    public abstract String performProfessionalDuty();

    //Returns a readable description of the health professional.
    @Override 
    public String toString(){
        return getClass().getSimpleName()
                + " [ID: " + id
                + ", Name: " + name
                + ", Working Days: " + workingDays
                + "]";
    }

    //Compares health professionals using their unique IDs.
    @Override
    public final boolean equals(Object obj){

        if (this == obj){
            return true;
        }

        if (!(obj instanceof HealthProfessional)){
            return false;
        }

        HealthProfessional other = (HealthProfessional) obj;

        return id.equals(other.id);
    }

    //Generates a hash code based on the professional's ID.
    @Override 
    public final int hashCode(){
        return Objects.hash(id);
    }
}
