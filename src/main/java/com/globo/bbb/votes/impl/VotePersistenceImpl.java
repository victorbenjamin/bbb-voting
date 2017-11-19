package com.globo.bbb.votes.impl;

import com.globo.bbb.votes.VotePersistence;
import com.globo.bbb.votes.VotesHour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Repository
class VotePersistenceImpl implements VotePersistence {

    private static Collection<VotesHour> EMPTY_VOTES = Collections.singleton(VotesHour.NO_VOTES);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void persist(VotesHour votes) {
        if (votes.isEmpty()) return;
        final Query query = new Query(Criteria.where("_id").is(votes.getHour()));
        final Update update = new Update();
        update.inc("particip1", votes.getParticip1());
        update.inc("particip2", votes.getParticip2());
        this.mongoTemplate.upsert(query, update, VotesHour.class);
    }

    @Override
    public Collection<VotesHour> all() {
        final List<VotesHour> votes = this.mongoTemplate.findAll(VotesHour.class);
        if (votes.isEmpty()) {
            return EMPTY_VOTES;
        } else {
            Collections.sort(votes);
            return votes;
        }
    }
}
