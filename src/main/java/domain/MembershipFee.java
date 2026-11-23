package domain;

public class MembershipFee {
    private static final int PASSIVE_MEMBERSHIP = 500;
    private static final int ACTIVE_UNDER_18 = 1000;
    private static final int ACTIVE_18_TO_59 = 1600;
    private static final int DISCOUNT_PERCENT_FROM_60 = 25;

    private int year;
    private int amount;
    private boolean paid;

    public MembershipFee(int year, int amount) {
        this.year = year;
        this.amount = amount;
        this.paid = false;
    }

    public static int calculateAmount(int age, boolean active){
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
