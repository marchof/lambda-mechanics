import java.lang.reflect.Method;
import java.util.function.Supplier;

/**
 * Specialized interfaces may override existing methods with a different (more
 * specific) return type. In this case so called bridge methods have to be
 * created for implementing classes.
 */
public class LambdaWithAdapterMethods {

	static interface StringSupplier extends Supplier<String> {
		@Override
		String get();
	}

	public static void main(String[] args) {
		StringSupplier supplier = () -> "Lambda";
		for (Method m : supplier.getClass().getDeclaredMethods()) {
			System.out.println(m);
		}
	}

}
