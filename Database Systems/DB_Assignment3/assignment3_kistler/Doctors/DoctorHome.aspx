<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="DoctorHome.aspx.cs" Inherits="assignment3_kistler.Doctors.DoctorHome" %>

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
    
        <asp:Label ID="Label1" runat="server" Text="Doctors Home Page" Font-Size="Large"></asp:Label>
        <br />
        <br />
        <asp:HyperLink ID="HyperLink1" runat="server" NavigateUrl="~/PageNotMadeYet.aspx">View Patients</asp:HyperLink>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <asp:HyperLink ID="HyperLink2" runat="server" NavigateUrl="~/PageNotMadeYet.aspx">Search Patients</asp:HyperLink>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <asp:HyperLink ID="HyperLink3" runat="server" NavigateUrl="~/PageNotMadeYet.aspx">Email Patients</asp:HyperLink>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <asp:LoginStatus ID="LoginStatus1" runat="server" LoginText="Logout" LogoutAction="Redirect" LogoutPageUrl="~/login.aspx" LogoutText="Login" OnLoggingOut="LoginStatus1_LoggingOut" />
&nbsp;<asp:Label ID="uNameLabel" runat="server" Text="Label"></asp:Label>
    
    </div>
    &nbsp;&nbsp;&nbsp;
    </form>
</body>
</html>
