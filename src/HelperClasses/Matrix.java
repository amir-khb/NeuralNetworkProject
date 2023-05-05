package HelperClasses;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
	double[][] data;
	int rows;
	int cols;

	/**
	 * CONSTRUCTORS
	 **/
	public Matrix(int rows, int cols) {
		data = new double[rows][cols];
		this.rows = rows;
		this.cols = cols;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				data[i][j] = Math.random() * 2 - 1;
			}
		}
	}

	/**
	 * END OF CONSTRUCTORS
	 **/

	//SCALAR ADDITION TO MATRIX
	public void add(double scaler) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				this.data[i][j] += scaler;
			}

		}
	}

	/**
	 * MATRIX ADDITION, SUBTRACTION, MULTIPLICATION, AND TRANSPOSE
	 **/

	//MATRIX ADDITION
	public void add(Matrix m) {
		if (cols != m.cols || rows != m.rows) {
			System.out.println("Shape Mismatch");
			return;
		}

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				this.data[i][j] += m.data[i][j];
			}
		}
	}

	//MATRIX SUBTRACT
	public static Matrix subtract(Matrix a, Matrix b) {
		Matrix temp = new Matrix(a.rows, a.cols);
		for (int i = 0; i < a.rows; i++) {
			for (int j = 0; j < a.cols; j++) {
				temp.data[i][j] = a.data[i][j] - b.data[i][j];
			}
		}
		return temp;
	}

	//MATRIX TRANSPOSE
	public static Matrix transpose(Matrix a) {
		Matrix temp = new Matrix(a.cols, a.rows);
		for (int i = 0; i < a.rows; i++) {
			for (int j = 0; j < a.cols; j++) {
				temp.data[j][i] = a.data[i][j];
			}
		}
		return temp;
	}

	//MATRIX MULTIPLY
	public static Matrix multiply(Matrix a, Matrix b) {
		Matrix temp = new Matrix(a.rows, b.cols);
		for (int i = 0; i < temp.rows; i++) {
			for (int j = 0; j < temp.cols; j++) {
				double sum = 0;
				for (int k = 0; k < a.cols; k++) {
					sum += a.data[i][k] * b.data[k][j];
				}
				temp.data[i][j] = sum;
			}
		}
		return temp;
	}

	public void multiply(Matrix a) {
		for (int i = 0; i < a.rows; i++) {
			for (int j = 0; j < a.cols; j++) {
				this.data[i][j] *= a.data[i][j];
			}
		}

	}

	public void multiply(double a) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				this.data[i][j] *= a;
			}
		}

	}

	/**
	 * END OF MATRIX ADDITION, SUBTRACTION, MULTIPLICATION, AND TRANSPOSE
	 * <p>
	 * SIGMOID FUNCTION FOR ACTIVATION FUNCTION
	 **/
	public void sigmoid() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++)
				this.data[i][j] = 1 / (1 + Math.exp(-this.data[i][j]));
		}

	}

	//DERIVATIVE OF SIGMOID FUNCTION
	public Matrix dsigmoid() {
		Matrix temp = new Matrix(rows, cols);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++)
				temp.data[i][j] = this.data[i][j] * (1 - this.data[i][j]);
		}
		return temp;
	}

	/**
	 * END OF SIGMOID FUNCTION FOR ACTIVATION FUNCTION
	 * <p>
	 * HELPER METHODS
	 **/
	//CREATE MATRIX FROM AN ARRAY
	public static Matrix fromArray(double[] x) {
		Matrix temp = new Matrix(x.length, 1);
		for (int i = 0; i < x.length; i++)
			temp.data[i][0] = x[i];
		return temp;

	}

	public List<Double> toArray() {
		List<Double> temp = new ArrayList<Double>();

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				temp.add(data[i][j]);
			}
		}
		return temp;
	}
}