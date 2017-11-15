package com.globo.bbb;

import rx.Observable;
import rx.Scheduler;

import static rx.observables.JoinObservable.*;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class VoteService {

    private Subject<Boolean, Boolean> participant1;
    private Subject<Boolean, Boolean> participant2;
    private Long count1 = 0L;
    private Long count2 = 0L;

    public VoteService() {
        this(Schedulers.computation());
    }

    public VoteService(Scheduler scheduler) {
        this.participant1 = PublishSubject.create();
        Observable<List<Boolean>> test1 = this.participant1.buffer(1, TimeUnit.SECONDS, scheduler);
        this.participant2 = PublishSubject.create();

        Observable<List<Boolean>> test2 = this.participant2.buffer(1, TimeUnit.SECONDS, scheduler);
        when(from(test1).and(test2)
                .then((p1, p2) -> new VoteGroup(p1.size(), p2.size(), Instant.now())))
                .toObservable().subscribe(g -> {
                    this.count1 += g.getParticip1();
                    this.count2 += g.getParticip2();

        });
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
