import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Same as {@link LambdaMetaFactoryTest} but with capturing state.
 */
public class LambdaMetaFactoryClosureTest {

	public static void main(String[] args) throws Throwable {

		Instant capturedValue = Instant.now();

		// Parameters to the lambda factory:
		Lookup caller = MethodHandles.lookup();
		String invokedName = "accept";
		MethodType invokedType = MethodType.methodType(Consumer.class,
				Instant.class);
		MethodType samMethodType = MethodType.methodType(Void.TYPE,
				Object.class);
		MethodType implType = MethodType.methodType(Void.TYPE, Instant.class,
				Object.class);
		MethodHandle implMethod = caller.findStatic(
				LambdaMetaFactoryClosureTest.class, "myLambda", implType);

		// Create the factory:
		CallSite factory = LambdaMetafactory.metafactory(caller, invokedName,
				invokedType, samMethodType, implMethod, samMethodType);

		// Create the actual lambda instance:
		Consumer<String> printer = (Consumer<String>) factory.dynamicInvoker()
				.invoke(capturedValue);

		// Use the lambda instance:
		List<String> greetings = Arrays.asList("Hello", "Lambda");
		greetings.forEach(printer);
	}

	public static void myLambda(Instant timestamp, Object item) {
		System.out.println(timestamp + " " + item);
	}

}
