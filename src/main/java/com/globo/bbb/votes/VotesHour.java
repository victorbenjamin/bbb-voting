package com.globo.bbb.votes;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Document(collection = "VotesHour")
public class VotesHour implements Comparable<VotesHour> {

    public static VotesHour NO_VOTES = new VotesHour(0,0);

    @Id
    private LocalDateTime hour;


    private long particip1;
    private long particip2;

    public VotesHour(long particip1, long particip2) {
        this(particip1, particip2, LocalDateTime.now().truncatedTo(ChronoUnit.HOURS));
    }

    private VotesHour(long particip1, long particip2, LocalDateTime hour) {
        this.particip1 = particip1;
        this.particip2 = particip2;
        this.hour = hour;
    }

    private VotesHour() {}

    public long getParticip1() {
        return particip1;
    }

    public long getParticip2() {
        return particip2;
    }

    public LocalDateTime getHour() {
        return hour;
    }

    public long getTotal() {
        return this.particip1 + this.particip2;
    }

    public boolean isEmpty() {
        return this.getTotal() == 0;
    }

    VotesHour plus(VotesHour votes) {
        if (!votes.hour.equals(this.hour)) throw new IllegalArgumentException();
        return new VotesHour(this.particip1 + votes.getParticip1(),
                this.particip2 + votes.particip2, this.hour);
    }

    VotesHourResume getResume() {
        return new VotesHourResume(this);
    }

    @Override
    public int compareTo(VotesHour o) {
        return this.hour.compareTo(o.hour);
    }
}
