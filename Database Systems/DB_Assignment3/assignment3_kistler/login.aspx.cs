using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace assignment3_kistler
{
    public partial class login : System.Web.UI.Page
    {
        bool selectedUserType = false;
        public static string uName;

        protected void Page_Load(object sender, EventArgs e)
        {
            UnobtrusiveValidationMode = System.Web.UI.UnobtrusiveValidationMode.None;
        }

        protected void Login1_Authenticate(object sender, AuthenticateEventArgs e)
        {
            bool isDoc = false;
            uName = Login1.UserName;

            if (!selectedUserType)
            {
                showMessage("Please Select User Type: (Doctor or Patient)");

            }
            else//if user type has been selected
            {
                if (DropDownList1.SelectedValue.Equals("Doctor"))
                    isDoc = true;

                if ((Login1.UserName == "abc" && Login1.Password == "123")
                     || Login1.UserName == "123" && Login1.Password == "abc")
                {
                    if (isDoc)
                    {
                        Response.Redirect("~/Doctors/DoctorHome.aspx");
                    }
                    else//if its a patient
                    {
                        Response.Redirect("~/Patients/PatientHome.aspx");
                    }
                }
            }//end selected user
        }//end authenticate

        public void showMessage(string message)
        {
            ClientScript.RegisterStartupScript(
                this.GetType(), "myalert",
                "alert('" + message + "');", true
                );
        }

        protected void DropDownList1_SelectedIndexChanged(object sender, EventArgs e)
        {
            selectedUserType = true;
        }
    }
}