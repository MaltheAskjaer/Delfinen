package filehandling;

import domain.CompetitiveSwimmer;
import domain.Member;
import domain.Stroke;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {
    private static final String TEST_MEMBER_FILE = "test-members.txt";
    private static final String TEST_STROKE_FILE = "test-strokes.txt";

    // Sletter kun testenes egne filer, så næste test starter med tomme data.
    @AfterEach
    void deleteTestFiles() {
        new File(TEST_MEMBER_FILE).delete();
        new File(TEST_STROKE_FILE).delete();
    }

    @Test
    void savedEmptyListCanBeLoadedAgain() throws FileNotFoundException {
        // Arrange
        FileHandler fileHandler = new FileHandler(TEST_MEMBER_FILE, TEST_STROKE_FILE);
        ArrayList<Member> members = new ArrayList<>();

        // Act
        fileHandler.save(members);
        ArrayList<Member> loadedMembers = fileHandler.load();

        // Assert
        assertTrue(loadedMembers.isEmpty());
    }

    @Test
    void savedMemberCanBeLoadedAgain() throws FileNotFoundException {
        // Arrange
        FileHandler fileHandler = new FileHandler(TEST_MEMBER_FILE, TEST_STROKE_FILE);
        LocalDate birthday = LocalDate.of(2000, 5, 12);
        Member member = new Member("Søren", "Lund", birthday, true);
        LocalDate paymentDate = LocalDate.of(2026, 11, 20);
        member.restorePayment(paymentDate);
        ArrayList<Member> members = new ArrayList<>();
        members.add(member);

        // Act
        fileHandler.save(members);
        ArrayList<Member> loadedMembers = fileHandler.load();

        // Assert
        assertEquals(1, loadedMembers.size());
        Member loadedMember = loadedMembers.get(0);
        assertTrue(loadedMember.hasPaid());
        assertEquals(paymentDate, loadedMember.getPaidDate());
        assertEquals(member.getMemberId(), loadedMember.getMemberId());
        assertEquals("Søren", loadedMember.getFirstName());
        assertEquals("Lund", loadedMember.getLastName());
        assertEquals(birthday, loadedMember.getBirthday());
        assertEquals("RECREATIONAL", loadedMember.getMemberType());
    }

    @Test
    void missingMemberFileThrowsException() {
        // Arrange
        FileHandler fileHandler = new FileHandler(TEST_MEMBER_FILE, TEST_STROKE_FILE);
        new File(TEST_MEMBER_FILE).delete();

        // Act & Assert
        assertThrows(FileNotFoundException.class, () -> fileHandler.load());
    }

    @Test
    void invalidMemberLinesAreSkipped() throws FileNotFoundException {
        // Arrange
        FileHandler fileHandler = new FileHandler(TEST_MEMBER_FILE, TEST_STROKE_FILE);
        PrintStream file = new PrintStream(new File(TEST_MEMBER_FILE));
        file.println("id;firstName;lastName;birthday;memberType;paid;paidDate");
        file.println("abc;Anna;Lund;2000-05-12;PASSIVE;false;");
        file.println("18;Ida;Lund;2000-13-12;PASSIVE;false;");
        file.println("17;Emil;Jensen;1998-03-21;RECREATIONAL;false;");
        file.close();

        // Act
        ArrayList<Member> loadedMembers = fileHandler.load();

        // Assert
        assertEquals(1, loadedMembers.size());
        assertEquals(17, loadedMembers.get(0).getMemberId());
        assertEquals(2, fileHandler.getSkippedLineCount());
    }

    // Tjekker, at medlemsnummeret forbinder svømmeren med begge discipliner efter indlæsning.
    @Test
    void savedCompetitiveSwimmerKeepsIdAndStrokes() throws FileNotFoundException {
        // Arrange
        FileHandler fileHandler = new FileHandler(TEST_MEMBER_FILE, TEST_STROKE_FILE);

        ArrayList<Stroke> strokes = new ArrayList<>();
        strokes.add(Stroke.FRONTCRAWL);
        strokes.add(Stroke.BACKSTROKE);

        CompetitiveSwimmer swimmer = new CompetitiveSwimmer(
                "Anna", "Lund", LocalDate.of(2000, 5, 12), true, strokes);

        ArrayList<Member> members = new ArrayList<>();
        members.add(swimmer);

        // Act
        fileHandler.save(members);
        ArrayList<Member> loadedMembers = fileHandler.load();

        // Assert
        assertEquals(1, loadedMembers.size());

        Member loadedSwimmer = loadedMembers.get(0);
        assertEquals(swimmer.getMemberId(), loadedSwimmer.getMemberId());
        assertEquals("COMPETITIVE", loadedSwimmer.getMemberType());
        assertEquals(strokes, loadedSwimmer.getStrokes());
    }
}
