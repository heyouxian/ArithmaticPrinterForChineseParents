package math.arithmetic.elementray;

import static math.arithmetic.elementray.ResultFormater.COUNT_PER_LINE;
import static math.arithmetic.elementray.ResultFormater.LINE_PER_PAGE;
import static math.arithmetic.elementray.Utility.divisors;
import static math.arithmetic.elementray.Utility.random;

import java.io.StringWriter;

import math.arithmetic.elementray.operation.AbstractOperation;
import math.arithmetic.elementray.operation.ContinuousIntegerOperation;
import math.arithmetic.elementray.operation.IntegerOperation;

/**
 * <pre>
 * --------------------------------------------------------
 * Copyright (c) 2016 by Nokia. All rights reserved.
 * --------------------------------------------------------
 * FILE :         ElementaryArithmeticMaker.java
 * --------------------------------------------------------
 * DESCRIPTION: 
 * CREATION DATE: Jul 22, 2016
 * AUTHOR:        shanh
 * PROJECT:       DaughtersArithmatic
 * --------------------------------------------------------
 * HISTORY:       Jul 22 2016: Created for Elementary School 2nd Grade
 *                Sep 18 2016: Add function for continuous operation twice for 3nd Grade 
 * --------------------------------------------------------
 * </pre>
 */
public class ElementaryArithmeticMaker {
	static final char EQUAL_CHAR = '=';
	static final char[] OPERATIONS = { '＋', '－', '×', '÷' };

	/**
	 * 写在最前面： 最大值都是不包含；最小值都是包含。变量MIN最小值，MAX最大值
	 */
	static final int MAX_SUM = 100; // 和
	static final int MIN_ADDEND = 11; // 被加数
	static final int MAX_ADDEND = MAX_SUM - MIN_ADDEND;
	// 加法要求都是两位数加两位数

	static final int MIN_MULTIPLICATOR = 2; // 被乘数（乘数）
	static final int MAX_MULTIPLICATOR = 10;
	// 乘法口诀

	static final int MIN_RESULT = Math.min(MIN_ADDEND, MIN_MULTIPLICATOR);
	// Make sure a equation result is >= MIN_RESULT

	static final int EQUTION_COUNT = COUNT_PER_LINE * LINE_PER_PAGE * 10;

	private <T extends IntegerOperation> ContinuousIntegerOperation<T> buildContinuousIntegerOperation(
			T op, ContinuousIntegerOperation<T> ret) {
		ret.setEquation(op);
		ret.setOperator(OPERATIONS[random.nextInt(4)]);
		ret.setNumberBeforeEquation(random.nextBoolean());
		ret.setReminder(0);
		switch (op.getOperator()) {
		case '×':
			if (op.getResult() >= MIN_MULTIPLICATOR
					&& op.getResult() < MAX_MULTIPLICATOR) {
				ret.setNumber(random(MIN_MULTIPLICATOR, MAX_MULTIPLICATOR));
				ret.setResult(ret.getNumber() * op.getResult());
				ret.setOperator('×');
				break;
			}
		case '÷':
			if ((ret.isNumberBeforeEquation() || op.getResult() < MIN_MULTIPLICATOR
					* MIN_MULTIPLICATOR)
					&& op.getResult() < MAX_MULTIPLICATOR) {
				ret.setNumberBeforeEquation(true);
				ret.setNumber(random(op.getResult() * MIN_MULTIPLICATOR,
						op.getResult() * (MAX_MULTIPLICATOR - 1) + 1));
				ret.setResult(ret.getNumber() / op.getResult());
				ret.setNumber(ret.getResult() * op.getResult());
				ret.setOperator('÷');
				break;
			} else if ((!ret.isNumberBeforeEquation() || op.getResult() >= MAX_MULTIPLICATOR)
					&& op.getResult() <= (MAX_MULTIPLICATOR - 1)
							* (MAX_MULTIPLICATOR - 1)) {
				// 算式值小等于81且（算式在前或（算式值大于9导致算式必须在前））
				int forbiddenNumber = 0;
				if (op.getOperator() == '×') {
					// prevent like a*b/a or a*b/b
					forbiddenNumber = op.getSecondNumber();
				}
				int[] divisors = divisors(op.getResult(), 10, forbiddenNumber);
				if (divisors.length > 0) {
					ret.setNumberBeforeEquation(false);
					ret.setNumber(divisors[random(0, divisors.length)]);
					ret.setResult(op.getResult() / ret.getNumber());
					ret.setOperator('÷');
					break;
				}
			}
		case '＋':
			if (op.getResult() >= MIN_ADDEND && op.getResult() < MAX_ADDEND) {
				ret.setNumber(random(MIN_ADDEND, MAX_SUM - op.getResult()));
				ret.setResult(ret.getNumber() + op.getResult());
				ret.setOperator('＋');
				break;
			}
		case '－':
			if (ret.isNumberBeforeEquation()) {
				if (op.getResult() >= MAX_ADDEND) {
					ret.setNumberBeforeEquation(false);
				}
			} else {
				if (op.getResult() < MIN_ADDEND * 2) {
					ret.setNumberBeforeEquation(true);
				}
			}
			if (ret.isNumberBeforeEquation()) {
				ret.setNumber(random(
						Math.min(op.getResult() + MIN_ADDEND, MAX_SUM - 1),
						MAX_SUM));
				ret.setResult(ret.getNumber() - op.getResult());
			} else {
				ret.setNumber(random(
						Math.min(MIN_ADDEND, op.getResult() - MIN_RESULT),
						Math.max(op.getResult() - MIN_ADDEND + 1,
								op.getResult() - MIN_RESULT + 1)));
				ret.setResult(op.getResult() - ret.getNumber());
			}
			ret.setOperator('－');
			break;
		}
		return ret;
	}

	private IntegerOperation buildIntegerOperation(boolean noReminder,
			IntegerOperation ret) {
		try {
			ret.setOperator(OPERATIONS[random.nextInt(4)]);
			ret.setReminder(0);
			switch (ret.getOperator()) {
			case '＋':
				ret.setFirstNumber(random(MIN_ADDEND, MAX_ADDEND));
				ret.setSecondNumber(random(MIN_ADDEND,
						MAX_SUM - ret.getFirstNumber()));
				ret.setResult(ret.getFirstNumber() + ret.getSecondNumber());
				break;
			case '－':
				ret.setFirstNumber(random(MIN_ADDEND * 2, MAX_SUM));
				ret.setSecondNumber(random(MIN_ADDEND, ret.getFirstNumber()
						- MIN_ADDEND + 1));
				ret.setResult(ret.getFirstNumber() - ret.getSecondNumber());
				break;
			case '×':
				ret.setFirstNumber(random(MIN_MULTIPLICATOR, MAX_MULTIPLICATOR));
				ret.setSecondNumber(random(MIN_MULTIPLICATOR, MAX_MULTIPLICATOR));
				ret.setResult(ret.getFirstNumber() * ret.getSecondNumber());
				break;
			case '÷':
				ret.setFirstNumber(random(
						MIN_MULTIPLICATOR * MIN_MULTIPLICATOR,
						(MAX_MULTIPLICATOR - 1) * (MAX_MULTIPLICATOR - 1)
								+ (noReminder ? 1 : MAX_MULTIPLICATOR)));
				ret.setSecondNumber(random(Math.max(
						MIN_MULTIPLICATOR,
						Math.min(MAX_MULTIPLICATOR - 1, ret.getFirstNumber()
								/ (MAX_MULTIPLICATOR - 1) + 1)), Math.min(
						ret.getFirstNumber() / MIN_MULTIPLICATOR + 1,
						MAX_MULTIPLICATOR)));
				ret.setResult(ret.getFirstNumber() / ret.getSecondNumber());
				if (noReminder) {
					ret.setFirstNumber(ret.getResult() * ret.getSecondNumber());
				} else {
					ret.setReminder(ret.getFirstNumber() - ret.getResult()
							* ret.getSecondNumber());
				}
				break;
			}
			return ret;
		} catch (Exception e) {
			System.err.println("current equation is " + ret.toString());
			throw e;
		}
	}

	private void make() {
		StringWriter equationContent = new StringWriter();
		StringWriter resultContent = new StringWriter();
		try (ResultFormater equationFormater = new ResultFormater(
				equationContent);
				ResultFormater equationResultFormater = new ResultFormater(
						resultContent);) {
			IntegerOperation op = new IntegerOperation();
			ContinuousIntegerOperation<IntegerOperation> ret = new ContinuousIntegerOperation<>();
			for (int total = 0; total < EQUTION_COUNT; total++) {
				AbstractOperation equation = buildContinuousIntegerOperation(
						buildIntegerOperation(true, op), ret);
				equationFormater.format(new StringBuilder().append(equation)
						.append(EQUAL_CHAR).toString());
				equationResultFormater.format(String.valueOf(equation
						.getResult()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.print(equationContent.toString());
		System.out.print(resultContent.toString());
	}

	public static void main(String[] args) {
		ElementaryArithmeticMaker maker = new ElementaryArithmeticMaker();
		maker.make();
	}
}
