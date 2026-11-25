package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ClubTreasurerTest {

    ClubTreasurer treasurer;

    @BeforeEach
    void setUp(){
        treasurer = new ClubTreasurer();
    }

    @Test
    void recordPaymentAddPaidDateAndUpdatesPaid() {
        Member member = new Member("Anna", "Jensen",
                LocalDate.of(2000, 1, 1), true);

        treasurer.recordPayment(member, true);

        assertTrue(member.hasPaid());
        assertEquals(LocalDate.now(), member.getPaidDate());

    }

    @Test
    void getFeeAmountReturnsMembershipFee(){
        Member member = new Member("Anna", "Jensen",
                LocalDate.of(2000, 1, 1), true);

        int feeAmount = treasurer.getFeeAmount(member);

        assertEquals(feeAmount, member.getMembershipFeeAmount());
    }

    @Test
    void newPaymentYearResetsPaymentForAllMembers() {
        Member member = new Member("Anna", "Jensen",
                LocalDate.of(2000, 1, 1), true);
        member.setPaid(true);

        ArrayList<Member> members = new ArrayList<>();
        members.add(member);

        treasurer.startNewPaymentYear(members);

        assertFalse(member.hasPaid());
        assertNull(member.getPaidDate());
        assertEquals(1600, member.getMembershipFeeAmount());
    }
}