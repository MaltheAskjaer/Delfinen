package domain;
import java.time.LocalDate;
import java.time.Period;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Member {

    private static int nextId = 1;
    private int memberId;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private boolean active;

    public Member(String firstName, String lastName, LocalDate birthday, boolean active) {
        setFirstName(firstName);
        setLastName(lastName);
        setBirthday(birthday);
        setActive(active);
        this.memberId = nextId;
        nextId++;
    }

    // Bruges ved indlæsning, så medlemmet beholder sit medlemsnummer fra filen.
    public Member(int id, String firstName, String lastName, LocalDate birthday, boolean active){
        setFirstName(firstName);
        setLastName(lastName);
        setBirthday(birthday);
        setActive(active);
        this.memberId = id;

        // Nye medlemmer skal have et nummer over dem, vi allerede har indlæst.
        if(id >= nextId){
            nextId = id + 1;
        }
    }

    public void setFirstName(String firstName) {
        if(firstName.isBlank()){
            throw new IllegalArgumentException("Fornavn må ikke være tomt.");
        }

        if (!firstName.matches("[\\p{L}]+")) {
            throw new IllegalArgumentException("Fornavn må kun indeholde bogstaver.");
        }

        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if(lastName.isBlank()){
            throw new IllegalArgumentException("Efternavn må ikke være tomt.");
        }

        if (!lastName.matches("[\\p{L}]+")) {
            throw new IllegalArgumentException("Efternavn må kun indeholde bogstaver.");
        }

        this.lastName = lastName;
    }

    public void setBirthday(LocalDate birthday) {
        if(birthday.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Fødselsdag må ikke være i fremtiden.");
        }
        this.birthday = birthday;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    // Medlemstypen bliver skrevet i sidste kolonne i medlemsfilen.
    public String getMemberType(){
        if(active){
            return "RECREATIONAL";
        }
        return "PASSIVE";
    }

    // Almindelige medlemmer har ingen konkurrencediscipliner at gemme.
    public ArrayList<Stroke> getStrokes(){
        return new ArrayList<>();
    }

    public int getMemberId() {
        return memberId;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public LocalDate getBirthday()
    {
        return birthday;
    }

    public int getAgeOn(LocalDate date)
    {
        return Period.between(birthday, date).getYears();
    }

    @Override
    public String toString()
    {
        String result = "\n";
        result += "Medlemsnummer: " + this.memberId;
        result += "\nMedlemskab: " + (active ? "Aktivt" : "Passivt");
        result += "\nNavn: " + this.getFirstName() + " " + this.getLastName();
        result += "\nAlder: " + this.getAgeOn(LocalDate.now()) + " år";
        return result;
    }
}
