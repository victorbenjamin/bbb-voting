package com.globo.bbb.votes;

import com.globo.bbb.votes.VotePersistence;
import com.globo.bbb.votes.VotesHour;

import java.time.temporal.Temporal;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StubPersistence implements VotePersistence {

    private Map<Temporal, VotesHour> votesMap = new ConcurrentHashMap<>();


    @Override
    public void persist(VotesHour votes) {
        VotesHour v;
        if ((v = votesMap.get(votes.getHour())) == null) {
            votesMap.put(votes.getHour(), votes);
        } else {
            votesMap.put(votes.getHour(), votes.plus(v));
        }

    }

    @Override
    public Collection<VotesHour> all() {
        return votesMap.values();
    }
}
