package filehandling;

import domain.CompetitiveSwimmer;
import domain.Member;
import domain.Stroke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private static final String MEMBER_HEADER = "id;firstName;lastName;birthday;memberType";
    private static final String STROKE_HEADER = "memberId;stroke";
    private int skippedLineCount;
    private final String memberFile;
    private final String strokeFile;

    // Programmet og testene kan give hver deres filnavne.
    public FileHandler(String memberFile, String strokeFile) {
        this.memberFile = memberFile;
        this.strokeFile = strokeFile;
    }

    public int getSkippedLineCount() {
        return skippedLineCount;
    }


    // Skriver én række pr. medlem og én række pr. disciplin i hver sin fil.
    public void save(ArrayList<Member> memberList) throws FileNotFoundException {
        PrintStream members = new PrintStream(new File(memberFile));
        members.println(MEMBER_HEADER);

        PrintStream strokes = new PrintStream(new File(strokeFile));
        strokes.println(STROKE_HEADER);

        for (Member member : memberList) {
            members.println(member.getMemberId() + ";" + member.getFirstName() + ";"
                    + member.getLastName() + ";" + member.getBirthday() + ";"
                    + member.getMemberType());

            // Medlemsnummeret knytter disciplinen til medlemmet i den anden fil.
            for (Stroke stroke : member.getStrokes()) {
                strokes.println(member.getMemberId() + ";" + stroke.name());
            }
        }


        members.close();
        strokes.close();
    }

    // Genskaber medlemmerne fra filerne og tæller medlemsrækker, der springes over.
    public ArrayList<Member> load() throws FileNotFoundException {
        skippedLineCount = 0;
        ArrayList<Member> memberList = new ArrayList<>();
        Scanner fileScanner = new Scanner(new File(memberFile));

        // Første linje er kolonneoverskriften.
        if (fileScanner.hasNextLine()) {
            fileScanner.nextLine();
        }
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            // -1 bevarer tomme felter sidst på linjen til kontrollen af antal felter.
            String[] parts = line.split(";", -1);
            if (parts.length != 5) {
                skippedLineCount++;
                continue;
            }
            try {
                int id = Integer.parseInt(parts[0]);
                String firstName = parts[1];
                String lastName = parts[2];
                LocalDate birthday = LocalDate.parse(parts[3]);
                String memberType = parts[4];

                boolean active = false;
                if (memberType.equals("RECREATIONAL") || memberType.equals("COMPETITIVE")) {
                    active = true;
                }

                // Konstruktøren kræver mindst én disciplin, så vi læser dem først.
                if (memberType.equals("COMPETITIVE")) {
                    ArrayList<Stroke> strokes = loadStrokes(id);
                    if (strokes.isEmpty()) {
                        skippedLineCount++;
                        continue;
                    }
                    memberList.add(new CompetitiveSwimmer(id, firstName, lastName, birthday, active, strokes));
                } else if (memberType.equals("PASSIVE") || memberType.equals("RECREATIONAL")) {
                    memberList.add(new Member(id, firstName, lastName, birthday, active));
                } else {
                    skippedLineCount++;
                }
            } catch (IllegalArgumentException e) {
                skippedLineCount++;
            } catch (DateTimeParseException e) {
                skippedLineCount++;
            }
        }
        fileScanner.close();
        return memberList;
    }

    // Finder disciplinlinjerne for det medlemsnummer, vi er ved at indlæse.
    private ArrayList<Stroke> loadStrokes(int memberId) throws FileNotFoundException {
        ArrayList<Stroke> strokes = new ArrayList<>();
        File file = new File(strokeFile);

        if (!file.exists()) {
            return strokes;
        }

        Scanner fileScanner = new Scanner(file);

        try {
            if (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
            }

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(";", -1);
                int id = Integer.parseInt(parts[0]);

                if (id == memberId) {
                    if (parts.length != 2) {
                        throw new IllegalArgumentException("Disciplinlinjen skal have to felter.");
                    }

                    // Fx bliver teksten FRONTCRAWL til værdien Stroke.FRONTCRAWL.
                    Stroke stroke = Stroke.valueOf(parts[1]);
                    strokes.add(stroke);
                }
            }
        } catch (IllegalArgumentException e) {
            // En tom liste får load() til at springe svømmeren over.
            strokes = new ArrayList<>();
        }

        fileScanner.close();
        return strokes;
    }
}
