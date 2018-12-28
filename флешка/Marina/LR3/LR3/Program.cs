using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LR3
{
    class Progrяam
    {
        static void Main(string[] args)
        {
            int[,] K =          {     { 3, 5, 5, 4, 4 },
                                      { 4, 4, 4, 5, 4 },
                                      { 5, 4, 3, 3, 5 },
                                      { 3, 5, 3, 5, 3 },
                                      { 4, 2, 4, 5, 5 },
                                      { 3, 5, 3, 5, 3 },
                                      { 5, 3, 4, 3, 4 },
                                      { 4, 5, 3, 4, 3 }
                                };

            int[,] A1 = {             { 0, 1, 0, 0, 0 },
                                      { 0, 0, 0, 0, 0 },
                                      { 0, 0, 0, 0, 0 },
                                      { 0, 0, 0, 0, 1 },
                                      { 0, 0, 0, 0, 0 }
                                };
            int[,] A2 = {       { 0, 0, 0, 0, 0 },
                                      { 0, 0, 1, 0, 0 },
                                      { 0, 0, 0, 1, 0 },
                                      { 0, 0, 0, 0, 0 },
                                      { 0, 0, 0, 0, 0 }
                              };


            TPR3 obj = new TPR3(K, A1, A2);
            Console.WriteLine("Матрица K - cкалярные оценки Kji критериев Kj для решений Xi:");
            obj.Printarray(K);
            Console.WriteLine("Матрица предпочтений для критериев:");
            obj.Printarray(A1);
            Console.WriteLine("Матрица эквивалентности для критериев:");
            obj.Printarray(A2);
            Console.WriteLine("Измененная матрица Kji:");
            obj.Mainfunction();
            obj.Printresult();
            Console.ReadKey();
        }
        public class TPR3 {
            private int[,] K;
            private int[,] A1;
            private int[,] A2;
            private int[] X_teta;
            private int count_solution = 8;
            private int count_mark = 5;

            public TPR3(int[,] K, int[,] A1, int[,] A2)
            {
                this.K = new int[count_solution, count_mark];
                this.A1 = new int[count_mark, count_mark];
                this.A2 = new int[count_mark, count_mark];
                X_teta = new int[count_solution];
                this.K = K;
                this.A1 = A1;
                this.A2 = A2;
            }

            protected void Initializevector()
            {
                for (int i = 0; i < count_solution; i++)
                    X_teta[i] = 1;
            }

             public void Printarray(int[,] array)
            {
                for (int i = 0; i < array.GetLength(0); i++)
                {
                    for (int j = 0; j < array.GetLength(1); j++)
                        Console.Write("{0} ", array[i, j]);
                    Console.WriteLine();
                }
                Console.WriteLine();
            }

             public void Printresult()
            {
                Console.WriteLine("Вектор несравнимых решений: ");
                for (int i = 0; i < count_solution; i++)
                    if (X_teta[i] == 1)
                        Console.Write(i + 1 + " ");
            }

            public void Printvector(int[] a)
            {
                for (int i = 0; i < count_mark; i++)
                    Console.Write("{0} ", a[i]);
                Console.WriteLine();
            }

            protected bool Pref(int[] a, int[] b)
            {
                bool flag = true;
                int equalscount = 0;
                for (int i = 0; i < a.GetLength(0); i++)
                {
                    if (a[i] == b[i])
                        equalscount++;
                    else
                        if (b[i] > a[i]) // если критерии не равны вернём false
                    {
                        flag = false;
                        break;
                    }
                }

                if (flag && (equalscount != count_mark))
                    return true;
                else
                    return false;
            }

            protected bool Pref_for_j(int[] a, int[] b, int j)
            {
                if (a[j] > b[j])
                    return true;
                else
                    return false;
            }

            protected int[] Line_to_vector(int[,] massiv, int number_of_line)
            {
                int[] vector = new int[count_mark];
                for (int i = 0; i < count_mark; i++)
                {
                    vector[i] = massiv[number_of_line, i];
                }
                return vector;
            }

            protected void calculate(bool no_first_tour, bool pref, int _j, int _h)
            {
                int[] timingvector1 = new int[count_mark];
                int[] timingvector1_1 = new int[count_mark];
                int[] timingvector2 = new int[count_mark];
                for (int i = 0; i < count_solution - 1; i++)
                {
                    timingvector1 = Line_to_vector(K, i);
                    if (no_first_tour && pref)
                    {
                        timingvector1_1 = timingvector1;
                        timingvector1 = swap(timingvector1, _j, _h);
                        if (!Pref_for_j(timingvector1_1, timingvector1, _j))
                            break;
                        if (X_teta[i] == 1)
                            Printvector(timingvector1);
                    }
                    if (no_first_tour && (!pref))
                    {
                        timingvector1_1 = timingvector1;
                        timingvector1 = swap(timingvector1, _j, _h);
                        if (Pref(timingvector1_1, timingvector1))
                            break;
                        if (X_teta[i] == 1)
                            Printvector(timingvector1);
                    }

                    for (int j = i + 1; j < count_solution; j++)
                    {
                        timingvector2 = Line_to_vector(K, j);
                        if (Pref(timingvector1, timingvector2))
                            X_teta[j] = 0;
                        else
                            if (Pref(timingvector2, timingvector1))
                            X_teta[i] = 0;
                    }
                }
            }

            protected int[] swap(int[] a, int j, int h)
            {
                int temp = a[j];
                a[j] = a[h];
                a[h] = temp;
                return a;
            }
            public void Mainfunction()
            {
                
                Initializevector();
                calculate(false, true, 0, 0);
                int[] timingvector1 = new int[count_mark];
                 for (int i = 0; i < 4; i++)
                {
                    for (int j = 0; j < 4; j++)
                    {
                        if (A1[i, j] == 1)
                        {
                            calculate(true, true, i, j);
                        }
                    }
                }
               
                for (int i = 0; i < 4; i++)
                {
                    for (int j = 0; j < 4; j++)
                    {
                        if (A2[i, j] == 1)
                        {
                            calculate(true, false, i, j);
                        }
                    }
                }
            }
        }

  }
}
