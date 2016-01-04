package com.example.egidijusgecius.demo_leakcanary;

import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.pm.PackageManager;

import com.squareup.leakcanary.LeakCanary;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

		//prepare to verify static methods
		PowerMockito.mockStatic(LeakCanary.class);
		mockForLeakCanaryInternals();
	}


	private void mockForLeakCanaryInternals() {
		Context appContext = mock(Context.class);
		when(app.getApplicationContext()).thenReturn(appContext);

		PackageManager packageManager = mock(PackageManager.class);
		when(appContext.getPackageManager()).thenReturn(packageManager);
	}


	@Test
	public void when_installCalled_thenLeakCanaryIsInstalled() {

		//WHEN
		wrapper.install();

		//THEN - verify LeakCanary.install() has been called
		PowerMockito.verifyStatic();
		LeakCanary.install(app);
	}

	@Test
	public void when_installWithExclusionsCalled_then_enableDisplayLeakActivityCalled() {

		//WHEN
		Class[] noClasses = new Class[0];
		wrapper.installWithExclusions(noClasses);

		//THEN
		PowerMockito.verifyStatic();
		LeakCanary.enableDisplayLeakActivity((Context) any());
	}

	@Test
	public void when_installWithExclusionsCalled_then_registerActivityLifecycleCallbacksCalled() {

		//WHEN
		Class[] noClasses = new Class[0];
		wrapper.installWithExclusions(noClasses);

		//THEN
		verify(app).registerActivityLifecycleCallbacks((ActivityLifecycleCallbacks) any());
	}

}
