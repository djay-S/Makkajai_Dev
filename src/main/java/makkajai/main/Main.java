package makkajai.main;

import makkajai.model.Event;
import makkajai.model.Topic;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Topic> topicList = getTopicList1();
        List<Event> eventList = getEventList();

        topicList.sort(Comparator.comparingInt(Topic::getDurationInMins));

        List<Topic> topics = new ArrayList<>(topicList);
        List<Topic> scheduledTopic = scheduleEvent(topics, eventList);
        printSchedule("Track 1", scheduledTopic);

        topicList.sort((t1, t2) -> t2.getDurationInMins() - t1.getDurationInMins());
        topics = new ArrayList<>(topicList);
        scheduledTopic = scheduleEvent(topics, eventList);

        printSchedule("Track 2", scheduledTopic);
    }


    private static void printSchedule(String track, List<Topic> topicList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(track).append("\n");

        for (Topic topic : topicList) {
            boolean isAfter12 = topic.getEndTime().compareTo(LocalTime.of(12, 0)) >= 0;
            boolean isBefore13 = topic.getStartTime().compareTo(LocalTime.of(13, 0)) <= 0;
            if (isAfter12 && isBefore13) {
                stringBuilder.append("12:00 ")
                        .append("Lunch\n");
            }
            stringBuilder.append(topic.getStartTime())
                    .append(" ")
                    .append(topic.getTopicName())
                    .append(" - ")
                    .append(topic.getDurationInMins())
                    .append("mins")
                    .append("\n");
        }

        stringBuilder
                .append(topicList.get(topicList.size() - 1).getEndTime())
                .append(" ")
                .append("Networking Event");

        System.out.println(stringBuilder);
    }

    private static List<Topic> scheduleEvent(List<Topic> topicList, List<Event> eventList) {
        List<Topic> schedulesTopics = new ArrayList<>();

        for (Event event : eventList) {
            long eventDurationInMins = -1;
            Duration eventDuration = Duration.between(event.getStartTime(), event.getEndTime());
            if ("Morning Session".equalsIgnoreCase(event.getName())) {
                eventDurationInMins = eventDuration.toMinutes();
            } else {
                eventDurationInMins = eventDuration.toMinutes() + event.getEndTimeWindowInMins();
            }
            ArrayList<Topic> resultantTopics = new ArrayList<>();
            fillSlot(eventDurationInMins, topicList, resultantTopics);
            assignTopicSlots(event.getStartTime(), resultantTopics, schedulesTopics);
        }

        return schedulesTopics;
    }

    private static void assignTopicSlots(LocalTime startTime, ArrayList<Topic> resultantTopics, List<Topic> schedulesTopics) {
        for (Topic topic : resultantTopics) {
            topic.setStartTime(startTime);
            startTime = startTime.plus(topic.getDurationInMins(), ChronoUnit.MINUTES);

            schedulesTopics.add(topic);
        }
    }

    private static void fillSlot(long duration, List<Topic> givenTopics, List<Topic> resultantTopics) {
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

    private static List<Event> getEventList() {
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

    private static List<Topic> getTopicList1() {
        return new ArrayList<>(
                List.of(
                        new Topic("Writing Fast Tests Against Enterprise Rails ", 60),
                        new Topic("Overdoing it in Python", 45),
                        new Topic("Lua for the Masses", 30),
                        new Topic("Ruby Errors from Mismatched Gem Versions", 45),
                        new Topic("Common Ruby Errors", 45),
                        new Topic("Rails for Python Developers lightning Communicating Over Distance", 60),
                        new Topic("Accounting-Driven Development", 45),
                        new Topic("Woah", 30),
                        new Topic("Sit Down and Write", 30),
                        new Topic("Pair Programming vs Noise", 45),
                        new Topic("Rails Magic", 60),
                        new Topic("Ruby on Rails: Why We Should Move On", 60),
                        new Topic("Clojure Ate Scala (on my project)", 45),
                        new Topic("Programming in the Boondocks of Seattle", 30),
                        new Topic("Ruby vs. Clojure for Back-End Development", 30),
                        new Topic("Ruby on Rails Legacy App Maintenance", 60),
                        new Topic("A World Without HackerNews", 30),
                        new Topic("User Interface CSS in Rails Apps", 30)
                )
        );
    }
}
