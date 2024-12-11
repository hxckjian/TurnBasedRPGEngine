package ErrorChecker;

import java.util.function.Predicate;

public class InputValidator {
	public static boolean validateAndHandle(int value, Predicate<Integer> validator, String errorMessage) {
	    if (!validator.test(value)) {
	        System.out.println(errorMessage);
	        return false;
	    }
	    return true;
	} 
}
