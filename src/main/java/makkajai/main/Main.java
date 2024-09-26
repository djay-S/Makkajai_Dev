package makkajai.main;

import makkajai.generator.GenerateEvents;
import makkajai.generator.GenerateTopics;
import makkajai.generator.GenerateTracks;
import makkajai.misc.PrintSchedule;
import makkajai.model.Event;
import makkajai.model.Topic;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        generateTracks();
    }

    private static void generateTracks() {
        List<Topic> topicList = GenerateTopics.getTopicList1();
        List<Event> eventList = GenerateEvents.getEventList();

        List<Topic> track1TopicSchedule = GenerateTracks.generateTrack1(topicList, eventList);
        PrintSchedule.printSchedule("Track 1", track1TopicSchedule);

        topicList = GenerateTopics.getTopicList1();

        List<Topic> track2TopicSchedule = GenerateTracks.generateTrack2(topicList, eventList);
        PrintSchedule.printSchedule("Track 2", track2TopicSchedule);

    }

}
