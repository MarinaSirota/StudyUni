package tpr3;

public class mainClass {

	public static void main(String[] args)
	{
		int[][] K =
		{
			{3, 5, 5, 4, 4},
			{4, 4, 4, 5, 4},
			{5, 4, 3, 3, 5},
			{3, 5, 3, 5, 3},
			{4, 2, 4, 5, 5},
			{3, 5, 3, 5, 3},
			{5, 3, 4, 3, 4},
			{4, 5, 3, 4, 3}
		};

		int[][] A1 =
		{
			{0, 1, 0, 0, 0},
			{0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0},
			{0, 0, 0, 0, 1},
			{0, 0, 0, 0, 0}
		};
		int[][] A2 =
		{
			{0, 0, 0, 0, 0},
			{0, 0, 1, 0, 0},
			{0, 0, 0, 1, 0},
			{0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0}
		};


		TPR obj = new TPR(K, A1, A2);
		System.out.println("������� K - c�������� ������ K ��������� Kj ��� ������� Xi:");
		obj.Printarray(K);
		System.out.println("������� ������������ ��� ���������:");
		obj.Printarray(A1);
		System.out.println("������� ��������������� ��� ���������:");
		obj.Printarray(A2);
		obj.Search();
		obj.Printresult();
		
}

}
