package com.globo.bbb.com.globo.bbb.votes;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

class VotesHour {

    private long particip1;
    private long particip2;
    private Instant hour;

    VotesHour(long particip1, long particip2) {
        this(particip1, particip2, Instant.now().truncatedTo(ChronoUnit.HOURS));
    }

    private VotesHour(long particip1, long particip2, Instant hour) {
        this.particip1 = particip1;
        this.particip2 = particip2;
        this.hour = hour;
    }

    long getParticip1() {
        return particip1;
    }

    long getParticip2() {
        return particip2;
    }

    Instant getHour() {
        return hour;
    }

    VotesHour plus(VotesHour votes) {
        if (!votes.hour.equals(this.hour)) throw new IllegalArgumentException();
        return new VotesHour(this.particip1 + votes.getParticip1(),
                this.particip2 + votes.particip2, this.hour);
    }
}
