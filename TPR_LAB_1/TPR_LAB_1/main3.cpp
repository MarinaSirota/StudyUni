#include <iostream>

using namespace std; 

int findMin(int array[]) {
	int min = 0;
	for (int i = 0; i < sizeof(array); i++) {
		if (array[i] > array[min]) {
			min = i;
		}
	}
	return min;
}
int main() {
	setlocale(LC_ALL, "RUS");
	const int n = 7;
	
	int maxR[n];
	int matr[n][n] = 
	{ 0,1,1,0,0,0,0,
		0,0,1,0,0,0,0,
		0,0,0,0,0,1,1,
		0,1,0,0,0,1,0,
		0,1,0,1,0,1,0,
		0,0,0,0,0,0,1,
		0,0,0,0,0,0,0 };



	int b = 0, zflag = 0;
	int buff[n];
	for (int j = 0; j < n; j++) {
		zflag = 0;
		for (int i = 0; i < n; i++) {
			if (matr[i][j] == 0)
				zflag++;
		}
		if (zflag == n) {
			buff[b] = j;
			b++;

		}
	}

	for (int i = 0; i < b; i++)
		for (int j = 0; j < n; j++) {
			matr[buff[i]][j] = 1;
			matr[j][buff[i]] = 1;
		}

	int k = 0;
	while (k < n) {

		int count[n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				if (matr[i][j] == 0) {
					count[i]++;
				}
		}

		maxR[k] = findMin(count);
		for (int i = 0; i < n; i++) {
			matr[maxR[k]][i] = 1;
			matr[i][maxR[k]] = 1;
		}

		k++;
	}
	b = 0;
	for (int i = 0; i < n; i++) {
		if (maxR[i] == 0) {
			maxR[i] = buff[b];
			b++;
		}
		cout << maxR[i];
	}
	cout << endl;
	system("pause");
	return 0;
}