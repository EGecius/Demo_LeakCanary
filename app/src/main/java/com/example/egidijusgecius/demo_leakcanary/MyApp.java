package com.example.egidijusgecius.demo_leakcanary;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class MyApp extends Application {

	private List<Object> leakedObjects = new ArrayList<>();

	@Override
	public void onCreate() {
		super.onCreate();
	}

	/** Used to leak objects to test how LeakCanary works */
	public void leakObject(Object object) {
		leakedObjects.add(object);
	}

}
