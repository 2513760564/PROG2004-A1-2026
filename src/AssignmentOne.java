package src;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**Demostrates the health service management system.
 * 
 * Part 3 demostrates health professional objects, inheritance and polymorphism.
 * 
 * Part 6 demonstrates appointment management, including booking, filtering, sorting and cancellation.
 */
public class AssignmentOne {
    //Main method used to demonstrate the system.
    public static void main(String[] args){

        System.out.println("===================================");
        System.out.println("  HEALTH SERVICE MANAGEMENT SYSTEM  ");
        System.out.println("   HEALTH PROFESSIONALS  ");
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
                + " )");

            System.out.println(
                professional.performProfessionalDuty());

            System.out.println();
        }

        //Display the total number of professionals.
        System.out.println("--------------------------------------");
        System.out.println(
            "Total health professionals: "
            + professionals.size());

        System.out.println("Part 3 demonstration completed.");

        System.out.println("\n\n===================================");
        System.out.println(" PART 6 - APPOINTMENT MANAGEMENT  ");
        System.out.println("===================================");

        System.out.println("\nSTEP 1: CREATE HEALTH PROFESSIONALS");
        System.out.println("--------------------------------------");
        
        //Use today's working day for this demonstration.
        //This ensures the demonstration works on any day.
        DayOfWeek today = LocalDate.now().getDayOfWeek();

        EnumSet<DayOfWeek> workingToday = EnumSet.of(today);

        //Create one professional of each type.
        GeneralPractitioner appointmentGP = 
                new GeneralPractitioner(
                        "50001",
                        "Daniel Green",
                        workingToday,
                    "City Health Clinic");

        Specialist appointmentSpecialist = 
                new Specialist(
                        "50002",
                        "Olivia White",
                        workingToday,
                    "Cardiology");

        Nurse appointmentNurse = 
                new Nurse(
                        "50003",
                        "Sophia Martin",
                        workingToday,
                    "Registered Nurse");

        Dietitian appointmentDietitian = 
                new Dietitian(
                        "50004",
                        "William Clark",
                        workingToday,
                    "Sports Nutrition");

        System.out.println(appointmentGP);
        System.out.println(appointmentSpecialist);
        System.out.println(appointmentNurse);
        System.out.println(appointmentDietitian);

        //Create another nurse who does not work today.
        Nurse unavailableNurse = 
                 new Nurse(
                        "50005",
                        "Lucas Talor",
                        EnumSet.of(today.plus(1)),
            "Registered Nurse");

        System.out.println("\nSTEP 2: CREATE PATIENTS");
        System.out.println("--------------------------------------");

        Patient patient1 = new Patient("David Wilson", "0412345678");
        Patient patient2 = new Patient("Sarah Brown", "0412345679");
        Patient patient3 = new Patient("Michael Chen", "0412345680");
        Patient patient4 = new Patient("Emma Talor", "0412345681");

        System.out.println(patient1);
        System.out.println(patient2);
        System.out.println(patient3);
        System.out.println(patient4);

        System.out.println("\nSTEP 3: INITIALISE APPOINTMENT MANAGER");
        System.out.println("------------------------------------------");

        AppointmentManager manager = new AppointmentManager();

        System.out.println("Appointment manager created successfully.");

        System.out.println("Available time slots: "
            + AppointmentManager.getAvailableTimeSlots());

        System.out.println("\nSTEP 4: ADD APPOINTMENTS");
        System.out.println("------------------------------------------");

        //Create appointments in an intentionally unsorted order to demonstrate sorting later.

        Appointment appointment1 = 
               new Appointment(
                    patient1,
                    appointmentGP,
                    LocalTime.of(9,0));
        
        Appointment appointment2 = 
               new Appointment(
                    patient2,
                    appointmentSpecialist,
                    LocalTime.of(8,30));

        Appointment appointment3 = 
               new Appointment(
                    patient3,
                    appointmentNurse,
                    LocalTime.of(10,0));

        Appointment appointment4 = 
               new Appointment(
                    patient4,
                    appointmentDietitian,
                    LocalTime.of(11,0));

        Appointment appointment5 = 
               new Appointment(
                    patient4,
                    appointmentGP,
                    LocalTime.of(9,30));

        Appointment appointment6 = 
               new Appointment(
                    patient2,
                    appointmentSpecialist,
                    LocalTime.of(12,0));

        //Add all valid appointments.
        System.out.println(
            manager.addAppointment(appointment1));
        System.out.println(
            manager.addAppointment(appointment2));
        System.out.println(
            manager.addAppointment(appointment3));
        System.out.println(
            manager.addAppointment(appointment4));
        System.out.println(
            manager.addAppointment(appointment5));
        System.out.println(
            manager.addAppointment(appointment6));

        System.out.println("\nSTEP 5: TEST INVALID APPOINTMENTS");
        System.out.println("------------------------------------------");

        //Test 1: Double booking.
        //Use a diffrerent object with the same professional ID to demostrate the equals() implementation.

        GeneralPractitioner  duplicateGP =
                new GeneralPractitioner(
                        "50001",
                        "Daniel Green",
                        workingToday,
                        "City Health Clinic");

        Appointment duplicateAppointment =
                new Appointment(
                        patient2,
                        duplicateGP,
                        LocalTime.of(9, 0));
        
        System.out.println("\nTest 1: Double booking");

        System.out.println(
                manager.addAppointment(duplicateAppointment));

        // Test 2: Invalid time slot.
        Appointment invalidTimeAppointment =
                new Appointment(
                        patient3,
                        appointmentNurse,
                        LocalTime.of(9, 15));

        System.out.println("\nTest 2: Invalid time slot");

        System.out.println(
                manager.addAppointment(invalidTimeAppointment));

        // Test 3: Professional not working today.
        Appointment unavailableAppointment =
                new Appointment(
                        patient4,
                        unavailableNurse,
                        LocalTime.of(13, 0));

        System.out.println("\nTest 3: Professional not working today");

        System.out.println(
                manager.addAppointment(unavailableAppointment));

        System.out.println("\nSTEP 6: DISPLAY ALL APPOINTMENTS");

        displayAppointments(
                "ALL APPOINTMENTS",
                manager.getAllAppointments());

        System.out.println("\nSTEP 7: FILTER BY PROFESSIONAL ID");

        displayAppointments(
                "Appointments for Professional ID 50001",
                manager.getAppointmentsByProfessionalId("50001"));

         System.out.println("\nSTEP 8: FILTER BY PATIENT MOBILE NUMBER");

        displayAppointments(
                "Appointments for Patient 0412345678",
                manager.getAppointmentsByPatientMobileNumber(
                        "0412345678"));

        System.out.println("\nSTEP 9: SORT ALL APPOINTMENTS");

        displayAppointments(
                "ALL APPOINTMENTS SORTED BY TIME",
                manager.getSortedAppointments());

        System.out.println("\nSTEP 10: SORT FILTERED APPOINTMENTS");

        displayAppointments(
                "PATIENT 0412345679 APPOINTMENTS SORTED BY TIME",
                manager.getSortedAppointmentsByPatientMobileNumber(
                        "0412345679"));

        System.out.println("\nSTEP 11: CANCEL BY PROFESSIONAL ID AND TIME");
        System.out.println("-----------------------------------");

        System.out.println(
                manager.cancelAppointmentByProfessionalIdAndTime(
                        "50001",
                        LocalTime.of(9, 30)));

        System.out.println("\nSTEP 12: CANCEL BY PATIENT MOBILE NUMBER");
        System.out.println("-----------------------------------");

        // Patient 1 has two appointments.
        // Both will be cancelled using the mobile number.
         System.out.println(
                manager.cancelAppointmentsByPatientMobileNumber(
                        "0412345678"));

        System.out.println("\nSTEP 13: TEST INVALID CANCELLATIONS");
        System.out.println("-----------------------------------");

        // Try to cancel an appointment that does not exist.
        System.out.println(
                manager.cancelAppointmentByProfessionalIdAndTime(
                        "99999",
                        LocalTime.of(14, 0)));

        // Try to cancel appointments using an unknown patient number.
        System.out.println(
                manager.cancelAppointmentsByPatientMobileNumber(
                        "0412345699"));

        System.out.println("\nSTEP 14: DISPLAY UPDATED APPOINTMENTS");

        displayAppointments(
                "FINAL APPOINTMENT LIST",
                manager.getSortedAppointments());

        System.out.println("\n===================================");
        System.out.println(" PART 6 DEMONSTRATION COMPLETED ");
        System.out.println("===================================");
    }

        //Displays a collection of appointments in a readable format.
        private static void displayAppointments(
                String title,
                List<Appointment> appointments){

            System.out.println("\n" + title);
            System.out.println("-----------------------------------");     
            
            if (appointments.isEmpty()){
                System.out.println("No appointments found.");
                return;
            }

            for (Appointment appointment : appointments){
                System.out.println(appointment);
                System.out.println();
            }
            System.out.println(
                "Total appointments displayed: "
                + appointments.size());
        
    }
}
