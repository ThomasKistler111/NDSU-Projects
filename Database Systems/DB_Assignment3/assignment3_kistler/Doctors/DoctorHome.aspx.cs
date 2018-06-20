using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace assignment3_kistler.Doctors
{
    public partial class DoctorHome : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            uNameLabel.Text = "Welcome, " + assignment3_kistler.login.uName;
        }

        protected void LoginStatus1_LoggingOut(object sender, LoginCancelEventArgs e)
        {
            FormsAuthentication.SignOut();
            FormsAuthentication.RedirectToLoginPage();
        }
    }
}