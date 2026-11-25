package domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    void validMemberDoesNotThrowException() {
        assertDoesNotThrow(() -> new Member("Jason", "Duval", LocalDate.of(1996, 10, 19), true));
    }

    @Test
    void invalidMemberThrowsException(){
        //Tomt felt
        assertThrows(IllegalArgumentException.class, () -> new Member("", "Duval", LocalDate.of(1996, 10, 19), true));

        //Navn med tal
        assertThrows(IllegalArgumentException.class, () -> new Member("Jason9", "Duval", LocalDate.of(1996, 10, 19), true));
    }

    @Test
    void birthdayInFutureThrowsException(){
        assertThrows(IllegalArgumentException.class, () -> new Member("Jason", "Duval", LocalDate.now().plusDays(1), true));
    }

    @Test
    void memberIdIsUnique(){
        Member member = new Member("Jason", "Duval", LocalDate.of(1996, 10, 19), true);
        Member member2 = new Member("Lucia", "Caminos", LocalDate.of(2000, 10, 19), true);

        assertNotEquals(member.getMemberId(), member2.getMemberId());
        assertEquals(member.getMemberId() + 1, member2.getMemberId());

    }

    @Test
    void gettersReturnMemberDetails() {
        LocalDate birthday = LocalDate.of(2000, 5, 15);
        Member member = new Member("Maja", "Jensen", birthday, true);

        assertEquals("Maja Jensen", member.getName());
        assertEquals(birthday, member.getBirthday());
    }

    @Test
    void ageIsCalculatedForProvidedDate() {
        LocalDate dayBefore18thBirthday = LocalDate.of(2026, 11, 23);
        LocalDate eighteenthBirthday = LocalDate.of(2026, 11, 24);
        Member member = new Member("Jason", "Duval", LocalDate.of(2008, 11, 24), true);

        assertEquals(17, member.getAgeOn(dayBefore18thBirthday));
        assertEquals(18, member.getAgeOn(eighteenthBirthday));

    }

    @Test
    void setNewAmountUpdatesFeeWhenMemberTurns18() {
        LocalDate today = LocalDate.now();
        Member member = new Member(
                "Maja", "Jensen",
                today.minusYears(18).plusDays(1),
                true
        );

        assertEquals(1000, member.getMembershipFeeAmount());

        member.setBirthday(today.minusYears(18));
        member.setNewAmount();

        assertEquals(1600, member.getMembershipFeeAmount());
    }
}
