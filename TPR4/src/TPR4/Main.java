package TPR4;

public class Main {

	public static void main(String args[]) {

		double[][] A1 = new double[][] { {1, 0.2, 2, 2},
			{5, 1, 3, 3},
			{0.5, 0.33, 1,0.166},
			{0.5, 0.33, 6, 1}
 }; 

		
		double[][] A21 = new double[][] { { 1, 2, 0.25, 0.25, 2, 1 }, { 0.5, 1, 4, 2, 1, 0.5 },
				{ 4, 0.25, 1, 0.5, 2, 1 }, { 4, 0.5, 2, 1, 1, 1}, { 0.5, 1, 0.5,  1, 1, 4 },
				{ 1, 2, 1,1, 0.25, 1 } };

		double[][] A22 = new double[][] { { 1, 5, 1, 5, 1, 5 }, {  0.2, 1, 0.2, 1, 0.2, 1},
				{  1, 5, 1, 5, 1, 5 }, { 0.2, 1, 0.2, 1, 0.2, 1 }, {  1, 5, 1, 5, 1, 5 },
				{  0.2, 1, 0.2, 1, 0.2, 1} };


		double[][] A23 = new double[][] { { 1,1,1,1,1,1 }, { 1,1,1,1,1,1 }, { 1,1,1,1,1,1 },
			{1,1,1,1,1,1 }, { 1, 1, 1, 1, 1, 1 }, {1,1,1,1,1,1} };


		double[][] A24 = new double[][] { {1,2,1,1,1,2 }, { 0.5,1,1,1,1,1 }, { 1,1,1,2,2,2},
				{1,1,0.5,1,0.5,0.5 }, { 1,1,0.5,2,1,1 }, {0.5,1,0.5,2,1,1} };

		printMatrix(A1, "A1");
		double[] w = Not_main.Find_w(A1);
		Not_main.Find_ind_deg(A1, w, 4);
		printMatrix(A21, "\nA21 (Фундаментальность предмета)");
		double[] w21 = Not_main.Find_w(A21);
		Not_main.Find_ind_deg(A21, w21, 6);
		printMatrix(A22, "\nA22 (Уровень развития науки)");
		double[] w22 = Not_main.Find_w(A22);
		Not_main.Find_ind_deg(A22, w22, 6);
		printMatrix(A23, "\nA23 (Возможность использования в профессиональной деятельности)");
		double[] w23 = Not_main.Find_w(A23);
		Not_main.Find_ind_deg(A23, w23, 6);
		printMatrix(A24, "\nA24 (Симпатия к преподавателю)");
		double[] w24 = Not_main.Find_w(A24);
		Not_main.Find_ind_deg(A24, w24, 6);

		double[] marks = new double[A21.length];

		for (int i = 0; i < marks.length; i++) {
			marks[i] = w21[i] * w[0] + w22[i] * w[1] + w23[i] * w[2] + w24[i] * w[3];
		}

		printResult(marks);

	}

	public static void printMatrix(double[][] matrix, String name) {

		System.out.println(name + ":");

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}

	}

	public static void printResult(double[] marks) {
		System.out.println("\nОценки:");
		String formattedMarks = String.format("%.3f", marks[0]);
		System.out.println("Теория принятия решений = " + formattedMarks);
		formattedMarks = String.format("%.3f", marks[1]);
		System.out.println("Теория алгоритмов = " + formattedMarks);
		formattedMarks = String.format("%.3f", marks[2]);
		System.out.println("Теория вероятности и математическая статистика = " + formattedMarks);
		formattedMarks = String.format("%.3f", marks[3]);
		System.out.println("Теория информационных процессов = " + formattedMarks);
		formattedMarks = String.format("%.3f", marks[4]);
		System.out.println("Технологии обработки информации = " + formattedMarks);
		formattedMarks = String.format("%.3f", marks[5]);
		System.out.println("Технологии программирования = " + formattedMarks);

		int max = 0;

		for (int i = 1; i < marks.length; i++) {
			if (marks[i] > marks[max]) {
				max = i;
			}
		}

		System.out.print("\nНаилучший выбор:\n");

		switch (max) {
		case 0: {
			System.out.println("Теория принятия решений");
			break;
		}
		case 1: {
			System.out.println("Теория алгоритмов");
			break;
		}
		case 2: {
			System.out.println("Теория вероятности и математической статистики");
			break;
		}
		case 3: {
			System.out.println("Теория информационных процессов");
			break;
		}
		case 4: {
			System.out.println("Технологии обработки информации");
			break;
		}
		case 5: {
			System.out.println("Технологии программирования");
			break;
		}
		}
	}
}
