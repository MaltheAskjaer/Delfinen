package ui;

import controller.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    Controller controller = new Controller();

    public void start(){
        while(true){
            System.out.println("Velkommen til Delfinen!\n");
            System.out.println("Vælg bruger: ");
            System.out.println("""
                1. Formand
                2. Kasserer
                3. Træner
                4. Afslut program
                """);
            int choice = readInt("Vælg: ");

            switch(choice){
                case 1 -> presidentStart();
                case 2 -> treasurerStart();
                case 3 -> trainerStart();
                case 4 -> {
                    return;
                }
                default -> System.out.println("Ugyldigt valg! Vælg igen");
            }
        }

    }

    private void presidentStart(){
        if(correctPin(controller.getPresidentPin())){
            System.out.println("Du er nu logget ind som formand!\n");
            while(true){
                System.out.println("""
                1. Opret medlem
                2. Afslut program
                """);
                int choice = readInt("Vælg: ");

                switch(choice){
                    case 1 -> addMember();
                    case 2 -> {
                        return;
                    }
                    default -> System.out.println("Ugyldigt valg! Vælg igen");
                }
            }
        }
    }

    private void treasurerStart(){
        if(correctPin(controller.getTreasurerPin())){
            System.out.println("Logget ind!");
            System.out.println("Du er nu logget ind som kasserer!\n");
            while(true){
                System.out.println("""
                1. Afslut program
                """);
                int choice = readInt("Vælg: ");

                switch(choice){
                    case 1 -> {
                        return;
                    }
                    default -> System.out.println("Ugyldigt valg! Vælg igen");
                }
            }
        }
    }

    private void trainerStart(){
        if(correctPin(controller.getTrainerPin())){
            System.out.println("Du er nu logget ind som træner!\n");
            while(true){
                System.out.println("""
                1. Afslut program
                """);
                int choice = readInt("Vælg: ");

                switch(choice){
                    case 1 -> {
                        return;
                    }
                    default -> System.out.println("Ugyldigt valg! Vælg igen");
                }
            }
        }

    }

    private boolean correctPin(int otherPin){
        do{
            int pin = readInt("Pinkode (Skriv 0 for at fortryde): ");
            if(pin == 0){
                break;
            }
            if(pin == otherPin){
                return true;
            }
            System.out.println("Forkert kode! Prøv igen\n");
        }while(true);

        return false;
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if(input.isBlank()){
                return 0;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\"" + input + "\" er ikke et helt tal. Prøv igen.");
            }
        }
    }

    private LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return LocalDate.now();
            }
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("\"" + input + "\" er ikke en dato på formen dd-mm-åååå. Prøv igen.");
            }
        }
    }

    private String readName(String prompt) {
        while (true) {
            System.out.print(prompt);
            String name = scanner.nextLine().trim();

            if (name.matches("[\\p{L}][\\p{L} '\\-]*")) {
                return name;
            }

            System.out.println("Navnet må kun indeholde bogstaver.");
        }
    }

    private void addMember(){
        boolean competitive = false;

        System.out.println();
        String firstName = readName("Fornavn: ");
        firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);

        String lastName = readName("Efternavn: ");
        lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);

        LocalDate birthday = readDate("fødselsdag [dd-mm-åååå]: ");

        System.out.println("""
                
                1. Aktivt medlemskab
                2. Passivt medlemskab
                """);
        boolean active = switch(readInt("Vælg: ")){
            case 1 -> true;
            case 2 -> false;
            default -> false;
        };

        if(active){
            System.out.println("""
                
                1. Konkurrencesvømmer
                2. Motionist
                """);
            competitive = switch(readInt("Vælg: ")){
                case 1 -> true;
                case 2 -> false;
                default -> false;
            };
        }

        if(competitive){
            try {
                controller.addCompetitiveSwimmer(firstName, lastName, birthday, active);
            } catch(IllegalArgumentException e){
                System.out.println("Medlemmet blev ikke oprettet. " + e.getMessage());
            }
            return;
        }

        try{
            controller.addMember(firstName, lastName, birthday, active);
        } catch(IllegalArgumentException e){
            System.out.println("Medlemmet blev ikke oprettet. " + e.getMessage());
        }
    }

}
