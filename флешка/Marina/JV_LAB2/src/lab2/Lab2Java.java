
package lab2;
public class Lab2Java {

	public static void main(String[] args) {
		int n = 4;
		int l = 60;
		CLongBuffer T[] = new CLongBuffer[n];
		for (int i = 0; i < n; i++) {
			T[i] = new CLongBuffer(l);
			T[i].PrintInfo();
			T[i].PrintFirstN(10);
			T[i].Max();
			T[i].Sort();
			T[i].PrintFirstN(10);
			T[i].SaveSeparateLine("buf" + i + ".txt");
		}
	}
}
