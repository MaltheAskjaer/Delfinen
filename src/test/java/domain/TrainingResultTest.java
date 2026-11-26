package domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TrainingResultTest
{
    @Test
    void constructorStoresTrainingResultDetails() {
        long timeInSeconds = 32;
        LocalDate date = LocalDate.now();
        Stroke stroke = Stroke.FRONTCRAWL;

        TrainingResult result = new TrainingResult(timeInSeconds, date, stroke);

        assertEquals(timeInSeconds, result.getTimeInSeconds());
        assertEquals(date, result.getDate());
        assertEquals(stroke, result.getStroke());
    }

    @Test
    void zeroOrNegativeTimeThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new TrainingResult(0, LocalDate.now(), Stroke.FRONTCRAWL));
        assertThrows(IllegalArgumentException.class,
                () -> new TrainingResult(-1, LocalDate.now(), Stroke.FRONTCRAWL));
    }

    @Test
    void missingOrFutureDateThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new TrainingResult(32, null, Stroke.FRONTCRAWL));
        assertThrows(IllegalArgumentException.class,
                () -> new TrainingResult(32, LocalDate.now().plusDays(1), Stroke.FRONTCRAWL));
    }

    @Test
    void missingStrokeThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new TrainingResult(32, LocalDate.now(), null));
    }
}
