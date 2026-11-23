package domain;

import java.time.LocalDate;
import java.util.ArrayList;

public class ClubPresident {
    private static int pinCode = 1234;
    private ArrayList<Member> members;


    public ClubPresident(){
        members = new ArrayList<>();
    }

    public int getPin() {
        return pinCode;
    }

    public void addMember(String firstName, String lastName, LocalDate birthday, boolean active){
        Member member = new Member(firstName, lastName, birthday, active);
        members.add(member);
    }

    public void addCompetitiveSwimmer(String firstName, String lastName, LocalDate birthday, boolean active){
        CompetitiveSwimmer member = new CompetitiveSwimmer(firstName, lastName, birthday, active);
        members.add(member);

    }
    public ArrayList<Member> getMembers()
    {
        ArrayList<Member> memberList = members;
        return memberList;
    }
}
