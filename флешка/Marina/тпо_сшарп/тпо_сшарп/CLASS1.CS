using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace tpo2
{
    public class car
    {
        public String marka { get; set; }

        public int year { get; set; }

        public override string ToString()
        {
            StringBuilder sb = new StringBuilder();
            sb.Append("car marka: ");
            sb.Append(marka);
            sb.Append("; year: ");
            sb.Append(year);
            sb.Append(";");
            return sb.ToString();
        }

    }
    public class listCar : List<car>
        
    {
    
 
        public virtual void print()
        {
            foreach (car c in this)
            {
                Console.WriteLine(c.ToString());
            }
  
        }

        public void addCar(listCar listOne, String marka_, int year_) {

            listOne.Add(new car() { marka =marka_, year = year_ });
        }
       

    }

    public class Log
    {

        static private StreamWriter log = new StreamWriter("log.log");
        static public void Add(string msg)
        {
            log.WriteLine(msg);
        }
        static public void Close()
        {
            log.Close();
        }
    }

    abstract class Tester
    {
        protected void LogMessage(string s)
        //Добавление сообщения в лог-файл
        {
            Log.Add(s);
        }
    }

    class carTester : Tester // Тестовый драйвер
    {
        car OUT;
        public carTester()
        {
            OUT = new car();
            Run();
        }
        private listCar init()
        {
            listCar listOne=new listCar();
            return listOne;
        }
        private void Run()
        {
            carTest1(init());
        }
        private void carTest1(listCar listOne)
        {
            listOne.addCar(listOne, "toyota", 1995);
            listOne.addCar(listOne, "mazda", 2000);
            listOne.addCar(listOne, "nissan", 2010);
            
            foreach (car c in listOne)
            {
                Log.Add(c.ToString());
                Console.WriteLine(c.ToString());
            }
        }
            static void Main()
            {
                carTester CommandTester = new carTester();
                Log.Close();
            Console.ReadLine();

            }
        }
    }
