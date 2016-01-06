package com.example.egidijusgecius.demo_leakcanary;

/**
 * Custom Application class for release builds
 */
public class MyApp extends SharedApp {

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	void trackForMemoryLeaks() {
		//do nothing in release builds
	}

}
