import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Demonstation how the JRE re-uses lambda implementation classes or instances
 * in different scenarios.
 */
public class LambdaInstancesTest {

	// If a non-capturing lambda is created multiple times at from the same code
	// the same instance is re-used.

	Runnable createLambda() {
		return LambdaInstancesTest::staticMethod;
	}

	@Test
	public void same_call_site() {
		Runnable r1 = createLambda();
		Runnable r2 = createLambda();
		assertSame(r1, r2);
		assertSame(r1.getClass(), r2.getClass());
	}

	// For different code positions different lambda implementations are created
	// even if they have the same lambda body.

	@Test
	public void different_call_site() {
		Runnable r1 = LambdaInstancesTest::staticMethod;
		Runnable r2 = LambdaInstancesTest::staticMethod;
		assertNotSame(r1, r2);
		assertNotSame(r1.getClass(), r2.getClass());
	}

	// Lambdas which capture state use a different instance on every creation
	// but share the same implementation class

	Runnable createLambdaWithCapture() {
		return System.out::println;
	}

	@Test
	public void same_call_site_with_capture() {
		Runnable r1 = createLambdaWithCapture();
		Runnable r2 = createLambdaWithCapture();
		assertNotSame(r1, r2);
		assertSame(r1.getClass(), r2.getClass());
	}

	// Not everything that looks like a capture actually captures any state at
	// the point of lambda creation.

	static Runnable createLambdaWithApparentCapture() {
		return () -> System.out.println();
	}

	@Test
	public void same_call_site_with_apparent_capture() {
		Runnable r1 = createLambdaWithApparentCapture();
		Runnable r2 = createLambdaWithApparentCapture();
		assertSame(r1, r2);
		assertSame(r1.getClass(), r2.getClass());
	}

	static void staticMethod() {
	}

}