package com.globo.bbb.votes;

public class VotesHourResume {

    private long particip1;
    private long particip2;
    private String hour;

    VotesHourResume(VotesHour votes) {
        this.hour = votes.getHour().toString();
        this.particip1 = votes.getParticip1();
        this.particip2 = votes.getParticip2();
    }

    public long getParticip1() {
        return particip1;
    }

    public long getParticip2() {
        return particip2;
    }

    public String getHour() {
        return hour;
    }
}
