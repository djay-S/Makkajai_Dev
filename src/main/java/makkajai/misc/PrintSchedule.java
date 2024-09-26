package makkajai.misc;

import makkajai.model.Topic;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class PrintSchedule {
    public static void printSchedule(String track, List<Topic> topicList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(track).append("\n");

        for (Topic topic : topicList) {
            boolean isAfter12 = topic.getEndTime().compareTo(LocalTime.of(12, 0)) >= 0;
            boolean isBefore13 = topic.getStartTime().compareTo(LocalTime.of(13, 0)) < 0;

            stringBuilder.append(topic.getStartTime())
                    .append(" ")
                    .append(topic.getTopicName())
                    .append(" - ")
                    .append(topic.getDurationInMins())
                    .append("mins")
                    .append("\n");

            if (isAfter12 && isBefore13) {
                stringBuilder.append("12:00 ")
                        .append("Lunch\n");
            }
        }

        Topic lastTopic = topicList.get(topicList.size() - 1);
        LocalTime endTime = lastTopic.getStartTime().plus(lastTopic.getDurationInMins(), ChronoUnit.MINUTES);
        stringBuilder
                .append(endTime)
                .append(" ")
                .append("Networking Event\n");

        System.out.println(stringBuilder);
    }
}
