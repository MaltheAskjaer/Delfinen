package domain;

import java.time.LocalDate;

public class TrainingResult {
    private long timeInSeconds;
    private LocalDate date;
    private Stroke stroke;

    public TrainingResult(long timeInSeconds, LocalDate date, Stroke stroke) {
        if (timeInSeconds <= 0)
        {
            throw new IllegalArgumentException("Tiden skal være større end 0.");
        }
        if (date == null) {
            throw new IllegalArgumentException("Datoen mangler.");
        }
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Datoen må ikke være i fremtiden.");
        }
        if (stroke == null) {
            throw new IllegalArgumentException("Disciplinen mangler.");
        }

        this.timeInSeconds = timeInSeconds;
        this.date = date;
        this.stroke = stroke;
    }

    public long getTimeInSeconds() {
        return timeInSeconds;
    }

    public LocalDate getDate() {
        return date;
    }

    public Stroke getStroke() {
        return stroke;
    }
}
