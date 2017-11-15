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

    private Subject<Boolean, Boolean> particip1;
    private Subject<Boolean, Boolean> particip2;
    private Long count1 = 0L;
    private Long count2 = 0L;

    public VoteService() {
        this(Schedulers.computation());
    }

    public VoteService(Scheduler scheduler) {
        this.particip1 = PublishSubject.create();
        this.particip2 = PublishSubject.create();

        when(
                from(this.timeBuffer(this.particip1, scheduler)
                ).and(this.timeBuffer(this.particip2, scheduler))
                .then((p1, p2) -> new VoteGroup(p1, p2, Instant.now()))
        ).toObservable().subscribe(g -> {
            this.count1 += g.getParticip1();
            this.count2 += g.getParticip2();

        });
    }

    private Observable<Integer> timeBuffer(Observable<Boolean> particip, Scheduler scheduler) {
        return particip.buffer(1, TimeUnit.SECONDS, scheduler).map(List::size);
    }

    public void voteParticip1() {
        particip1.onNext(true);
    }

    public void voteParticip2() {
        particip2.onNext(true);
    }

    public long getVotesParticip1() {
        return this.count1;
    }

    public long getVotesParticip2() {
        return this.count2;
    }

}
