package makkajai.generator;

import makkajai.model.Event;

import java.time.LocalTime;
import java.util.List;

public class GenerateEvents {
    public static List<Event> getEventList() {
        Event morningSession = Event.builder()
                .name("Morning Session")
                .startTime(LocalTime.of(9, 0))
                .endTime(LocalTime.of(12, 0))
                .build();

        Event afternoonSession = Event.builder()
                .name("Afternoon Session")
                .startTime(LocalTime.of(13, 0))
                .endTime(LocalTime.of(16, 0))
                .endTimeWindowInMins(60)
                .build();

        return List.of(morningSession, afternoonSession);
    }
}
