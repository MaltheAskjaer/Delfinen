package domain;
import java.time.LocalDate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Member {

    private static int nextId = 1;
    private int memberId;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private boolean active;

    public Member(String firstName, String lastName, LocalDate birthday, boolean active){
        this.memberId = nextId;
        nextId++;
        setFirstName(firstName);
        setLastName(lastName);
        setBirthday(birthday);
        setActive(active);
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

    public int getMemberId() {
        return memberId;
    }

    public String getName()
    {
        return this.firstName + " " + this.lastName;
    }

    public LocalDate getBirthday()
    {
        return birthday;
    }

    public int getAgeOn(LocalDate date)
    {
        long antalDage = ChronoUnit.DAYS.between(birthday, date);
        return (int) (antalDage / 365);
    }

    @Override
    public String toString()
    {
        String result = "\n";
        result += "Medlemsnummer: " + this.memberId + ", " + (active ? "Aktivt" : "Passivt") + " medlemskab";
        result += "\nNavn: " + this.getName();
        result += "\nAlder: " + this.getAgeOn(LocalDate.now()) + " år";
        return result;
    }
}
