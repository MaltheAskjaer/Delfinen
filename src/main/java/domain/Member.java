package domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Member {

    private int memberNumber;
    private String name;
    private LocalDate birthDate;
    private String membershipForm;

    public Member(int memberNumber, String name, LocalDate birthDate, String membershipForm)
    {
        this.memberNumber = memberNumber;
        this.name = name;
        this.birthDate = birthDate;
        this.membershipForm = membershipForm;
    }

    public int getMemberNumber()
    {
        return memberNumber;
    }

    public String getName()
    {
        return name;
    }

    public LocalDate getBirthDate()
    {
        return birthDate;
    }

    public String getMembershipForm()
    {
        return membershipForm;
    }
    public int getAgeOn(LocalDate date)
    {
        long antalDage = ChronoUnit.DAYS.between(birthDate, date);
        return (int) (antalDage / 365);
    }

    @Override
    public String toString()
    {
        return "Member{" +
                "memberNumber=" + memberNumber +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", membershipForm='" + membershipForm + '\'' +
                '}';
    }
}
