package domain;

import java.time.LocalDate;

public class CompetitiveSwimmer extends Member {

    public CompetitiveSwimmer(String firstName, String lastName, LocalDate birthday, boolean active) {
        super(firstName, lastName, birthday, active);
    }

    @Override
    public String toString(){
        String result = super.toString();
        int age = this.getAgeOn(LocalDate.now());
        if (age < 18)
        {
            result += "\nHold: Junior";
        } else
        {
            result += "\nHold: Senior";
        }
        return result;
    }

}
