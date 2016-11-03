package com.gaadi.analyticsmanager.analytics;

import android.content.Context;

import com.gaadi.analyticsmanager.analytics.interfaces.IAnalytics;

/**
 * Created by Pavan on 13/8/16.
 */

public class AnalyticsManager implements IAnalytics {

    private static AnalyticsManager mInstance;
    private Context mContext;

    private FirebaseAnalyticsHelper firebaseAnalyticsHelper;
    private FlurryAnalyticsManager flurryAnalyticsManager;

    private int analyticsTypes[];
    private String mProjectID;

    private AnalyticsManager(Context context, String projectID, int ... analyticsTypes){
        this.mProjectID = projectID;
        this.mContext = context;
        this.analyticsTypes=new int[analyticsTypes.length];
        this.analyticsTypes=analyticsTypes;
        init();
    }
    /* call this method from the application class of your app to get an instance of
    * com.gaadi.analyticsmanager.analytics manager
    * */
    public synchronized static AnalyticsManager getInstance(Context context, String projectID , int ... analyticsTypes){
        if(mInstance == null)
            mInstance = new AnalyticsManager(context, projectID, analyticsTypes);

        return mInstance;

    }

    public void startSession() {
        if(flurryAnalyticsManager != null)
            flurryAnalyticsManager.startSession(mContext, mProjectID);
    }
    public void endSession() {
        if(flurryAnalyticsManager != null)
            flurryAnalyticsManager.endSession(mContext);
    }

    public void init() {

        for (int val:analyticsTypes) {

            switch (val){
                case IAnalytics.FIREBASE:
                    firebaseAnalyticsHelper = FirebaseAnalyticsHelper.getInstance(mContext);
                    break;
                case IAnalytics.FLURRY:
                    flurryAnalyticsManager = FlurryAnalyticsManager.getInstance();
                    flurryAnalyticsManager.initFlurry(mContext, mProjectID);
                    break;
            }
        }
    }

    @Override
    public void sendEvent(String event) {

        for (int val:analyticsTypes) {

            switch (val){
                case IAnalytics.FIREBASE:
                  firebaseAnalyticsHelper.sendEvent(event);
                    break;
                case IAnalytics.FLURRY:
                    flurryAnalyticsManager.sendEvent(event);
                    break;
            }
        }

    }

    @Override
    public void sendScreenName(String screenName) {

        for (int val:analyticsTypes) {

            switch (val){
                case IAnalytics.FIREBASE:
                    firebaseAnalyticsHelper.sendScreenName(screenName);
                    break;
                case IAnalytics.FLURRY:
                   firebaseAnalyticsHelper.sendScreenName(screenName);
                    break;
            }
        }

    }
}
