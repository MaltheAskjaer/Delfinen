package controller;

import domain.ClubPresident;
import domain.ClubTreasurer;
import domain.Member;
import domain.Trainer;
import filehandling.FileHandler;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller
{
    ClubPresident president;
    ClubTreasurer treasurer;
    Trainer trainer;
    FileHandler fileHandler;

    private ArrayList<Member> members = new ArrayList<>();

    public Controller()
    {
        this.president = new ClubPresident();
        this.treasurer = new ClubTreasurer();
        this.trainer = new Trainer();
        this.fileHandler = new FileHandler();
    }

    public int getPresidentPin()
    {
        return president.getPin();
    }

    public int getTreasurerPin()
    {
        return treasurer.getPin();
    }

    public int getTrainerPin()
    {
        return trainer.getPin();
    }

    public void addMember(Member member)
    {
        members.add(member);
    }

    public ArrayList<Member> getMembers()
    {
        return members;
    }
}
