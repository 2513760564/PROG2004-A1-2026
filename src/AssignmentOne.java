package src;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.EnumSet;

//Demostrates the creation and use of diffrent health professional objects through inhertance and polymorphism.
public class AssignmentOne {
    public static void main(String[] args){

        System.out.println("===================================");
        System.out.println(" HEALTH SERVICE MANAGEMENT SYSTEM ");
        System.out.println("  PART 3 - HEALTH PROFESSIONALS ");
        System.out.println("===================================");

        //Create an ArrayList using the common parent type.
        ArrayList<HealthProfessional> professionals = new ArrayList<>();

        //Create three general practitioners.
        GeneralPractitioner gp1 = new GeneralPractitioner("10001", "Alex Lee", EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY),"Central Health Clinic");
        GeneralPractitioner gp2 = new GeneralPractitioner("10002", "Jane Smith", EnumSet.of(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY),"North Medical Centre");
        GeneralPractitioner gp3 = new GeneralPractitioner("10003", "Bob Johnson", EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.THURSDAY),"Southside Medical Clinic");

        //Create a specialist.
        Specialist specialist1 = new Specialist("20001","Morgan Brown",  EnumSet.of(DayOfWeek.MONDAY,DayOfWeek.THURSDAY),"Cardiology");

        //Create a nurse.
        Nurse nurse1 = new Nurse("30001","Emily Wilson", EnumSet.of(DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY),"Registered Nurse");

        //Create a dietician.
        Dietitian dietitian1 = new Dietitian("40001","Casey Taylor", EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY),"Sports Nutrition");

        //Add all professionals to the same collection.
        professionals.add(gp1);
        professionals.add(gp2); 
        professionals.add(gp3);
        professionals.add(specialist1);
        professionals.add(nurse1);
        professionals.add(dietitian1);

        //Display all professionals details.
        System.out.println("\nPART 1: PROFESSIONAL DETAILS");
        System.out.println("-----------------------------------");

        for (HealthProfessional professional : professionals){
            System.out.println(professional);
            System.out.println();
        }

        //Demonstrate runtime polymirphism.
        System.out.println("\nPART 2: PROFESSIONAL DUTIES");
        System.out.println("-----------------------------------");

        for (HealthProfessional professional : professionals){

            System.out.println(
                professional.getName()
                + " (" + professional.getClass().getSimpleName()
                + " )"
            );

            System.out.println(
                professional.performProfessionalDuty()
            );

            System.out.println();
        }

        //Display the total number of professionals.
        System.out.println("--------------------------------------");
        System.out.println(
            "Total health professionals: "
            + professionals.size()
        );

        System.out.println("Part 3 demonstration completed.");
        System.out.println("===================================");

        
    }
}
