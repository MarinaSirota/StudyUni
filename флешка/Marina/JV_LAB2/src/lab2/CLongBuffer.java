package lab2;
	import java.util.Random;
import java.io.*;

	public class CLongBuffer extends CBuffer
			implements IBufferComputable, IBufferSortable, IBufferStorable, IBufferPrintable {

		protected long T[];

		public CLongBuffer(int count) {
			super(count);
			T = new long[count];
			generate();
		}

		@Override
		public void generate() {
			Random rand = new Random();
			int buffSize = getBufSize();
			for (int i = 0; i < buffSize; i++) {
				T[i] = rand.nextLong();
			}
		}

		@Override
		public void Max() {
			long sum = T[0];
			for (int i = 1; i < bufSize; i++) {
				if(T[i]>sum)
					sum=T[i];
			}
			System.out.print("Max: " + sum + '\n');
		}

		@Override
		public void Sort() {
			
			int m;
	        long tmp;
	        for(int i=0;i<bufSize-1;i++) {
	            m = i;
	            for(int j=i+1;j<bufSize;j++) {
	                if(T[j] < T[m]) {
	                    m = j;
	                }
	            }
	            if(m != i) {
	                tmp = T[i];
	                T[i] = T[m];
	                T[m] = tmp;
	            }
	        }
			
		}

		@Override
		public void PrintInfo() {
			System.out.print('\t' + "Номер буфера: " + bufID + " Размер: " + bufSize + '\n');
		}

		@Override
		public void Print() {
			for (int i = 0; i < bufSize; i++) {
				System.out.printf("%f ; ", T[i]);
			}
			System.out.print('\n');
		}

		@Override
		public void PrintFirstN(int n) {
			for (int i = 0; i < Math.min(bufSize, n); i++) {
				System.out.printf("%d ; ", T[i]);
			}
			System.out.print('\n');
		}

		@Override
		public void PrintLastN(int n) {
			for (int i = Math.max(0, bufSize - n); i < bufSize; i++) {
				System.out.printf("%d ; ", T[i]);
			}
			System.out.print('\n');
		}

		@Override
		public void SaveSeparateLine(String filename) {
			PrintFile(filename, '\n');
		}

		private void PrintFile(String filename, char separator) {
			try (PrintWriter out = new PrintWriter(new FileOutputStream(filename, true))) {
				for (int i = 0; i < bufSize; i++) {
					out.print(T[i]);
					out.print(separator);
				}
				out.print('\n');
				out.close();
			} catch (FileNotFoundException e) {
				System.out.print("Не найден файл: " + filename);
				e.printStackTrace();
			}
		}

	}
	
	
	


