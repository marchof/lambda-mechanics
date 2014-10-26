import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * There is no need to use the handy lambda syntax. You can also take the effort
 * to use the LambdaMetaFactory directly.
 * 
 * Disclaimer: Never ever do this in production code, or at least do not blame
 * me for doing so ;-)
 */
public class LambdaMetaFactoryTest {

	public static void main(String[] args) throws Throwable {

		// Parameters to the lambda factory:
		Lookup caller = MethodHandles.lookup();
		String invokedName = "accept";
		MethodType invokedType = MethodType.methodType(Consumer.class);
		MethodType samMethodType = MethodType.methodType(Void.TYPE,
				Object.class);
		MethodHandle implMethod = caller.findStatic(
				LambdaMetaFactoryTest.class, "myLambda", samMethodType);

		// Create the factory:
		CallSite factory = LambdaMetafactory.metafactory(caller, invokedName,
				invokedType, samMethodType, implMethod, samMethodType);

		// Create the actual lambda instance:
		Consumer<String> printer = (Consumer<String>) factory.dynamicInvoker()
				.invoke();

		// Use the lambda instance:
		List<String> greetings = Arrays.asList("Hello", "Lambda");
		greetings.forEach(printer);
		
	}

	public static void myLambda(Object item) {
		System.out.println(item);
	}

}
