package domain;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public enum Stroke {
    BUTTERFLY("Butterfly"),
    FRONTCRAWL("Crawl"),
    BACKSTROKE("Rygcrawl"),
    BREASTSTROKE("Brystsvømning");

    private final String displayName;

    Stroke(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
