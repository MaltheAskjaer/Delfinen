package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MembershipFeeTest {


    @Test
    void active17yearOldPays1000(){
        int amount = MembershipFee.calculateAmount(17, true);

        assertEquals(1000, amount);
    }

    @Test
    void active18yearOldPays1600(){
        int amount = MembershipFee.calculateAmount(18, true);

        assertEquals(1600, amount);
    }

    @Test
    void active59yearOldPays1600(){
        int amount = MembershipFee.calculateAmount(59, true);

        assertEquals(1600, amount);
    }

    @Test
    void active60yearOldPays1200(){
        int amount = MembershipFee.calculateAmount(60, true);

        assertEquals(1200, amount);
    }

    @Test
    void passive60yearOldPays500(){
        int amount = MembershipFee.calculateAmount(60, false);

        assertEquals(500, amount);
    }

}