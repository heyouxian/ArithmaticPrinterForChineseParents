package math.arithmetic.elementray.operation;
public class IntegerOperation extends AbstractOperation {
	protected int firstNumber;
	protected int secondNumber;

	public int getFirstNumber() {
		return firstNumber;
	}

	public void setFirstNumber(int firstNumber) {
		this.firstNumber = firstNumber;
	}

	public int getSecondNumber() {
		return secondNumber;
	}

	public void setSecondNumber(int secondNumber) {
		this.secondNumber = secondNumber;
	}

	@Override
	public String toString() {
		if (operator == 0) {
			return String.valueOf(result);
		}
		return new StringBuilder().append(firstNumber).append(operator)
				.append(secondNumber).toString();
	}
}