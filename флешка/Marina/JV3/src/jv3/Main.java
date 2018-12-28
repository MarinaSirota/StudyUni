package jv3;

import java.io.File;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	protected static String inFile = "";
	protected static String outFile = "";

	public static void main(String[] args) {
		if (!init(args)) {
			return;
		}
		Scanner scn = new Scanner(System.in);

		listOne list1 = new listOne();
		list1.read(inFile);
		System.out.print("\t������ ���������:\n");
		list1.print();
		System.out.print("������� ����� ������ ��� ������\n");
		if (list1.contains(scn.next())) {
			System.out.print("����� �������\n");
		} else {
			System.out.print("����� �� �������\n");
		}
		System.out.print("\n");

		listTwo list2 = new listTwo();
		list2.read(inFile);
		System.out.print("\t������ ��������� �� �������������:\n");
		list2.print();
		System.out.print("\t���������� ������ ��������� �� ������� ����:\n");
		Collections.sort(list2);
		list2.print();
		System.out.print("\t ���������� ������ ��������� �� ������� ����:\n");
		Collections.sort(list2, new carComparator());
		list2.print();
		list2.write(outFile);
		System.out.print("\n");

		listThree list3 = new listThree();
		list3.read(inFile);
		System.out.print("\t������ ���������:\n");
		list3.print();
		System.out.print("������� ����� ������ ��� ������\n");
		list3.print(scn.next());
		System.out.print("\n");

		scn.close();
	}

	private static boolean init(String[] args) {
		int i = 0;
		while (i < args.length) {
			if (args[i].equalsIgnoreCase("-i")) {
				inFile = args[++i];
			} else if (args[i].equalsIgnoreCase("-o")) {
				outFile = args[++i];
			} else {
				System.out.printf("�������� ���������: ", args[i]);
			}
			i++;
		}
		if (inFile.isEmpty()) {
			System.out.print("������� ���� �� ������, ����������� -i ");
			return false;
		} else if (!(new File(inFile)).exists()) {
			System.out.print("������� ���� �� ����������");
			return false;
		}
		if (outFile.isEmpty()) {
			System.out.print("�������� ���� �� ������, ����������� -o ");
			return false;
		}
		return true;
	}

}