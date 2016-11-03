package com.gaadi.analyticsmanager.analytics;

import android.content.Context;
import android.os.Bundle;

import com.gaadi.analyticsmanager.analytics.interfaces.IAnalytics;
import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by Pavan on 28/9/16.
 */
public class FirebaseAnalyticsHelper implements IAnalytics {

    private static FirebaseAnalyticsHelper firebaseAnalyticsHelper;
    FirebaseAnalytics firebaseAnalytics;
    private FirebaseAnalyticsHelper(){

    }

    public static synchronized FirebaseAnalyticsHelper getInstance(Context context){
        if(firebaseAnalyticsHelper == null){
            firebaseAnalyticsHelper = new FirebaseAnalyticsHelper();
        }
        firebaseAnalyticsHelper.firebaseAnalytics = initFireBase(context);
        return firebaseAnalyticsHelper;

    }
    public static FirebaseAnalytics initFireBase(Context context) {
        return FirebaseAnalytics.getInstance(context);
    }

    @Override
    public void sendEvent(String event) {
        Bundle bundle = new Bundle();
       /* bundle.putString("Button_click_123", event);
        firebaseAnalytics.logEvent("EVENT_BUTTON_CLICK", bundle);*/
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, event);
        //Logs an app event.
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);

    }

    @Override
    public void sendScreenName(String screenName) {
        
    }
}
