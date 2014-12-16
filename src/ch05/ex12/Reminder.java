package ch05.ex12;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Reminder {

    private List<Promise> promises;

    public Reminder() {
        promises = new ArrayList<>();
    }

    public Reminder addPromise(String title, ZonedDateTime time) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("title is null or empty.");
        }
        if (time == null) {
            throw new IllegalArgumentException("time is null.");
        }

        promises.add(new Promise(title, time));
        return this;
    }

    public List<Notification> remind(ZonedDateTime current) {
        if (current == null) {
            throw new IllegalArgumentException("current is null.");
        }

        ZoneId currentZone = current.getZone();
        LocalDateTime currentTime = current.toLocalDateTime();
        return promises.stream().filter(promise -> {
            LocalDateTime promiseTime = promise.time.withZoneSameInstant(currentZone).toLocalDateTime();
            System.out.println("promiseTime: " + promiseTime);
            long minutes = Duration.between(currentTime, promiseTime).toMinutes();
            System.out.println("minutes: " + minutes);
            return 0 <= minutes && minutes <= 60;
        }).map(promise -> {
            LocalDateTime promiseTime = promise.time.withZoneSameInstant(currentZone).toLocalDateTime();
            long restMinutes = Duration.between(currentTime, promiseTime).toMinutes();
            Notification notification = new Notification(promise, restMinutes);
            return notification;
        }).collect(Collectors.toList());
    }
}

class Promise {
    String title;
    ZonedDateTime time;

    Promise(String title, ZonedDateTime time) {
        this.title = title;
        this.time = time;
    }
}

class Notification {
    Promise promise;
    long restMinutes;

    Notification(Promise promise, long restMinutes) {
        this.promise = promise;
        this.restMinutes = restMinutes;
    }
}