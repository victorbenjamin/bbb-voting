package com.globo.bbb;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class VoteGroup {

    private long particip1;
    private long particip2;
    private Instant hour;

    public VoteGroup(long particip1, long particip2, Instant now) {
        this.particip1 = particip1;
        this.particip2 = particip2;
        this.hour = Instant.now().truncatedTo(ChronoUnit.HOURS);
    }

    public long getParticip1() {
        return particip1;
    }

    public long getParticip2() {
        return particip2;
    }
}
