package com.example.egidijusgecius.demo_leakcanary;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Single Responsibility:
 *
 * Test for {@link LeakCanaryWrapper}
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(LeakCanary.class)
public class LeakCanaryWrapperTest {

	@Mock Application app;

	LeakCanaryWrapper wrapper;

	@Before
	public void setup() {
		wrapper = new LeakCanaryWrapper(app);
	}

	@Test
	public void when_installCalled_thenLeakCanaryIsInstalled() {

		//prepare to verify static methods
		PowerMockito.mockStatic(LeakCanary.class);

		//WHEN
		wrapper.install();

		//THEN - verify LeakCanary.install() has been called
		PowerMockito.verifyStatic();
		LeakCanary.install(app);
	}
}
