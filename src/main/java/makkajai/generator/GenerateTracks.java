package makkajai.generator;

import makkajai.model.Event;
import makkajai.model.Topic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GenerateTracks {
    public static List<Topic> generateTrack1(List<Topic> topicList, List<Event> eventList) {
        topicList.sort(Comparator.comparingInt(Topic::getDurationInMins));
        List<Topic> scheduledTopics = scheduleEvent(topicList, eventList);
        return scheduledTopics;
    }

    public static List<Topic> generateTrack2(List<Topic> topicList, List<Event> eventList) {
        topicList.sort((t1, t2) -> t2.getDurationInMins() - t1.getDurationInMins());
        List<Topic> scheduledTopics = scheduleEvent(topicList, eventList);
        return scheduledTopics;
    }

    private static List<Topic> scheduleEvent(List<Topic> topicList, List<Event> eventList) {
        List<Topic> scheduledTopics = new ArrayList<>();

        for (Event event : eventList) {
            List<Topic> eventTopics = event.scheduleEvent(topicList);
            scheduledTopics.addAll(eventTopics);
        }
        return scheduledTopics;
    }
}
