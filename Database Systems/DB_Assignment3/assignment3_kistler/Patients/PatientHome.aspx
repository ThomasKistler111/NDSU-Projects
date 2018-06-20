<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="PatientHome.aspx.cs" Inherits="assignment3_kistler.Patients.PatientHome" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
        <asp:Label ID="Title" runat="server" Font-Bold="True" Font-Italic="False" Font-Size="X-Large" Font-Underline="True" ForeColor="#0033CC" Text="Hospital Online System"></asp:Label>
        <br />
        <br />
    
        <asp:Label ID="Label1" runat="server" Text="Patient's Home Page" Font-Size="Large"></asp:Label>
        <br />
        <hr />
        <asp:HyperLink ID="HyperLink1" runat="server" NavigateUrl="~/Patients/MakeAppointments.aspx">Make Appointments</asp:HyperLink>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <asp:HyperLink ID="HyperLink2" runat="server" NavigateUrl="~/Patients/ViewAppointments.aspx">View Appointment(s)</asp:HyperLink>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <asp:HyperLink ID="HyperLink3" runat="server" NavigateUrl="~/PageNotMadeYet.aspx">Patient Email</asp:HyperLink>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <asp:LoginStatus ID="LoginStatus1" runat="server" LoginText="Logout" LogoutAction="Redirect" LogoutPageUrl="~/login.aspx" LogoutText="Login" OnLoggingOut="LoginStatus1_LoggingOut" />
&nbsp;<asp:Label ID="uNameLabel" runat="server" Text="Label"></asp:Label>
    
        <hr />
    
    </div>
    </form>
</body>
</html>
