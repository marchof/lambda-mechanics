import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.junit.Test;

/**
 * Lambdas do always map to a particular functional interface. Even if the type
 * is not directly visible in the source code the compiler interferes the lambda
 * type from methods arguments.
 */
public class LambdaTypes {

	@Test
	public void implizitType() {

		List<String> greetings = Arrays.asList("Hello", "Lambda");

		greetings.forEach((item) -> System.out.println(item));

		greetings.forEach(System.out::println);

	}

	@Test
	public void explizitType() {

		List<String> greetings = Arrays.asList("Hello", "Lambda");

		Consumer<String> printer = System.out::println;

		greetings.forEach(printer);

	}
	
	@Test
	public void innerClass() {

		List<String> greetings = Arrays.asList("Hello", "Lambda");

		greetings.forEach(new Consumer<String>() {
			@Override
			public void accept(String item) {
				System.out.println(item);
			}
		});
	}

}
