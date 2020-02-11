package com.dicoding.intifada.myservice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService extends IntentService {

    public static String EXTRA_DURATION = "extra_duration";
    public static final String TAG = MyIntentService.class.getSimpleName();

    public MyIntentService() {
        super("MyIntentService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent: Mulai.....");
        if (intent != null) {
            long duration = intent.getLongExtra(EXTRA_DURATION, 0);
            try {
                Thread.sleep(duration);
                Log.d(TAG, "onHandleIntent: Selesai.....");
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
