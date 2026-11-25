package controller;

import domain.*;
import filehandling.FileHandler;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    ClubPresident president;
    ClubTreasurer treasurer;
    Trainer trainer;
    FileHandler fileHandler;

    public Controller() {
        this.president = new ClubPresident();
        this.treasurer = new ClubTreasurer();
        this.trainer = new Trainer();
        this.fileHandler = new FileHandler("members.txt", "strokes.txt");
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

    public void addCompetitiveSwimmer(String firstName, String lastName, LocalDate birthday, boolean active, ArrayList<Stroke> strokes){
        president.addCompetitiveSwimmer(firstName, lastName, birthday, active, strokes);
    }

    public ArrayList<Member> getMembers(){
        return president.getMembers();
    }

    public int getMembershipFeeAmount(Member member){
        return treasurer.getFeeAmount(member);
    }

    public void recordPayment(Member member){
        treasurer.recordPayment(member, true);
    }

    public Member searchMember(int memberId){
        if(getMembers() == null || getMembers().isEmpty()){
            throw new IllegalArgumentException("Medlemslisten er tom");
        }

        for(Member member : getMembers()){
            if(member.getMemberId() == memberId){
                return member;
            }
        }

        return null;
    }

    public void startNewPaymentYear(){
        treasurer.startNewPaymentYear(getMembers());
    }

    // Giver klubbens medlemsliste videre til FileHandler, når programmet skal gemme.
    public void save() throws FileNotFoundException {
        fileHandler.save(getMembers());
    }

    // Lægger medlemmerne fra filen ind i den liste, som menuen bruger.
    public void load() throws FileNotFoundException {
    ArrayList<Member> loadedMembers = fileHandler.load();

    for(Member member : loadedMembers){
        getMembers().add(member);
    }
    }

    // Menuen bruger tallet til at afgøre, om det er sikkert at gemme.
    public int getSkippedLineCount(){
        return fileHandler.getSkippedLineCount();
    }


}
