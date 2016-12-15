package math.arithmetic.elementray.operation;
public abstract class AbstractOperation implements
		Comparable<AbstractOperation> {
	protected char operator = 0;
	protected int result;
	protected int reminder;

	public char getOperator() {
		return operator;
	}

	public void setOperator(char operator) {
		this.operator = operator;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getReminder() {
		return reminder;
	}

	public void setReminder(int reminder) {
		this.reminder = reminder;
	}

	@Override
	public int compareTo(AbstractOperation o) {
		return charPriority() - o.charPriority();
	}

	public int charPriority() {
		switch (operator) {
		case '＋':
		case '－':
			return 0;
		case '×':
		case '÷':
			return 1;
		}
		return Integer.MAX_VALUE;
	}

	public boolean matchCommutation(AbstractOperation o) {
		return (operator == '＋' || operator == '×') && operator == o.operator;
	}
}