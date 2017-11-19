package com.globo.bbb.com.globo.bbb.votes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class VoteRest {

    @Autowired()
    private VoteService service;

    @RequestMapping(value = "/vote/particip/1", method = RequestMethod.POST)
    public Object voteParticip1() {
        service.voteParticip1();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/vote/particip/2", method = RequestMethod.POST)
    public Object voteParticip2() {
        service.voteParticip2();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
