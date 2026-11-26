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

    @Test
    void firstTrainingResultIsSavedAsBestForItsStroke() {
        CompetitiveSwimmer swimmer = new CompetitiveSwimmer(
                "Jason", "Duval", LocalDate.of(1996, 10, 19), true, allStrokes);
        TrainingResult firstResult = new TrainingResult(
                32, LocalDate.of(2026, 11, 20), Stroke.FRONTCRAWL);

        boolean becameBest = swimmer.addTrainingResult(firstResult);

        assertTrue(becameBest);
        assertEquals(firstResult, swimmer.getBestTrainingResult(Stroke.FRONTCRAWL));
    }

    @Test
    void fasterTrainingResultReplacesPreviousBestIncludingDate() {
        CompetitiveSwimmer swimmer = new CompetitiveSwimmer(
                "Jason", "Duval", LocalDate.of(1996, 10, 19), true, allStrokes);
        swimmer.addTrainingResult(new TrainingResult(
                32, LocalDate.of(2026, 11, 20), Stroke.FRONTCRAWL));
        TrainingResult fasterResult = new TrainingResult(
                31, LocalDate.of(2026, 11, 22), Stroke.FRONTCRAWL);

        boolean becameBest = swimmer.addTrainingResult(fasterResult);

        assertTrue(becameBest);
        assertEquals(fasterResult, swimmer.getBestTrainingResult(Stroke.FRONTCRAWL));
        assertEquals(31, swimmer.getBestTrainingResult(Stroke.FRONTCRAWL).getTimeInSeconds());
        assertEquals(LocalDate.of(2026, 11, 22), swimmer.getBestTrainingResult(Stroke.FRONTCRAWL).getDate());
    }

    @Test
    void slowerOrEqualTrainingResultDoesNotReplacePreviousBest() {
        CompetitiveSwimmer swimmer = new CompetitiveSwimmer(
                "Jason", "Duval", LocalDate.of(1996, 10, 19), true, allStrokes);
        TrainingResult previousBest = new TrainingResult(
                32, LocalDate.of(2026, 11, 20), Stroke.FRONTCRAWL);
        swimmer.addTrainingResult(previousBest);

        boolean slowerBecameBest = swimmer.addTrainingResult(new TrainingResult(
                33, LocalDate.of(2026, 11, 21), Stroke.FRONTCRAWL));
        boolean equalBecameBest = swimmer.addTrainingResult(new TrainingResult(
                32, LocalDate.of(2026, 11, 22), Stroke.FRONTCRAWL));

        assertFalse(slowerBecameBest);
        assertFalse(equalBecameBest);
        assertEquals(previousBest, swimmer.getBestTrainingResult(Stroke.FRONTCRAWL));
    }

    @Test
    void bestTrainingResultsAreStoredSeparatelyForEachStroke() {
        CompetitiveSwimmer swimmer = new CompetitiveSwimmer(
                "Jason", "Duval", LocalDate.of(1996, 10, 19), true, allStrokes);
        TrainingResult frontcrawlResult = new TrainingResult(
                32, LocalDate.of(2026, 11, 20), Stroke.FRONTCRAWL);
        TrainingResult butterflyResult = new TrainingResult(
                29, LocalDate.of(2026, 11, 21), Stroke.BUTTERFLY);

        swimmer.addTrainingResult(frontcrawlResult);
        swimmer.addTrainingResult(butterflyResult);

        assertEquals(frontcrawlResult, swimmer.getBestTrainingResult(Stroke.FRONTCRAWL));
        assertEquals(butterflyResult, swimmer.getBestTrainingResult(Stroke.BUTTERFLY));
    }

}
