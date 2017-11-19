package com.globo.bbb.com.globo.bbb.votes;

import java.util.Collection;

public interface VotePersistence {

    void persist(VotesHour group);

    Collection<VotesHour> all();

}
