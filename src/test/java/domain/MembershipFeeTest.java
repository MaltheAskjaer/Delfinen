package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MembershipFeeTest {


    @Test
    void active17yearOldPays1000(){
        MembershipFee membershipFee = new MembershipFee(17, true);

        assertEquals(1000, membershipFee.getAmount());
    }

    @Test
    void active18yearOldPays1600(){
        MembershipFee membershipFee = new MembershipFee(18, true);

        assertEquals(1600, membershipFee.getAmount());
    }

    @Test
    void active59yearOldPays1600(){
        MembershipFee membershipFee = new MembershipFee(59, true);

        assertEquals(1600, membershipFee.getAmount());
    }

    @Test
    void active60yearOldPays1200(){
        MembershipFee membershipFee = new MembershipFee(60, true);

        assertEquals(1200, membershipFee.getAmount());
    }

    @Test
    void passive60yearOldPays500(){
        MembershipFee membershipFee = new MembershipFee(60, false);
        assertEquals(500, membershipFee.getAmount());
    }

}