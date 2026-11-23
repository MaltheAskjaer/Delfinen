package controller;

import domain.ClubPresident;
import domain.ClubTreasurer;
import domain.Trainer;
import filehandling.FileHandler;

import java.time.LocalDate;

public class Controller {
    ClubPresident president;
    ClubTreasurer treasurer;
    Trainer trainer;
    FileHandler fileHandler;

    public Controller() {
        this.president = new ClubPresident();
        this.treasurer = new ClubTreasurer();
        this.trainer = new Trainer();
        this.fileHandler = new FileHandler();
    }

    public int getPresidentPin(){
        return president.getPin();
    }

    public int getTreasurerPin(){
        return treasurer.getPin();
    }

    public int getTrainerPin(){
        return trainer.getPin();
    }

    public void addMember(String firstName, String lastName, LocalDate birthday, boolean active){
        president.addMember(firstName, lastName, birthday, active);
    }

    public void addCompetitiveSwimmer(String firstName, String lastName, LocalDate birthday, boolean active){
        president.addCompetitiveSwimmer(firstName, lastName, birthday, active);
    }

}
