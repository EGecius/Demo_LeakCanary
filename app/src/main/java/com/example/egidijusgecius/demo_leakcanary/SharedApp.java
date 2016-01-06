package com.example.egidijusgecius.demo_leakcanary;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Single Responsibility:
 *
 * Share Application code for both debug & release builds
 */
public abstract class SharedApp extends Application {

	private List<Object> leakedObjects = new ArrayList<>();

	/** To track for memory leaks */
	abstract void trackForMemoryLeaks();

	/** Used to leak objects to test how LeakCanary works */
	public void leakObject(Object object) {
		leakedObjects.add(object);
	}

}
