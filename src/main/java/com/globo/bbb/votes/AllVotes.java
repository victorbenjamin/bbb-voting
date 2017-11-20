package com.globo.bbb.votes;

import java.util.Collection;

class AllVotes {

    static AllVotes EMPTY_VOTES = new AllVotes(VotePersistence.EMPTY_VOTES);

    private long particip2;
    private long particip1;
    private PercentualVotes percentualVotes;
    private long total;
    private Object votesPerHour;

    AllVotes(Collection<VotesHour> votes) {
        final long p1 = votes.stream().mapToLong(VotesHour::getParticip1).sum();
        final long p2 = votes.stream().mapToLong(VotesHour::getParticip2).sum();
        this.percentualVotes = new PercentualVotes(p1, p2);
        this.votesPerHour = votes.stream().map(VotesHourResume::new).toArray();
        this.total = p1 + p2;
        this.particip1 = p1;
        this.particip2 = p2;

    }

    public PercentualVotes getPercentualVotes() {
        return percentualVotes;
    }

    public long getParticip1() {
        return particip1;
    }

    public long getParticip2() {
        return particip2;
    }

    public Object getVotesPerHour() {
        return votesPerHour;
    }

    public long getTotal() {
        return total;
    }
}
