package src;
import java.time.DayOfWeek;
import java.util.EnumSet;

public class AssignmentOne {

    public static void main(String[] args){

        System.out.println("==============================");
        System.out.println(" PART 1 - HEALTH PROFESSIONAL ");
        System.out.println("==============================");

        //Create a professional with specified working days.
        HealthProfessinal professinal1 = 
              new DemoProfessinal1(
                "10001",
                "Alex Chen",
                EnumSet.of(
                    DayOfWeek.MONDAY,
                    DayOfWeek.WEDNESDAY,
                    DayOfWeek.FRIDAY
                )
              ); 
                  


        
    }

}
