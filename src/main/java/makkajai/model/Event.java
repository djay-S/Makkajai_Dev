package makkajai.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class Event {
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private int endTimeWindowInMins;
//    private List<Topic> sessionTopics;
}
