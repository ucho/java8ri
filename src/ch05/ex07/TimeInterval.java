package ch05.ex07;

import java.time.LocalDateTime;

public class TimeInterval {

    private LocalDateTime start;
    private LocalDateTime end;

    public TimeInterval(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            throw new NullPointerException();
        }
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("start is after end.");
        }
        this.start = start;
        this.end = end;
    }

    public boolean conflictingWith(TimeInterval other) {
        if (other == null) {
            throw new NullPointerException();
        }
        if (other.start.isBefore(start) || other.start.isEqual(start)) {
            return other.end.isAfter(start);
        }
        return other.start.isAfter(start) && other.start.isBefore(end);
    }
}
