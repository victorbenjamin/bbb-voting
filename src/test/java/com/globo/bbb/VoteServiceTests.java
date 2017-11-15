package com.globo.bbb;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rx.schedulers.TestScheduler;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
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
        IntStream.range(0, 1_000_000).forEach(i -> {
            service.voteParticip1();
            service.voteParticip2();
        });
        scheduler.advanceTimeBy(1200, TimeUnit.MILLISECONDS);
        Collection<VotesHour> votes = service.getVotes();
        long particip1 = votes.stream().mapToLong(VotesHour::getParticip1).sum();
        long particip2 = votes.stream().mapToLong(VotesHour::getParticip2).sum();
        verify(this.persistence, times(1)).persist(any(VotesHour.class));
        assertEquals(1_000_000, particip1);
        assertEquals(1_000_000, particip2);
    }

}
