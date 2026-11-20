package controller;

import domain.ClubPresident;
import domain.ClubTreasurer;
import domain.Trainer;
import filehandling.FileHandler;

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

}
