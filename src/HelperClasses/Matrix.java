package HelperClasses;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
	private Node head;
	private int rows;
	private int cols;

	private static class Node {
		double value;
		Node nextRow;
		Node nextCol;

		Node(double value) {
			this.value = value;
		}
	}

	public Matrix(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		initializeMatrix2();
	}

	private void initializeMatrix2() {
		head = new Node(0.0);
		Node currentRow = head;
		Node currentCol = head;

		for (int i = 1; i < rows; i++) {
			currentRow.nextRow = new Node(0.0);
			currentRow = currentRow.nextRow;
		}

		for (int j = 1; j < cols; j++) {
			currentCol.nextCol = new Node(0.0);
			currentCol = currentCol.nextCol;
		}
	}

	public void add(double scalar) {
		Node currentRow = head;
		while (currentRow != null) {
			Node currentCol = currentRow;
			while (currentCol != null) {
				currentCol.value += scalar;
				currentCol = currentCol.nextCol;
			}
			currentRow = currentRow.nextRow;
		}
	}

	public void add(Matrix m) {
		if (m.rows != rows || m.cols != cols) {
			System.out.println("Shape Mismatch");
			return;
		}

		Node currentRowA = head;
		Node currentRowB = m.head;

		while (currentRowA != null && currentRowB != null) {
			Node currentColA = currentRowA;
			Node currentColB = currentRowB;

			while (currentColA != null && currentColB != null) {
				currentColA.value += currentColB.value;
				currentColA = currentColA.nextCol;
				currentColB = currentColB.nextCol;
			}

			currentRowA = currentRowA.nextRow;
			currentRowB = currentRowB.nextRow;
		}
	}

	public static Matrix subtract(Matrix a, Matrix b) {
		Matrix result = new Matrix(a.rows, a.cols);
		Node currentRowA = a.head;
		Node currentRowB = b.head;
		Node currentRowRes = result.head;

		while (currentRowA != null && currentRowB != null && currentRowRes != null) {
			Node currentColA = currentRowA;
			Node currentColB = currentRowB;
			Node currentColRes = currentRowRes;

			while (currentColA != null && currentColB != null && currentColRes != null) {
				currentColRes.value = currentColA.value - currentColB.value;
				currentColA = currentColA.nextCol;
				currentColB = currentColB.nextCol;
				currentColRes = currentColRes.nextCol;
			}

			currentRowA = currentRowA.nextRow;
			currentRowB = currentRowB.nextRow;
			currentRowRes = currentRowRes.nextRow;
		}

		return result;
	}

	public static Matrix transpose(Matrix a) {
		Matrix result = new Matrix(a.cols, a.rows);
		Node currentRowA = a.head;
		Node currentRowRes = result.head;

		while (currentRowA != null && currentRowRes != null) {
			Node currentColA = currentRowA;
			Node currentColRes = currentRowRes;

			while (currentColA != null && currentColRes != null) {
				currentColRes.value = currentColA.value;
				currentColA = currentColA.nextCol;
				currentColRes = currentColRes.nextRow;
			}

			currentRowA = currentRowA.nextRow;
			currentRowRes = currentRowRes.nextCol;
		}

		return result;
	}

	public static Matrix multiply(Matrix a, Matrix b) {
		Matrix result = new Matrix(a.rows, b.cols);
		Node currentRowA = a.head;
		Node currentRowRes = result.head;

		while (currentRowA != null && currentRowRes != null) {
			Node currentColB = b.head;
			Node currentColRes = currentRowRes;

			while (currentColB != null && currentColRes != null) {
				double sum = 0.0;
				Node currentColA = currentRowA;
				while (currentColA != null) {
					sum += currentColA.value * currentColB.value;
					currentColA = currentColA.nextCol;
					currentColB = currentColB.nextRow;
				}
				currentColRes.value = sum;
				currentColRes = currentColRes.nextCol;
				currentColB = b.head;
			}

			currentRowA = currentRowA.nextRow;
			currentRowRes = currentRowRes.nextRow;
		}

		return result;
	}

	public void multiply(Matrix a) {
		Node currentRowA = a.head;
		Node currentRowRes = head;

		while (currentRowA != null && currentRowRes != null) {
			Node currentColA = currentRowA;
			Node currentColRes = currentRowRes;

			while (currentColA != null && currentColRes != null) {
				currentColRes.value *= currentColA.value;
				currentColA = currentColA.nextCol;
				currentColRes = currentColRes.nextCol;
			}

			currentRowA = currentRowA.nextRow;
			currentRowRes = currentRowRes.nextRow;
		}
	}

	public void multiply(double a) {
		Node currentRow = head;
		while (currentRow != null) {
			Node currentCol = currentRow;
			while (currentCol != null) {
				currentCol.value *= a;
				currentCol = currentCol.nextCol;
			}
			currentRow = currentRow.nextRow;
		}
	}

	public void sigmoid() {
		Node currentRow = head;
		while (currentRow != null) {
			Node currentCol = currentRow;
			while (currentCol != null) {
				currentCol.value = 1 / (1 + Math.exp(-currentCol.value));
				currentCol = currentCol.nextCol;
			}
			currentRow = currentRow.nextRow;
		}
	}

	public Matrix dsigmoid() {
		Matrix result = new Matrix(rows, cols);
		Node currentRow = head;
		Node currentRowRes = result.head;

		while (currentRow != null && currentRowRes != null) {
			Node currentCol = currentRow;
			Node currentColRes = currentRowRes;

			while (currentCol != null && currentColRes != null) {
				currentColRes.value = currentCol.value * (1 - currentCol.value);
				currentCol = currentCol.nextCol;
				currentColRes = currentColRes.nextCol;
			}

			currentRow = currentRow.nextRow;
			currentRowRes = currentRowRes.nextRow;
		}

		return result;
	}

	public static Matrix fromArray(double[] array) {
		Matrix result = new Matrix(array.length, 1);
		Node currentRow = result.head;

		for (int i = 0; i < array.length; i++) {
			currentRow.value = array[i];
			if (i != array.length - 1) {
				currentRow.nextRow = new Node(0.0);
				currentRow = currentRow.nextRow;
			}
		}

		return result;
	}

	public List<Double> toArray() {
		List<Double> result = new ArrayList<>();
		Node currentRow = head;

		while (currentRow != null) {
			Node currentCol = currentRow;
			while (currentCol != null) {
				result.add(currentCol.value);
				currentCol = currentCol.nextCol;
			}
			currentRow = currentRow.nextRow;
		}

		return result;
	}
}
