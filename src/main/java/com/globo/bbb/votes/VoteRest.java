package com.globo.bbb.votes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class VoteRest {

    @Autowired()
    private VoteService service;

    @RequestMapping(value = "/vote/particip/1", method = RequestMethod.POST)
    public PercentualVotes voteParticip1() {
        service.voteParticip1();
        return service.getVotes().getPercentualVotes();
    }

    @RequestMapping(value = "/vote/particip/2", method = RequestMethod.POST)
    public PercentualVotes voteParticip2() {
        service.voteParticip2();
        return service.getVotes().getPercentualVotes();
    }

    @RequestMapping(value = "/vote/resume", method = RequestMethod.GET)
    public AllVotes getResume() {
        return service.getVotes();
    }
}
