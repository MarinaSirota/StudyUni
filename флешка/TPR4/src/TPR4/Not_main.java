package TPR4;
public class Not_main {

	public static double[] Find_w(double[][] matrix) {
		double[] w = new double[matrix.length];

		double sum = 0;

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				sum += matrix[i][j];
			}
		}

		for (int i = 0; i < matrix.length; i++) {
			w[i] = 0;
			for (int j = 0; j < matrix[i].length; j++)
				w[i] += matrix[i][j];
			w[i] /= sum;
		}

		for (int i = 0; i < w.length; i++) {
			String formattedMarks = String.format("%.3f", w[i]);
			System.out.print("w[" + (i + 1) + "] = " + formattedMarks + "  ");
		}
		System.out.println();

		return w;
	}

	public static void Find_ind_deg(double[][] matrix, double[] w, double n) throws Error {

		try {
			double[] ww = new double[w.length];

			for (int i = 0; i < matrix.length; i++) {
				ww[i] = 0;
				for (int j = 0; j < matrix[i].length; j++) {
					ww[i] += matrix[i][j] * w[j];
				}
			}

			double[] www = new double[w.length];

			for (int i = 0; i < w.length; i++) {
				www[i] = ww[i] / w[i];
			}

			double lambda = 0;

			for (int i = 0; i < w.length; i++) {
				lambda += www[i] / n;
			}

			double degreeIndex = (lambda - n) / (n - 1);
			String formattedMarks = String.format("%.3f", lambda);
			System.out.println("Lmax = " + formattedMarks);
			formattedMarks = String.format("%.3f", degreeIndex);
			System.out.println("ÈÑ = " + formattedMarks);

			
		} catch (Error error) {
		}
		;
	}
}
