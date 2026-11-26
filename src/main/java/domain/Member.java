package domain;
import java.time.LocalDate;
import java.time.Period;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Member {

    private static int nextId = 1;
    private int memberId;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private boolean active;
    private MembershipFee membershipfee;

    public Member(String firstName, String lastName, LocalDate birthday, boolean active){
        this.memberId = nextId;
        nextId++;
        setFirstName(firstName);
        setLastName(lastName);
        setBirthday(birthday);
        setActive(active);
        membershipfee = new MembershipFee(getAgeOn(LocalDate.now()), active);
    }

    public int getMembershipFeeAmount(){
        return this.membershipfee.getAmount();
    }

    public LocalDate getPaidDate(){
        return this.membershipfee.getPaidDate();
    }

    public void setNewAmount(){
        this.membershipfee.setNewAmount(this.getAgeOn(LocalDate.now()), active);
    }

    public boolean hasPaid(){
        return this.membershipfee.isPaid();
    }

    public void setPaid(boolean paid){
        this.membershipfee.setPaid(paid);
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

    public String getFormattedMemberId() {
        return String.format("%04d", this.memberId);
    }

    public int getMemberId(){
        return this.memberId;
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
        return Period.between(birthday, date).getYears();
    }

    @Override
    public String toString()
    {
        String result = "\n";
        result += "Medlemsnummer: " + getFormattedMemberId();
        result += "\nMedlemskab: " + (active ? "Aktivt" : "Passivt");
        result += "\nNavn: " + this.getName();
        result += "\nAlder: " + this.getAgeOn(LocalDate.now()) + " år";
        return result;
    }
}
