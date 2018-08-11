using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;
using System.Windows.Forms;

namespace ConvertTempForm
{
    class Program
    {
        static Form convertTemperatureForm;
        static void Main(string[] args)
        {
            convertTemperatureForm = new Form();

            Label lblInput = new Label();
            lblInput.Size = new Size(100, 20);
            lblInput.Text = "Input Value: ";
            lblInput.Location = new Point(10, 10);
            convertTemperatureForm.Controls.Add(lblInput);

            TextBox txtInput = new TextBox();
            txtInput.Size = new Size(100, 20);
            txtInput.Location = new Point(120, 10);
            convertTemperatureForm.Controls.Add(txtInput);

            Label lblResult = new Label();
            lblResult.Size = new Size(100, 20);
            lblResult.Text = "Results";
            lblResult.Location = new Point(10, 40);
            convertTemperatureForm.Controls.Add(lblResult);

            TextBox txtResult = new TextBox();


            Button btnCelcius = new Button();
            btnCelcius.Size = new Size(100, 40);
            btnCelcius.Location = new Point(10, 130);
            btnCelcius.Text = "Convert To Celcius";
            convertTemperatureForm.Controls.Add(btnCelcius);

            Button btnFehrenheit = new Button();
            btnFehrenheit.Size = new Size(100, 40);
            btnFehrenheit.Location = new Point(120, 130);
            btnFehrenheit.Text = "Convert To Fahrenheit";
            convertTemperatureForm.Controls.Add(btnFehrenheit);

            Application.Run(convertTemperatureForm);
        }
    }
}
