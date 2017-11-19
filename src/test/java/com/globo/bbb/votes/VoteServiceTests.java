package com.globo.bbb.votes;

import org.junit.Before;
import org.junit.Test;
import rx.schedulers.TestScheduler;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class VoteServiceTests {

    private VoteService service;
    private TestScheduler scheduler;
    private VotePersistence persistence;

    @Before
    public void before() {
        this.persistence = spy(new StubPersistence());
        this.scheduler = new TestScheduler();
        this.service = new VoteService(this.persistence, 1000, this.scheduler);
    }

	@Test
	public void test1ZeroVotes() {
		assertEquals(0, this.service.getVotes().getTotal());
	}

    @Test
    public void test2SomeVotes() {
        service.voteParticip1();
        service.voteParticip1();
        service.voteParticip2();
        scheduler.advanceTimeBy(2000, TimeUnit.MILLISECONDS);
        AllVotes votes = service.getVotes();

        assertEquals(2, votes.getParticip1());
        assertEquals(1, votes.getParticip2());
    }

    @Test
    public void test2ALoteVotes() {
        final VoteService service = new VoteService(this.persistence, 100, this.scheduler);
        IntStream.range(0, 2_500_000).forEach(i -> {
            service.voteParticip1();
            service.voteParticip2();
        });
        scheduler.advanceTimeBy(1050, TimeUnit.MILLISECONDS);
        AllVotes votes = service.getVotes();
        verify(this.persistence, times(11)).persist(any(VotesHour.class));
        assertEquals(2_500_000, votes.getParticip1());
        assertEquals(2_500_000, votes.getParticip2());
    }

}
