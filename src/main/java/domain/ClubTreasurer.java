package domain;

import java.util.ArrayList;

public class ClubTreasurer {

    private int pinCode = 1234;

    public int getPin() {
        return pinCode;
    }

    public void recordPayment(Member member, boolean paid){
        member.setPaid(paid);
    }

    public int getFeeAmount(Member member){
        return member.getMembershipFeeAmount();
    }

    public void startNewPaymentYear(ArrayList<Member> members){
        if(members == null || members.isEmpty()){
            throw new IllegalArgumentException("Ingen medlemmer i medlemslisten");
        }
        for(Member member : members){
            member.setPaid(false);
            member.setNewAmount();
        }
    }



}
