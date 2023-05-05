package HelperClasses;

import java.io.*;

public class FileReaderHelper {
	File file;
	BufferedReader br;
	int dataSetNum;
	int attributeNum;

	public FileReaderHelper(String path, int dataSetNum, int attributeNum) throws FileNotFoundException {
		file = new File(path);
		br = new BufferedReader(new FileReader(file));
		this.dataSetNum = dataSetNum;
		this.attributeNum = attributeNum;
	}

	public double[][] textToArray() {
		double[][] arr = new double[attributeNum][dataSetNum];

		int counter = 0;

		try {
			while (true) {
				String s = br.readLine();
				if (s == null || counter == dataSetNum) break;
				String a[] = s.split(",");
				for (int i = 1; i <= 10; i++) {
					if (a[i].equals("?")) {
						a[i] = "0";
					}
				}
				arr[0][counter] = Integer.parseInt(a[0]);
				arr[1][counter] = Integer.parseInt(a[1]);
				arr[2][counter] = Integer.parseInt(a[2]);
				arr[3][counter] = Integer.parseInt(a[3]);
				arr[4][counter] = Integer.parseInt(a[4]);
				arr[5][counter] = Integer.parseInt(a[5]);
				arr[6][counter] = Integer.parseInt(a[6]);
				arr[7][counter] = Integer.parseInt(a[7]);
				arr[8][counter] = Integer.parseInt(a[8]);
				arr[9][counter] = Integer.parseInt(a[9]);
				arr[10][counter] = Integer.parseInt(a[10]);
				counter++;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return arr;
	}
}
