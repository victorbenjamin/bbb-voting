package com.globo.bbb.votes;

import java.util.Collection;
import java.util.Collections;

public interface VotePersistence {

    Collection<VotesHour> EMPTY_VOTES = Collections.singleton(VotesHour.NO_VOTES);

    void persist(VotesHour group);

    Collection<VotesHour> all();

}
