package com.globo.bbb;

import org.junit.Before;
import org.junit.Test;
import rx.schedulers.TestScheduler;

import java.util.Collection;
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
        this.service = new VoteService(this.persistence, this.scheduler);
    }

	@Test
	public void test1ZeroVotes() {
		assertEquals(0, this.service.getVotes().size());
	}

    @Test
    public void test2SomeVotes() {
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

    @Test
    public void test2ALoteVotes() {
        final VoteService service = new VoteService(this.persistence, this.scheduler, 100);
        IntStream.range(0, 2_500_000).forEach(i -> {
            service.voteParticip1();
            service.voteParticip2();
        });
        scheduler.advanceTimeBy(1000, TimeUnit.MILLISECONDS);
        Collection<VotesHour> votes = service.getVotes();
        long particip1 = votes.stream().mapToLong(VotesHour::getParticip1).sum();
        long particip2 = votes.stream().mapToLong(VotesHour::getParticip2).sum();
        verify(this.persistence, times(11)).persist(any(VotesHour.class));
        assertEquals(2_500_000, particip1);
        assertEquals(2_500_000, particip2);
    }

}
