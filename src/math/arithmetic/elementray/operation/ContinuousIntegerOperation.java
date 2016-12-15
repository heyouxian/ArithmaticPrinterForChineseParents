package math.arithmetic.elementray.operation;

public class ContinuousIntegerOperation<T extends IntegerOperation> extends
		IntegerOperation {
	protected T equation;
	protected int number;
	protected boolean numberBeforeEquation;

	public T getEquation() {
		return equation;
	}

	public void setEquation(T equation) {
		this.equation = equation;
		if (numberBeforeEquation) {
			this.secondNumber = equation.result;
		} else {
			this.firstNumber = equation.result;
		}
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
		if (numberBeforeEquation) {
			this.firstNumber = number;
		} else {
			this.secondNumber = number;
		}
	}

	public boolean isNumberBeforeEquation() {
		return numberBeforeEquation;
	}

	public void setNumberBeforeEquation(boolean numberBeforeEquation) {
		if (numberBeforeEquation != this.numberBeforeEquation) {
			int tmp = this.firstNumber;
			this.firstNumber = this.secondNumber;
			this.secondNumber = tmp;
		}
		this.numberBeforeEquation = numberBeforeEquation;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (numberBeforeEquation) {
			sb.append(number).append(operator);
			if (equation.compareTo(this) > 0 || matchCommutation(equation)) {
				sb.append(equation);
			} else {
				sb.append('(').append(equation).append(')');
			}
		} else {
			if (equation.compareTo(this) >= 0) {
				sb.append(equation);
			} else {
				sb.append('(').append(equation).append(')');
			}
			sb.append(operator).append(number);
		}
		return sb.toString();
	}
}