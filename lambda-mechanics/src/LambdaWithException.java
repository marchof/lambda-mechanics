import java.util.Arrays;
import java.util.List;

/**
 * Exceptions within lambdas are forwarded to the caller of the functional
 * interface. This allows some reverse engineering of the lambda implementation.
 */
public class LambdaWithException {

	public static void main(String[] args) {

		List<String> greetings = Arrays.asList("Hello", "Lambda");

		greetings.forEach((item) -> {
			throw new RuntimeException("Where are we?");
		});

	}

}
