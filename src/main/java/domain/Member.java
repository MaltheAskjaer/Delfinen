package domain;
import java.time.LocalDate;

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
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.active = active;
    }

}
