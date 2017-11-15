package com.globo.bbb;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rx.schedulers.TestScheduler;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BbbApplicationTests {

    private VoteService service;
    private TestScheduler scheduler;

    @Before
    public void before() {
        this.scheduler = new TestScheduler();
        this.service = new VoteService(new StubPersistence(), this.scheduler);
    }

	@Test
	public void testZeroVotes() {
		assertEquals(0, this.service.getVotes().size());
	}

    @Test
    public void testOneVotes() {
        service.voteParticip1();
        service.voteParticip1();
        service.voteParticip2();
        scheduler.advanceTimeBy(1200, TimeUnit.MILLISECONDS);
        Collection<VotesHour> votes = service.getVotes();
        long particip1 = votes.stream().mapToLong(VotesHour::getParticip1).sum();
        long particip2 = votes.stream().mapToLong(VotesHour::getParticip2).sum();

        assertEquals(2, particip1);
        assertEquals(1, particip2);
    }

}
