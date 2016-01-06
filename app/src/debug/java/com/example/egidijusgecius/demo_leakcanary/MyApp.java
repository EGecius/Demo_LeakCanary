package com.example.egidijusgecius.demo_leakcanary;

/**
 * Custom Application class for debug builds
 */
public class MyApp extends SharedApp {

	@Override
	protected void trackForMemoryLeaks() {
		new LeakCanaryWrapper(this).installWithExclusions(ChildActivity.class);
	}

}
