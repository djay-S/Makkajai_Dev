package makkajai.generator;

import makkajai.model.Topic;

import java.util.ArrayList;
import java.util.List;

public class GenerateTopics {
    public static List<Topic> getTopicList1() {
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
