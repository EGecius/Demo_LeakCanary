package com.example.egidijusgecius.demo_leakcanary;

import android.app.Activity;
import android.app.Application;

import com.squareup.leakcanary.AndroidExcludedRefs;
import com.squareup.leakcanary.DisplayLeakService;
import com.squareup.leakcanary.ExcludedRefs;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.squareup.leakcanary.ServiceHeapDumpListener;

/**
 * Single Responsibility:
 *
 * A wrapper around LeakCanary for an easy-to-use api to configure it.
 */
public class LeakCanaryWrapper {

	private final Application app;

    /**
     * @param app application object
     */
	public LeakCanaryWrapper(Application app) {
		this.app = app;
	}

    /** Installs LeakCanary with default settings */
	public RefWatcher install() {
		return LeakCanary.install(app);
	}

    /**
     * Installs LeakCanary with a list of Activities that will be excluded from tracking
     * @param activities Activities that should not be tracked for memory leaks
     * @return object that should be added references to
     */
    public RefWatcher installWithExclusions(final Class... activities) {

        ExcludedRefs excludedRefs = AndroidExcludedRefs.createAppDefaults().build();
        LeakCanary.enableDisplayLeakActivity(app);
        ServiceHeapDumpListener heapDumpListener = new ServiceHeapDumpListener(app, DisplayLeakService.class);
        final RefWatcher refWatcher =  LeakCanary.androidWatcher(app, heapDumpListener, excludedRefs);

        app.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacksAdapter() {
            @Override
            public void onActivityDestroyed(Activity activity) {
                for (Class aClass : activities) {
                    if (activity.getClass().equals(aClass)) {
                        return;
                    }
                }
                refWatcher.watch(activity);
            }
        });

        return refWatcher;
    }

}
