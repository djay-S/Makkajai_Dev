package makkajai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    private String topicName;
    private int durationInMins;
    private LocalTime startTime;
    private LocalTime endTime;

    public Topic(String topicName, int durationInMins) {
        this.topicName = topicName;
        this.durationInMins = durationInMins;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
        if (this.endTime == null) {
            this.endTime = this.startTime.plus(durationInMins, ChronoUnit.MINUTES);
        }
    }
}
