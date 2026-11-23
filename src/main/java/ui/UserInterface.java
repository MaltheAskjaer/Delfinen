package ui;

import controller.Controller;
import domain.Member;

import java.time.LocalDate;
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
        if(checkPin(controller.getPresidentPin())){
            while(true){
                System.out.println("Du er nu logget ind som formand!\n");
                System.out.println("""
                1. Se medlemsliste
                2. Afslut program
                """);
                int choice = readInt("Vælg: ");

                switch(choice){
                    case 1 -> { showAllMembers(); }
                    case 2 -> { return;
                    }
                    default -> System.out.println("Ugyldigt valg! Vælg igen");
                }
            }

        }
    }

    private void treasurerStart(){
        if(checkPin(controller.getTreasurerPin())){
            System.out.println("Logget ind!");
            while(true){
                System.out.println("Du er nu logget ind som formand!\n");
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
        if(checkPin(controller.getTrainerPin())){
            while(true){
                System.out.println("Du er nu logget ind som formand!\n");
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

    private boolean checkPin(int otherPin){
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
    private void showAllMembers() {
        if (controller.getMembers().isEmpty()) {
            System.out.println("Der er ingen medlemmer i klubben endnu.");
        } else {
            for (Member member : controller.getMembers()) {
                int age = member.getAgeOn(LocalDate.now());
                String status;

                if (age < 18) {
                    status = "Junior";
                } else {
                    status = "Senior";
                }

                System.out.println("Medlemsnummer: " + member.getMemberNumber());
                System.out.println("Navn: " + member.getName());
                System.out.println("Alder: " + age);
                System.out.println("Status: " + status);
                System.out.println("Medlemsform: " + member.getMembershipForm());
                System.out.println();
            }
        }
    }

}
