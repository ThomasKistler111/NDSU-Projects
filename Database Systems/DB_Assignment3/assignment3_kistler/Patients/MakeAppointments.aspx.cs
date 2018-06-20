using System;
using System.Collections.Generic;
using System.Linq;
using System.Data.Objects;
using System.Data.Entity;
using System.Web;
using System.Web.Security;
using System.Web.UI; 
using System.Web.UI.WebControls;

namespace assignment3_kistler.Patients
{
    public partial class MakeAppointments : System.Web.UI.Page
    {
        appointmentDBEntities dbcon = new appointmentDBEntities();
        string name;

        protected void Page_Load(object sender, EventArgs e)
        {
            name = assignment3_kistler.login.uName;
            uNameLabel.Text = "Welcome, " + assignment3_kistler.login.uName;
        }

        protected void LoginStatus1_LoggingOut(object sender, LoginCancelEventArgs e)
        {
            FormsAuthentication.SignOut();
            FormsAuthentication.RedirectToLoginPage();
        }

        protected void Calendar1_SelectionChanged(object sender, EventArgs e)
        {
            TextBoxDate.Text = Calendar1.SelectedDate.ToShortDateString();
        }

        protected void ListBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            //use selecteditem.text to retain am/pm
            TextBoxTime.Text = ListBox1.SelectedItem.Text;
        }

        protected void CancelButton_Click(object sender, EventArgs e)
        {
            Response.Redirect("~/Patients/PatientHome.aspx"); 
        }

        //ensures that 
        protected bool ValidForm()
        {
            if (TextBoxCity.Text.Length < 1)
            {
                ShowMessage("please enter location");
                return false;
            }
            else if (TextBoxHospital.Text.Length < 1)
            {
                ShowMessage("please enter hospital");
                return false;
            }
            else if (TextBoxDept.Text.Length < 1)
            {
                ShowMessage("please enter department");
                return false;
            }
            else if (TextBoxDate.Text.Length < 1)
            {
                ShowMessage("please select a date");
                return false;
            }
            else//if all textboxes are filled
            {
                return true;
            }
        }

        //submit button method
        protected void SubmitButton_Click(object sender, EventArgs e)
        {
            if (ValidForm())
            {
                string time = ListBox1.SelectedValue.ToString();
                TimeSpan t = new TimeSpan();
                if (!TimeSpan.TryParse(time, out t) || time.Length < 2)
                {
                    ShowMessage("Please select a valid time");
                }
                else
                {
                    string location = TextBoxHospital.Text + ", " + TextBoxCity.Text;
                    string dept = TextBoxDept.Text;
                    string reason = TextBoxReason.Text;
                    string provider = TextBoxProvider.Text;
                    DateTime dt = Calendar1.SelectedDate;
                    AppointmentTable aptTable = new AppointmentTable();
                    DateTime newDate = new DateTime(dt.Year, dt.Month, dt.Day, t.Hours, t.Minutes, 0);

                    if (ValidateAppointment(dt))
                    {
                        //populate new db table entry
                        aptTable.PatientName = name;
                        aptTable.Location = location;
                        aptTable.Department = dept;
                        aptTable.Time = t;
                        aptTable.Date = newDate;//date has been assigned in calendar selection changed method
                        aptTable.Reason = TextBoxReason.Text;
                        //Confirmation message=
                        ShowMessage("Your Appointment on: " + newDate.ToShortDateString() + " at: "
                            + newDate.ToShortTimeString() + " has been confirmed.");
                        //save changes
                        dbcon.AppointmentTables.Add(aptTable);
                        dbcon.SaveChanges();
                    }
                }
            }//end if validform()
        }

        //find out if the selected date/time has already been reserved
        //returns true if there are no appointments with the same time
        protected bool ValidateAppointment(DateTime dt)
        {
            var dateStrings = dbcon.AppointmentTables.Local.Select(s=>s.Date.Equals(dt)).ToList();
            if (dateStrings.Count > 0) 
            {
                //if theres an appointment at the same time
                ShowMessage("Sorry, the appointment at " + dt.Hour + ":" + dt.Minute
                    + " on " + dt.ToShortDateString() + "has already been reserved.");
                return false;
            }
            
            //if no matches
            return true;
        }

        protected void TextBoxDept_TextChanged(object sender, EventArgs e)
        {
            //deptChange = true;
        }

        protected void TextBoxCity_TextChanged(object sender, EventArgs e)
        {
            //locationChange = true;
        }

        //simple show message method
        protected void ShowMessage(string message)
        {
            ClientScript.RegisterStartupScript(
                this.GetType(), "myalert", "alert('" + message + "');", true);
        }

        //button to clear the form
        protected void ClearButton_Click(object sender, EventArgs e)
        {
            TextBoxCity.Text = "";
            TextBoxDate.Text = "";
            TextBoxDept.Text = "";
            TextBoxHospital.Text = "";
            TextBoxProvider.Text = "";
            TextBoxTime.Text = "";
            TextBoxReason.Text = "";
        }
    
    }
}