package controller;

import domain.*;
import filehandling.FileHandler;

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
    public boolean addTrainingResult(int memberId, TrainingResult result) {
        Member member = findMemberById(memberId);

        if (member == null) {
            throw new IllegalArgumentException(
                    "Der findes ikke et medlem med det medlemsnummer."
            );
        }

        if (!(member instanceof CompetitiveSwimmer)) {
            throw new IllegalArgumentException(
                    "Medlemmet er ikke en konkurrencesvømmer."
            );
        }

        CompetitiveSwimmer swimmer = (CompetitiveSwimmer) member;
        return swimmer.addTrainingResult(result);
    }

    private Member findMemberById(int memberId) {

        for (Member member : getMembers()) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }

        return null;
    }
}
