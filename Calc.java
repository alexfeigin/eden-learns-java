package com.eden.learnsjava

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Calc {

	enum Operand {
		PLUS("+", Integer::sum),
		MINUS("-", (x, y) -> x - y),
		MULTIPLICATION("*", (x, y) -> x * y),
		DIVISION("/", (x, y) -> x / y);

		private final String sign;
		BiFunction<Integer, Integer, Integer> function;

		Operand(String sign, BiFunction<Integer, Integer, Integer> function) {
			this.sign = sign;
			this.function = function;
		}

		private static final Map<String, com.proofpoint.inigo.Calc.Operand> bySign;

		static {
			bySign = Arrays.stream(values()).collect(Collectors.toMap(x -> x.sign, x -> x));
		}

		public static com.proofpoint.inigo.Calc.Operand getBySign(String sign) {
			return bySign.get(sign);
		}

		@Override
		public String toString() {
			return sign;
		}

		Integer calc(Integer A, Integer B) {
			return function.apply(A, B);
		}
	}

	public static void main(String[] args) {
		String operation = "";

		System.out.println("Enter Operation or q to quit:");

		while (!(operation = com.proofpoint.inigo.Calc.Console.readLine()).equals("q")) {

			String[] splitOperation = operation.split(" ");

			int A = Integer.parseInt(splitOperation[0]);
			com.proofpoint.inigo.Calc.Operand operand = com.proofpoint.inigo.Calc.Operand.getBySign(splitOperation[1]);
			int B = Integer.parseInt(splitOperation[2]);

			System.out.printf("%d %s %d = %d%n", A, operand, B, operand.calc(A, B));
		}
	}

	static class Console {
		private static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

		public static String readLine() {
			try {
				return console.readLine();
			} catch (IOException ignored) {
				return "q";
			}
		}
	}
}
