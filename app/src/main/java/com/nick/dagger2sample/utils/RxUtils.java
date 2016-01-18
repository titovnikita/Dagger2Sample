package com.nick.dagger2sample.utils;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class RxUtils {

    public static void unsubscribe(Subscription subscription) {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    public static void unsubscribe(CompositeSubscription subscriptions) {
        if (subscriptions != null && subscriptions.hasSubscriptions()) {
            subscriptions.unsubscribe();
        }
    }

    public static CompositeSubscription restoreSubscriptions(CompositeSubscription subscriptions) {
        if (subscriptions == null || subscriptions.isUnsubscribed()) {
            return new CompositeSubscription();
        }
        return subscriptions;
    }
}
