using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TempConvertConsole
{
    class Program
    {
        static void Main(string[] args)
        {
            String input, scale;
            Int32 temperature;
            Boolean exit = false;
            do
            {
                Console.WriteLine(" ");
                Console.WriteLine("Please input temperature and scale(C/F) or 0Q to Exit:");
                input = Console.ReadLine();
                try
                {
                    scale = input.Substring(input.Length -1);
                                
                    temperature = Convert.ToInt32(input.Substring(0, input.Length - 1));
                    scale = scale.ToUpper();
                    if (scale == "Q")
                    {
                        exit = true;
                    }
                    else if (scale == "C")
                    {
                        Console.WriteLine("{0:f1}"+(char)176+"C =  {1:f1}"+(char)176+"F",temperature,  temperature * 9 / 5 + 32);
                    }
                    else if (scale == "F")
                    {
                        Console.WriteLine("{0:f1}"+(char)176+"F =  {1:f1}"+(char)176+"C",temperature, (temperature - 32) * 5 / 9);
                    }
                    else
                    {
                        Console.WriteLine("Please input the correct temperature scale.");
                        Console.WriteLine("C for Celcius or F for Fehrenheit or Q to Quite: ");
                    }
                }
                catch (Exception ex)
                {

                    Console.WriteLine(ex.Message);
                }

            } while (exit == false);
        }
    }
}
