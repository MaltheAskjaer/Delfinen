package domain;

import java.time.LocalDate;

public class MembershipFee {
    private static final int PASSIVE_MEMBERSHIP = 500;
    private static final int ACTIVE_UNDER_18 = 1000;
    private static final int ACTIVE_18_TO_59 = 1600;
    private static final int DISCOUNT_PERCENT_FROM_60 = 25;

    private int amount;
    private boolean paid;
    private LocalDate paidDate;

    public MembershipFee(int age, boolean active) {
        amount = calculateAmount(age, active);
        this.paid = false;
        this.paidDate = null;
    }

    public LocalDate getPaidDate(){
        return paidDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setPaid(boolean paid){
        this.paid = paid;
        this.paidDate = LocalDate.now();
    }

    public void setNewAmount(int age, boolean active){
        amount = calculateAmount(age, active);
        this.paidDate = null;
    }

    public boolean isPaid(){
        return paid;
    }

    private static int calculateAmount(int age, boolean active){
        if(!active){
            return PASSIVE_MEMBERSHIP;
        }
        if(age < 18){
            return ACTIVE_UNDER_18;
        }
        if(age >= 60){
            return ACTIVE_18_TO_59 * (100 - DISCOUNT_PERCENT_FROM_60) / 100;
        }
        else{
            return ACTIVE_18_TO_59;
        }
    }




}
