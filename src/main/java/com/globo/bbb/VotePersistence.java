package com.globo.bbb;

import java.util.Collection;

public interface VotePersistence {

    void persist(VotesHour group);

    Collection<VotesHour> all();

}
