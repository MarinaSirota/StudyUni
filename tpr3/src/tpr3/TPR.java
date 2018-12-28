package tpr3;

public class TPR {
	private int[][] K;
	private int[][] A1;
	private int[][] A2;
	private int[] X_teta;
	private int xCount = 8;
	private int markCount = 5;

	public TPR(int[][] K, int[][] A1, int[][] A2) {
		this.K = new int[xCount][markCount];
		this.A1 = new int[markCount][markCount];
		this.A2 = new int[markCount][markCount];
		X_teta = new int[xCount];
		this.K = K;
		this.A1 = A1;
		this.A2 = A2;
	}

	protected final void Initializevector() {
		for (int i = 0; i < xCount; i++) {
			X_teta[i] = 1;
		}
	}

	public final void Printarray(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				System.out.printf("%1$s ", array[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	public final void Printresult() {
		System.out.println("Вектор несравнимых решений: ");
		for (int i = 0; i < xCount; i++) {
			if (X_teta[i] == 1) {
				System.out.print(i + 1 + " ");
			}
		}
	}

	public final void Printvector(int[] a) {
		for (int i = 0; i < markCount; i++) {
			System.out.printf("%1$s ", a[i]);
		}
		System.out.println();
	}

	protected final boolean Pref(int[] a, int[] b) {
		boolean flag = true;
		int equalscount = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == b[i]) {
				equalscount++;
			} else {
				if (b[i] > a[i]) // если критерии не равны вернём false
				{
					flag = false;
					break;
				}
			}
		}
		if (flag && (equalscount != markCount)) {
			return true;
		} else {
			return false;
		}
	}

	protected final boolean Pref_for_j(int[] a, int[] b, int j) {
		if (a[j] > b[j]) {
			return true;
		} else {
			return false;
		}
	}

	protected final int[] Line_to_vector(int[][] massiv, int number_of_line) {
		int[] vector = new int[markCount];
		for (int i = 0; i < markCount; i++) {
			vector[i] = massiv[number_of_line][i];
		}
		return vector;
	}

	protected final void calculate(boolean no_first_tour, boolean pref, int _j, int _h) {
		int[] timeVector1 = new int[markCount];
		int[] timeVector1_1 = new int[markCount];
		int[] timeVector2 = new int[markCount];
		for (int i = 0; i < xCount - 1; i++) {
			timeVector1 = Line_to_vector(K, i);
			if (no_first_tour && pref) {
				timeVector1_1 = timeVector1;
				timeVector1 = swap(timeVector1, _j, _h);
				if (!Pref_for_j(timeVector1_1, timeVector1, _j)) {
					break;
				}
				if (X_teta[i] == 1) {
					Printvector(timeVector1);
				}
			}
			if (no_first_tour && (!pref)) {
				timeVector1_1 = timeVector1;
				timeVector1 = swap(timeVector1, _j, _h);
				if (Pref(timeVector1_1, timeVector1)) {
					break;
				}
				//if (X_teta[i] == 1) {
					//Printvector(timeVector1);
				//}
			}
			for (int j = i + 1; j < xCount; j++) {
				timeVector2 = Line_to_vector(K, j);
				if (Pref(timeVector1, timeVector2)) {
					X_teta[j] = 0;
				} else {
					if (Pref(timeVector2, timeVector1)) {
						X_teta[i] = 0;
					}
				}
			}
		}
	}

	protected final int[] swap(int[] a, int j, int h) {
		int temp = a[j];
		a[j] = a[h];
		a[h] = temp;
		return a;
	}

	public final void Search() {

		Initializevector();
		calculate(false, true, 0, 0);
		int[] timeVector1 = new int[markCount];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (A1[i][j] == 1) {
					calculate(true, true, i, j);
				}
			}
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (A2[i][j] == 1) {
					calculate(true, false, i, j);
				}
			}
		}
	}
}
