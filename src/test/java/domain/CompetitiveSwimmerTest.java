package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompetitiveSwimmerTest {

    ArrayList<Stroke> allStrokes;
    ArrayList<Stroke> emptyStrokesList;

    @BeforeEach
    void setUp(){
        allStrokes = new ArrayList<>(List.of(Stroke.BUTTERFLY, Stroke.FRONTCRAWL, Stroke.BACKSTROKE, Stroke.BREASTSTROKE));
    }

    @Test
    void selectedStrokesAreSavedInSwimmerClass(){
        //Arrange
        CompetitiveSwimmer swimmer = new CompetitiveSwimmer("Jason", "Duval", LocalDate.of(1996, 10, 19), true, allStrokes);

        //Assert
        assertEquals(allStrokes.size(), swimmer.getStrokes().size());

    }

    @Test
    void noStrokesThrowsException(){
        assertThrows(IllegalArgumentException.class, () -> new CompetitiveSwimmer("Jason", "Duval", LocalDate.of(1996, 10, 19), true, emptyStrokesList));
    }

}