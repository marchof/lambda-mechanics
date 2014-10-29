import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.function.Supplier;

/**
 * Inspect different lambda implementations.
 */
public class LambdaInstanceInspection {

	static interface StringSupplier extends Supplier<String> {
		@Override
		String get();
	}

	public static void main(String[] args) {

		Runnable lambda1 = () -> System.out.println("Lambda");
		inspect("Lambda without Capture", lambda1);

		String capturedValue = "Lambda";
		Runnable lambda2 = () -> System.out.println(capturedValue);
		inspect("Lambda with Capture", lambda2);

		Runnable lambda3 = (Runnable & Serializable) () -> System.out.println();
		inspect("Serializable Lambda", lambda3);

		StringSupplier lambda4 = () -> "Lambda";
		inspect("Lambda With Bridge Methods", lambda4);

	}

	static void inspect(String label, Object instance) {
		System.out.println();
		System.out.println(label);

		Class<?> c = instance.getClass();
		show("toString()", instance);
		show("Class Name", c.getName());
		show("Class Modifier", Modifier.toString(c.getModifiers()));
		show("Super Class", c.getSuperclass().getName());
		show("Interface", c.getInterfaces());
		show("Class Loader", c.getClassLoader());
		show("Field", c.getDeclaredFields());
		show("Constructor", c.getDeclaredConstructors());
		show("Method", c.getDeclaredMethods());
	}

	static void show(String label, Object value) {
		System.out.printf("%-15s: %s%n", label, value);
	}

	static void show(String label, Object[] values) {
		Arrays.asList(values).forEach((v) -> show(label, v));
	}

}
