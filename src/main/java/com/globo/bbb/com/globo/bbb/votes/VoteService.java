package com.globo.bbb.com.globo.bbb.votes;

import rx.Observable;
import rx.Scheduler;

import static rx.observables.JoinObservable.*;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

class VoteService {

    private Subject<Boolean, Boolean> particip1;
    private Subject<Boolean, Boolean> particip2;
    private VotePersistence persistence;

//    public VoteService(VotePersistence persistence) {
//        this(persistence, Schedulers.computation());
//    }

    VoteService(VotePersistence persistence, Scheduler scheduler) {
        this(persistence, scheduler, 1000);
    }

    VoteService(VotePersistence persistence, Scheduler scheduler, long timespan) {
        this.persistence = persistence;
        this.particip1 = this.createSubject();
        this.particip2 = this.createSubject();

        final Observable<Integer> pcp1 = this.timeBuffer(this.particip1, scheduler, timespan);
        final Observable<Integer> pcp2 = this.timeBuffer(this.particip2, scheduler, timespan);

        when(from(pcp1).and(pcp2).then(VotesHour::new))
                .toObservable()
                .subscribe(this.persistence::persist);
    }

    private Subject<Boolean, Boolean> createSubject() {
        final Subject<Boolean, Boolean> particip1 = PublishSubject.create();
        return particip1.toSerialized();
    }

    private Observable<Integer> timeBuffer(Observable<Boolean> particip, Scheduler scheduler, long timespan) {
        return particip.buffer(timespan, TimeUnit.MILLISECONDS, scheduler).map(List::size);
    }

    void voteParticip1() {
        particip1.onNext(true);
    }

    void voteParticip2() {
        particip2.onNext(true);
    }

    Collection<VotesHour> getVotes() {
        return persistence.all();
    }

}
