using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
 
namespace assignment3_kistler.Patients
{
    public partial class ViewAppointments : System.Web.UI.Page
    {
        appointmentDBEntities dbcon = new appointmentDBEntities();

        protected void Page_Load(object sender, EventArgs e)
        {
            nameLabel.Text = "for " + assignment3_kistler.login.uName;
            uNameLabel.Text = "Welcome, " + assignment3_kistler.login.uName;
            nameLabel.Text = "for: " + assignment3_kistler.login.uName;
        }

        protected void LoginStatus1_LoggingOut(object sender, LoginCancelEventArgs e)
        {
            FormsAuthentication.SignOut();
            FormsAuthentication.RedirectToLoginPage();
        }

        protected void CancelAptButton_Click(object sender, EventArgs e)
        {
            if(GridView1.SelectedValue != null)
            {
                int removeID = Convert.ToInt32(GridView1.SelectedDataKey.Value);
                AppointmentTable toRemove = (from apt in dbcon.AppointmentTables
                                             where apt.IdNumber == removeID
                                             select apt).FirstOrDefault();
                dbcon.AppointmentTables.Remove(toRemove);
                dbcon.SaveChanges();
                GridView1.DataBind();  
            }            
            else
            {
                ShowMessage("Please select a row before deleting.");
            }
                
        }

        //simple show message method
        protected void ShowMessage(string message)
        {
            ClientScript.RegisterStartupScript(
                this.GetType(), "myalert", "alert('" + message + "');", true);
        }

        protected void GridView1_RowDeleting(object sender, GridViewDeleteEventArgs e)
        {

        }

        protected void GridView1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }
    }
}