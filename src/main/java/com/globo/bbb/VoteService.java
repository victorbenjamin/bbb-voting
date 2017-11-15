package com.globo.bbb;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

import java.util.concurrent.TimeUnit;

public class VoteService {

    private Subject<Boolean> participant1;
    private Subject<Boolean> participant2;
    private Long count1 = 0L;
    private Long count2 = 0L;

    public VoteService() {
        this(Schedulers.computation());
    }

    public VoteService(Scheduler scheduler) {
        this.participant1 = PublishSubject.create();
        this.participant1.buffer(1, TimeUnit.SECONDS, scheduler).subscribe(c -> this.count1 += c.size());
        this.participant2 = PublishSubject.create();
        this.participant2.buffer(1, TimeUnit.SECONDS, scheduler).subscribe(c -> this.count2 += c.size());
    }

    public void voteParcticipant1() {
        participant1.onNext(true);
    }

    public void voteParcticipant2() {
        participant2.onNext(true);
    }

    public long getVotesParticipant1() {
        return this.count1;
    }

    public long getVotesParticipant2() {
        return this.count2;
    }

}
