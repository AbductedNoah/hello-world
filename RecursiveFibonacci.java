/*
	 * @author Noah Wells
	 * A simple recursive class that takes an input from the main class and outputs the specified fibonacci number1
	 * preHomeworkSet2
	 */

public class RecursiveFibonacci {

	public static long fib(int nth) {
		// for 0 and 1 input, the fibonacci number is the same
		if (nth == 0) {
			return 0;
		} else if (nth == 1) {
			return 1;
		}
		// else, we use the given fibonacci formula to find the output
		else {
			long answer = fib(nth - 1) + fib(nth - 2);
			return answer;
		}
	}
}
