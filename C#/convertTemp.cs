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
        static TextBox txtResult;
        static TextBox txtInput;

        static void Main(string[] args)
        {
            convertTemperatureForm = new Form();
            convertTemperatureForm.Size = new Size(260, 155);
            convertTemperatureForm.Text = "Convert Temperature";

            Label lblInput = new Label();
            lblInput.Size = new Size(100, 20);
            lblInput.Text = "Input Value: ";
            lblInput.Location = new Point(20, 10);
            convertTemperatureForm.Controls.Add(lblInput);

            txtInput = new TextBox();
            txtInput.Size = new Size(100, 20);
            txtInput.Location = new Point(130, 10);
            convertTemperatureForm.Controls.Add(txtInput);

            Label lblResult = new Label();
            lblResult.Size = new Size(100, 20);
            lblResult.Text = "Results";
            lblResult.Location = new Point(20, 40);
            convertTemperatureForm.Controls.Add(lblResult);

            txtResult = new TextBox();
            txtResult.Size = new Size(100, 20);
            txtResult.Location = new Point(130, 40);
            txtResult.ReadOnly = true;
            convertTemperatureForm.Controls.Add(txtResult);
            
            Button btnCelcius = new Button();
            btnCelcius.Size = new Size(100, 40);
            btnCelcius.Location = new Point(20, 70);
            btnCelcius.Text = "Convert To Celcius";
            convertTemperatureForm.Controls.Add(btnCelcius);
            btnCelcius.Click += calculateCelcius;

            Button btnFehrenheit = new Button();
            btnFehrenheit.Size = new Size(100, 40);
            btnFehrenheit.Location = new Point(130, 70);
            btnFehrenheit.Text = "Convert To Fahrenheit";
            convertTemperatureForm.Controls.Add(btnFehrenheit);
            btnFehrenheit.Click += calculateFehrenheit;
            Application.Run(convertTemperatureForm);
        }

        private static void calculateFehrenheit(object sender, EventArgs e)
        {
            try
            {
                txtResult.Text = String.Format("{0:f1} \u00b0 F ", Convert.ToDouble(txtInput.Text) * 9 / 5 + 32);
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }

        }

        private static void calculateCelcius(object sender, EventArgs e)
        {
            try
            {
                txtResult.Text = String.Format("{0:f1}\u00b0 C ", (Convert.ToDouble(txtInput.Text)-32)*5/9);
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message,"Error",MessageBoxButtons.OK,MessageBoxIcon.Error);
            }
        }
    }
}
