package domain;

import java.time.LocalDate;

public class CompetitiveSwimmer extends Member {

    public CompetitiveSwimmer(int memberNumber, String name, LocalDate birthDate, String membershipForm)
    {
        super(memberNumber, name, birthDate, membershipForm);
    }
}
