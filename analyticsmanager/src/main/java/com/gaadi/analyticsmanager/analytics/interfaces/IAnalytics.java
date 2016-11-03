package com.gaadi.analyticsmanager.analytics.interfaces;

/**
 * Created by Pavan on 13/8/16.
 */
public interface IAnalytics {

    int FIREBASE=1;
    int FLURRY=2;

    void sendScreenName(String screenName);
    void sendEvent(String event);

}
