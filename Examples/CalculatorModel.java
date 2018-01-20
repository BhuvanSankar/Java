package week10;

/** The model for the Calculator. */
public class CalculatorModel {

    // the result of the calculation if the calculator is not in an error state
    private int result;
    // used to indicate that the calculator is in an error state
    private boolean error;

    /**
     * Creates a new calculator model that is not in an error state and has a
     * result of zero.
     */
    public CalculatorModel() {
        result = 0;
        error = false;
    }

    /**
     * Returns whether or not the calculator is in an error state.
     * @return Whether or not the caclculator is in an error state.
     */
    public boolean hasError() {
        return error;
    }

    /**
     * Returns the string "ERROR" if the calculator is in an error state, and
     * the current result of the calculator otherwise.
     * 
     * @return the result of the calculator
     */
    public String getResult() {
        if (error) {
            return "ERROR";
        } else {
            return "" + result;
        }
    }

    /**
     * Adds number to the calculator's result if the calculator is not in an
     * error state. Otherwise has no effect on the result of the calculator.
     * 
     * @param number
     *            the number to add
     */
    public void add(int number) {
        if (!error) {
            result += number;
        }
    }

    /**
     * Subtract the given number from the calculator's result if the calculator
     * is not in an error state. Otherwise has no effect on the result of the
     * calculator.
     * 
     * @param number
     *            the number to be subtracted
     */
    public void subtract(int number) {
        if (!error) {
            result -= number;
        }
    }

    /**
     * Multiply the calculator's result by the given number if the calculator is
     * not in an error state. Otherwise has no effect on the result of the
     * calculator.
     * 
     * @param number
     *            the number to multiply by
     */
    public void multiply(int number) {
        if (!error) {
            result *= number;
        }
    }

    /**
     * Divide the calculator's result by the given number. Transition to an
     * error state if the divisor is zero.
     * 
     * @param number
     *            the number to divide by
     */
    public void divide(int number) {
        if (!error && number != 0) {
            result /= number;
        } else {
            error = true;
        }
    }

    /**
     * Set the result of the calculator back to zero (not an error state).
     */
    public void clear() {
        result = 0;
        error = false;
    }
}
