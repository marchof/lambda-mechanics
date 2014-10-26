import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * Inspect a lambda implementation.
 */
public class SamInstanceInspection {

	public static void main(String[] args) {

		Runnable r = () -> {
		};

		show("toString()", r);
		show("Class Name", r.getClass().getName());
		show("Class Modifier", Modifier.toString(r.getClass().getModifiers()));
		show("Super Class", r.getClass().getSuperclass().getName());
		show("Interfaces", r.getClass().getInterfaces());
		show("Class Loader", r.getClass().getClassLoader());
	}

	static void show(String label, Object value) {
		System.out.printf("%-15s: %s%n", label, value);
	}

	static void show(String label, Object[] values) {
		show(label, Arrays.asList(values));
	}

}
