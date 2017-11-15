package com.globo.bbb;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rx.schedulers.TestScheduler;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BbbApplicationTests {

	@Test
	public void testZeroVotes() {
        VoteService service = new VoteService();
		assertEquals(0, service.getVotesParticip1());
        assertEquals(0, service.getVotesParticip2());
	}

    @Test
    public void testOneVotes() {
        TestScheduler scheduler = new TestScheduler();
        VoteService service = new VoteService(scheduler);
        service.voteParticip1();
        service.voteParticip1();
        service.voteParticip2();
        scheduler.advanceTimeBy(1200, TimeUnit.MILLISECONDS);
        assertEquals(2, service.getVotesParticip1());
        assertEquals(1, service.getVotesParticip2());
    }

}
