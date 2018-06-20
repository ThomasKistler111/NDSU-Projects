<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="login.aspx.cs" Inherits="assignment3_kistler.login" %>

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
    
        <asp:Label ID="Label1" runat="server" Text="Login Page" Font-Size="Large"></asp:Label>
    
        <hr />
        <br />
    
    </div>
        <asp:Label ID="Label2" runat="server" Text="Please Select User Type"></asp:Label>
&nbsp;
        <asp:DropDownList ID="DropDownList1" runat="server" OnSelectedIndexChanged="DropDownList1_SelectedIndexChanged">
            <asp:ListItem>---</asp:ListItem>
            <asp:ListItem>Doctor</asp:ListItem>
            <asp:ListItem>Patient</asp:ListItem>
        </asp:DropDownList>
        <br />
        <br />
        <asp:Login ID="Login1" runat="server" OnAuthenticate="Login1_Authenticate">
        </asp:Login>
    </form>
</body>
</html>
