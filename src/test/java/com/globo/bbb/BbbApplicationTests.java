package com.globo.bbb;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rx.schedulers.TestScheduler;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BbbApplicationTests {

	@Test
	public void testZeroVotes() {
        VoteService service = new VoteService();
		assertEquals(0, service.getVotesParticipant1());
        assertEquals(0, service.getVotesParticipant2());
	}

    @Test
    public void testOneVotes() {
        TestScheduler scheduler = new TestScheduler();
        VoteService service = new VoteService(scheduler);
        service.voteParcticipant1();
        service.voteParcticipant1();
        service.voteParcticipant2();
        scheduler.advanceTimeBy(1200, TimeUnit.MILLISECONDS);
        assertEquals(2, service.getVotesParticipant1());
        assertEquals(1, service.getVotesParticipant2());
    }

}
