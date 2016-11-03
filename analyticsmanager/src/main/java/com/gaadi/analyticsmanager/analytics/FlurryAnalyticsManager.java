package com.gaadi.analyticsmanager.analytics;

import android.content.Context;

import com.flurry.android.FlurryAgent;
import com.gaadi.analyticsmanager.analytics.interfaces.IAnalytics;

/**
 * Created by Pavan on 28/9/16.
 */
public class FlurryAnalyticsManager implements IAnalytics {

    private static FlurryAnalyticsManager flurryAnalyticsManager;
    private FlurryAnalyticsManager(){

    }

    public static synchronized FlurryAnalyticsManager getInstance(){
        if(flurryAnalyticsManager==null){
            flurryAnalyticsManager=new FlurryAnalyticsManager();
        }

        return flurryAnalyticsManager;

    }
    public void initFlurry(Context context, String flurryAPIKey){
        FlurryAgent.setLogEnabled(true);
        // init Flurry
        FlurryAgent.init(context, flurryAPIKey);
    }
    public void startSession(Context context, String flurryAPIKey) {
        FlurryAgent.onStartSession(context, flurryAPIKey);
    }
    public void endSession(Context context) {
        FlurryAgent.onEndSession(context);
    }

    @Override
    public void sendEvent(String event) {
        FlurryAgent.logEvent(event, Boolean.TRUE);
    }

    @Override
    public void sendScreenName(String screenName) {

    }
}