package com.example.demo;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.example.demo.utility.AppUtil;

/**
 * A) For mocking static methods, PowerMock provides two approaches: 1) Use
 * PowerMockito.mockStatic() to a mock a static class or all the static methods
 * in a class. 2) Use PowerMockito.spy() to mock a specific static method.
 *
 * B) To verify the static method invocation, first, call
 * PowerMockito.verifyStatic(Static.class) to start verifying behavior and then
 * call the actual static method to verify. This is important to note that we
 * need to call verifyStatic() per static method verification.
 * 
 */

/*
 * or AppUtilTests spy = PowerMockito.spy(new AppUtilTests());
 * PowerMockito.when(spy.staticMessage()).thenReturn("static method called");
 * 
 * PowerMockito.verifyStatic(AppUtilTests.class);
 * PowerMockito.verifyStatic(AppUtilTests.class, Mockito.times(1));
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ AppUtil.class })
public class PowerMockTests {

	@SuppressWarnings("static-access")
	@Test
	public void mockStaticMethodTest() {
		PowerMockito.mockStatic(AppUtil.class);
		// Set expectation
		when(AppUtil.staticMessage()).thenReturn("static method called");

		// invoke the method
		String message = AppUtil.staticMessage();

		// Assert the stub response
		Assert.assertEquals(message, "static method called");
		//
		AppUtil spy = PowerMockito.spy(new AppUtil());
		PowerMockito.when(spy.staticMessage()).thenReturn("static method called");
	}

	@Test
	public void mockFinalMethodTest() {
		// Mock final method
		AppUtil serviceMock = PowerMockito.mock(AppUtil.class,
				Mockito.withSettings().name("ServiceMock").verboseLogging());

		// Set expectation
		Mockito.when(serviceMock.finalMessage()).thenReturn("New Message from " + "Mock!");

		// invoke the method
		String message = serviceMock.finalMessage();

		// Assert the stub response
		Assert.assertEquals(message, "New Message from Mock!");

		// Verify final method invocation
		Mockito.verify(serviceMock).finalMessage();
	}

	@Test
	public void mockPrivateMethodTest() throws Exception {
		AppUtil mock = PowerMockito.spy(new AppUtil());
		PowerMockito.doReturn("New Message from Mock!").when(mock, "privateMessage");
		String privateMessage = Whitebox.invokeMethod(mock, "privateMessage");
		Assert.assertEquals(privateMessage, "New Message from Mock!");
		PowerMockito.verifyPrivate(mock, times(1)).invoke("privateMessage");
	}

}
