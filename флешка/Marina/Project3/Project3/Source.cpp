#include <iostream>

using namespace std;
const int n = 7;
void search(int mas[n][n]) {
	setlocale(LC_ALL, "RUS");
	float U[n];
	int XPlus[n];
	int XMinus [n];
	int k = 1;

	bool XPlusEmpty = true, XMinusEmpty = true, Inter = false;
	int min = -1, max = -1;
	int maxU = 0;
	U[0] = 0.0f;
	for (int p = 0; p < n - 1; p++)

	{
		XPlusEmpty = true;
		XMinusEmpty = true;
		Inter = false;

		for (int i = 0; i < n ; i++)

		{

			XPlus[i] = 0;
			XMinus[i] = 0;

		}

		for (int i = 0; i < k; i++)

		{

			if (mas[i][p + 1] == 1)

			{

				XPlus[i] = 1;

				XPlusEmpty = false;

			}

			if (mas[p + 1][i] == 1)

			{

				XMinus[i] = 1;
				XMinusEmpty = false;

			}

		}

		for (int j = 0; j < n ; j++)

			if (XPlus[j] == 1 && XMinus[j] == 1)

			{

				Inter = true;

			}

		if (XPlusEmpty == true && XMinusEmpty == false)

			U[p + 1] = U[p] + 1.0f;

		else if (XMinusEmpty == true && XPlusEmpty == false)

			U[p + 1] = U[p] - 1.0f;

		else if (XMinusEmpty == false && XPlusEmpty == false && Inter == true)

		{

			for (int i = 0; i <= k; i++)

			{

				if (XMinus[i] == 1 && XPlus[i] == 1) U[p + 1] = U[i];

			}

		}

		else if (XMinusEmpty == false && XPlusEmpty == false && Inter == false)

		{

			for (int z = 0; z <= k; z++)

			{

				if (XPlus[z] == 1)

				{

					min = z;

					for (int i = 0; i <= k; i++)

						if (mas[i][z] != 1 && XPlus[i] == 1) break;

				}

			}

			for (int z = 0; z <= k; z++)

			{

				if (XMinus[z] == 1)

				{

					max = z;

					for (int i = 0; i <= k; i++)

						if (mas[z][i] != 1 && XMinus[i] == 1)

						{

							max = z;

							break;

						}

				}

			}

			U[p + 1] = (U[min] + U[max]) / 2.0f;

		}

		k++;

		cout << "X" << (p + 1) << "- : ";

		for (int i = 0; i < n; i++)

		{

			if (XMinus[i] == 1)

				cout << "X" << i+1 << " ";

		}

		cout << endl;

		cout << "X" << (p + 1) << "+ : ";

		for (int i = 0; i < n; i++)

		{

			if (XPlus[i] == 1)

				cout << "X" << i+1 << " ";

		}

		cout << endl;
		cout << endl;

	}

	cout << "U: ";

	for (int i = 0; i < n; i++)

	{

		cout << U[i] << " ";

		if (U[i] > U[maxU])

			maxU = i;

	}

	cout << endl;

	cout << "Ёффективные решени€ : ";

	for (int i = 0; i < n; i++)

		if (U[i] == U[maxU])

			cout << "X" << (i + 1) << " ";


}

int main()

{
	const int n = 7;
	int A[n][n]={
{ 0, 0, 0, 0, 0, 0, 0 },
{ 1, 0, 1, 0, 0, 0, 0 },
{ 0, 0, 0, 1, 0, 0, 1 },
{ 0, 1, 0, 0, 1, 1, 0 },
{ 0, 1, 0, 0, 0, 0, 0 },
{ 1, 0, 0, 1, 0, 0, 0 },
{ 0, 0, 1, 0, 0, 1, 0 },

	};

	search(A);
	cout << endl;
	system("pause");

}
