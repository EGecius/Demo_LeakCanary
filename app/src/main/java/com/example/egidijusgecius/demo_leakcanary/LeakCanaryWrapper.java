package com.example.egidijusgecius.demo_leakcanary;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Single Responsibility:
 *
 * A wrapper around LeakCanary for an easy-to-use api to configure it.
 */
public class LeakCanaryWrapper {

	Application app;

	public LeakCanaryWrapper(Application app) {
		this.app = app;
	}

	public void install() {
		LeakCanary.install(app);
	}
}
