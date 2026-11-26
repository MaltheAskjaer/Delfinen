package domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
    void passiveMembersAlwaysPay500(){
        MembershipFee membershipFee60 = new MembershipFee(60, false);
        MembershipFee membershipFee15 = new MembershipFee(15, false);
        assertEquals(500, membershipFee60.getAmount());
        assertEquals(500, membershipFee15.getAmount());
    }

    @Test
    void newMembershipFeeIsUnpaid() {
        MembershipFee fee = new MembershipFee(25, true);

        assertFalse(fee.isPaid());
        assertNull(fee.getPaidDate());
    }

    @Test
    void paidMembershipFeeIsMarkedPaidAndGetsDate() {
        MembershipFee fee = new MembershipFee(25, true);

        fee.setPaid(true);

        assertTrue(fee.isPaid());
        assertEquals(LocalDate.now(), fee.getPaidDate());
    }

    @Test
    void changingAmountForNewYearClearsPaymentDate() {
        MembershipFee fee = new MembershipFee(17, true);
        fee.setPaid(true);

        fee.setNewAmount(18, true);

        assertEquals(1600, fee.getAmount());
        assertNull(fee.getPaidDate());
    }

}