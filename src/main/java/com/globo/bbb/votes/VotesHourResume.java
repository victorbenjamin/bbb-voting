package com.globo.bbb.votes;

public class VotesHourResume implements Comparable<VotesHourResume> {

    private long total;
    private String hour;

    VotesHourResume(VotesHour votes) {
        this.hour = votes.getHour().toString();
        this.total = votes.getParticip1() + votes.getParticip2();
    }

    public String getHour() {
        return hour;
    }

    public long getTotal() {
        return this.total;
    }


    @Override
    public int compareTo(VotesHourResume o) {
        return this.hour.compareTo(o.hour);
    }
}
