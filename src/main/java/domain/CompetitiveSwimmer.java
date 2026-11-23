package domain;

import java.time.LocalDate;

public class CompetitiveSwimmer extends Member {

    public CompetitiveSwimmer(String firstName, String lastName, LocalDate birthday, boolean active) {
        super(firstName, lastName, birthday, active);
    }
}
