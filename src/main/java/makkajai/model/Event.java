package makkajai.model;

import lombok.Builder;
import lombok.Data;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Event {
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private int endTimeWindowInMins;

    public List<Topic> scheduleEvent (List<Topic> topicList) {
        List<Topic> schedulesTopics = new ArrayList<>();

        long eventDurationInMins = -1;
        Duration eventDuration = Duration.between(this.getStartTime(), this.getEndTime());
        if ("Morning Session".equalsIgnoreCase(this.getName())) {
            eventDurationInMins = eventDuration.toMinutes();
        } else {
            eventDurationInMins = eventDuration.toMinutes() + this.getEndTimeWindowInMins();
        }
        ArrayList<Topic> resultantTopics = new ArrayList<>();
        fillSlot(eventDurationInMins, topicList, resultantTopics);
        assignTopicSlots(this.getStartTime(), resultantTopics, schedulesTopics);

        return schedulesTopics;
    }

    private void fillSlot(long duration, List<Topic> givenTopics, List<Topic> resultantTopics) {
        long target = duration - givenTopics.get(0).getDurationInMins();
        resultantTopics.add(givenTopics.get(0));
        givenTopics.remove(0);

        if (target == 0) {
            return;
        }
        if (target < 0) {
            target = duration;
            resultantTopics.remove(resultantTopics.size() - 1);
        }
        if (givenTopics.isEmpty()) {
            return;
        }
        fillSlot(target, givenTopics, resultantTopics);
    }

    private void assignTopicSlots(LocalTime startTime, ArrayList<Topic> resultantTopics, List<Topic> schedulesTopics) {
        for (Topic topic : resultantTopics) {
            topic.setStartTime(startTime);
            startTime = startTime.plusMinutes(topic.getDurationInMins());

            schedulesTopics.add(topic);
        }
    }
}
