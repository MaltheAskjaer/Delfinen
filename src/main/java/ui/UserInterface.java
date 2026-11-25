package ui;

import controller.Controller;
import domain.Member;
import domain.Stroke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    Controller controller = new Controller();

    public void start() {
        while (true) {
            System.out.println("Velkommen til Delfinen!\n");
            System.out.println("Vælg bruger: ");
            System.out.println("""
                    1. Formand
                    2. Kasserer
                    3. Træner
                    4. Afslut program
                    """);
            int choice = readInt("Vælg: ");

            switch (choice) {
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

    private void presidentStart() {
        if (correctPin(controller.getPresidentPin())) {
            System.out.println("Du er nu logget ind som formand!\n");
            while (true) {
                System.out.println();
                System.out.println("""
                        1. Opret medlem
                        2. Se medlemsliste
                        3. Gå til menu
                        """);
                int choice = readInt("Vælg: ");

                switch (choice) {
                    case 1 -> addMember();
                    case 2 -> showAllMembers();
                    case 3 -> {
                        return;
                    }
                    default -> System.out.println("Ugyldigt valg! Vælg igen");
                }
            }
        }
    }

    private void treasurerStart() {
        if (correctPin(controller.getTreasurerPin())) {
            System.out.println("Logget ind!");
            System.out.println("Du er nu logget ind som kasserer!");
            while (true) {
                System.out.println("""
                        
                        1. Find årskontingent
                        2. Vis alle kontingenter
                        3. Registrer betaling
                        4. Start nyt betalingsår
                        5. Gå til menu
                        """);
                int choice = readInt("Vælg: ");

                switch (choice) {
                    case 1 -> showMembershipFee();
                    case 2 -> showAllMembershipFees();
                    case 3 -> recordPayment();
                    case 4 -> startNewPaymentYear();
                    case 5 -> {
                        return;
                    }
                    default -> System.out.println("Ugyldigt valg! Vælg igen");
                }
            }
        }
    }

    public void showMembershipFee() {
            Member member = searchMember();
            if(member != null) {
                System.out.println("\nÅrskontingent: ");

                System.out.println(member.getName() + " - " + controller.getMembershipFeeAmount(member) + " kr.");
                if(member.hasPaid()){
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    System.out.println("Betalt d. " + member.getPaidDate().format(formatter));
                } else {
                    System.out.println("Ikke betalt");
                }
            } else{
                System.out.println("\nmedlem blev ikke fundet");
            }

    }

    public void showAllMembershipFees(){
        if(controller.getMembers() == null || controller.getMembers().isEmpty()){
            System.out.println("\nMedlemslisten er tom");
            return;
        }
        LocalDate date = LocalDate.now();
        System.out.println("\nÅrskontingenter: ");
        for(Member member : controller.getMembers()){
            System.out.println(member.getName() + " - " + member.getMembershipFeeAmount() + " kr.");
        }
        System.out.println();
    }

    public void recordPayment(){
        Member member = searchMember();

        if(member != null){
            if(member.hasPaid()){
                System.out.println("Betaling er allerede registreret for dette medlem.");
            }else{
                controller.recordPayment(member);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                System.out.println("Betaling registreret d. " + LocalDate.now().format(formatter));
            }
        } else{
            System.out.println("Medlem blev ikke fundet");
        }

    }

    public void startNewPaymentYear(){
        System.out.println("Hvis du starter et nyt betalingsår, bliver alle medlemmers kontingenter nulstillet og beregnet på ny.");
        System.out.println("Er du sikker på at du vil fortsætte? Denne handling kan ikke fortrydes.");
        System.out.println("""
                
                1. Ja
                2. Nej
                """);
        while(true){
            int choice = readInt("Vælg: ");

            if(choice != 1 && choice != 2){
                System.out.println("Ugyldigt valg! Vælg igen");
                continue;
            }
            if(choice == 1){
                try{
                    controller.startNewPaymentYear();
                    System.out.println("Nyt betalingsår startet. Alle kontingenter er blevet nulstillet og genberegnet");
                } catch(IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
               break;
            }
            System.out.println("\nHandlingen blev annulleret. Betalingsåret er uændret.");
            break;
        }


    }

    private void trainerStart() {
        if (correctPin(controller.getTrainerPin())) {
            System.out.println("Du er nu logget ind som træner!\n");
            while (true) {
                System.out.println("""
                        1. Gå til menu
                        """);
                int choice = readInt("Vælg: ");

                switch (choice) {
                    case 1 -> {
                        return;
                    }
                    default -> System.out.println("Ugyldigt valg! Vælg igen");
                }
            }
        }

    }

    private boolean correctPin(int otherPin) {
        do {
            int pin = readInt("Pinkode (Skriv 0 for at fortryde): ");
            if (pin == 0) {
                break;
            }
            if (pin == otherPin) {
                return true;
            }
            System.out.println("Forkert kode! Prøv igen\n");
        } while (true);

        return false;
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isBlank()) {
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
            System.out.println("\nDer er ingen medlemmer i klubben endnu.");
        } else {
            for (Member member : controller.getMembers()) {
                System.out.println(member);
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

    private void addMember() {
        boolean competitive = false;
        boolean active = false;

        System.out.println();
        String firstName = readName("Fornavn: ");
        firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);

        String lastName = readName("Efternavn: ");
        lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);

        LocalDate birthday = readDate("fødselsdag [dd-mm-åååå]: ");

        while (true) {
            System.out.println("""
                    
                    1. Aktivt medlemskab
                    2. Passivt medlemskab
                    """);
            int choice = readInt("Vælg: ");
            if (choice != 1 && choice != 2) {
                System.out.println("Ugyldigt valg! Vælg igen");
            } else if (choice == 1) {
                active = true;
                break;
            } else {
                break;
            }
        }

        if (active) {
            while (true) {
                System.out.println("""
                        
                        1. Konkurrencesvømmer
                        2. Motionist
                        """);
                int choice = readInt("Vælg: ");
                if (choice != 1 && choice != 2) {
                    System.out.println("Ugyldigt valg! Vælg igen");
                } else if (choice == 1) {
                    competitive = true;
                    break;
                } else {
                    break;
                }
            }

        }

        if (competitive) {
            try {
                controller.addCompetitiveSwimmer(firstName, lastName, birthday, active, addStrokes());
                System.out.println("Medlem oprettet!");
            } catch (IllegalArgumentException e) {
                System.out.println("Medlemmet blev ikke oprettet. " + e.getMessage());
            }
            return;
        }

        try {
            controller.addMember(firstName, lastName, birthday, active);
            System.out.println("Medlem oprettet!");
        } catch (IllegalArgumentException e) {
            System.out.println("Medlemmet blev ikke oprettet. " + e.getMessage());
        }
    }

    public ArrayList<Stroke> addStrokes() {
        System.out.println("Tilføj mindst en af følgende discipliner:\n");
        ArrayList<Stroke> allStrokes = new ArrayList<>(List.of(Stroke.BUTTERFLY, Stroke.FRONTCRAWL, Stroke.BACKSTROKE, Stroke.BREASTSTROKE));
        ArrayList<Stroke> selectedStrokes = new ArrayList<>();

        while (true) {
            try {
                int count = 1;
                for (Stroke stroke : allStrokes) {
                    System.out.println(count + ". " + stroke.toString());
                    count++;
                }
                int choice = readInt("Vælg: ");
                selectedStrokes.add(allStrokes.get(choice - 1));
                allStrokes.remove(choice - 1);
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Ugyldigt valg! Vælg igen");
            }
        }

        while (!allStrokes.isEmpty()) {
            int count = 1;
            for (Stroke stroke : allStrokes) {
                System.out.println(count + ". " + stroke.toString());
                count++;
            }
            int choice = readInt("Vælg (0 = fortsæt): ");
            if (choice == 0) {
                break;
            }
            selectedStrokes.add(allStrokes.get(choice - 1));
            allStrokes.remove(choice - 1);
        }

        return selectedStrokes;
    }

    public Member searchMember() {
        Member member = null;
        while (true) {
            int memberId = readInt("Søg efter medlemsnummer (0 = fortryd): ");
            if (memberId == 0) {
                break;
            }
            try {
                member = controller.searchMember(memberId);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("\n" + e.getMessage() + "\n");
            }
        }
        return member;
    }
}
