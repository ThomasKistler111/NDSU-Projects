<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="MakeAppointments.aspx.cs" Inherits="assignment3_kistler.Patients.MakeAppointments" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
    <style type="text/css">
        .auto-style9 {
            width: 326px;
            height: 232px;
        }
        .auto-style12 {
            width: 664px;
            height: 232px;
        }
        .auto-style18 {
            height: 118px;
            width: 664px;
        }
        .auto-style19 {
            height: 118px;
            width: 326px;
        }
        #TextArea1 {
            height: 95px;
            width: 676px;
        }
        #ReasonTextArea {
            height: 90px;
            width: 672px;
        }
        .auto-style22 {
            height: 122px;
        }
        .auto-style23 {
            width: 326px;
            height: 98px;
        }
        .auto-style24 {
            width: 664px;
            height: 98px;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
        <asp:Label ID="Title" runat="server" Font-Bold="True" Font-Italic="False" Font-Size="X-Large" Font-Underline="True" ForeColor="#0033CC" Text="Hospital Online System"></asp:Label>
        <br />
        <br />
    
        <asp:Label ID="Label1" runat="server" Text="Make Appointments" Font-Size="Large"></asp:Label>
        <br />
        <hr />
        <asp:HyperLink ID="HyperLink1" runat="server" NavigateUrl="~/Patients/PatientHome.aspx">Home</asp:HyperLink>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <asp:HyperLink ID="HyperLink2" runat="server" NavigateUrl="~/Patients/ViewAppointments.aspx">View Appointments</asp:HyperLink>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <asp:HyperLink ID="HyperLink3" runat="server" NavigateUrl="~/PageNotMadeYet.aspx">Patient Email</asp:HyperLink>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <asp:LoginStatus ID="LoginStatus1" runat="server" LoginText="Logout" LogoutText="Login" OnLoggingOut="LoginStatus1_LoggingOut" />
&nbsp;<asp:Label ID="uNameLabel" runat="server" Text="Label"></asp:Label>
    
    </div>
        <hr />
        <br />
        <asp:Label ID="Label9" runat="server" Text=" * indicates required field"></asp:Label>
        <br />
        <table style="width: 100%; height: 224px;" aria-orientation="vertical">
            <tr>
                <td class="auto-style22" colspan="2">
                    <asp:Label ID="Label8" runat="server" Text="Reason for Visit:"></asp:Label>
                    &nbsp;*<br />
                    <asp:TextBox ID="TextBoxReason" runat="server" Height="78px" TextMode="MultiLine" Width="629px"></asp:TextBox>
                    &nbsp;</td>
            </tr>
            <tr>
                <td class="auto-style23" aria-orientation="horizontal">
                    <asp:Label ID="Label3" runat="server" Text="Hospital: "></asp:Label>
&nbsp;&nbsp; <asp:TextBox ID="TextBoxHospital" runat="server" Width="183px"></asp:TextBox>
                    &nbsp;*&nbsp;
                    <asp:Label ID="Label4" runat="server" Text="Dept.: "></asp:Label>
&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
                    <asp:TextBox ID="TextBoxDept" runat="server" OnTextChanged="TextBoxDept_TextChanged" Width="188px"></asp:TextBox>
                &nbsp;*</td>
                <td class="auto-style24">
&nbsp;
                    <asp:Label ID="Label2" runat="server" Text="City: "></asp:Label>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <asp:TextBox ID="TextBoxCity" runat="server" OnTextChanged="TextBoxCity_TextChanged"></asp:TextBox>
                    &nbsp;*<br />
&nbsp;
                    <asp:Label ID="Label5" runat="server" Text="Provider: "></asp:Label>
                    <asp:TextBox ID="TextBoxProvider" runat="server"></asp:TextBox>
                &nbsp;*</td>
            </tr>
            <tr>
                <td class="auto-style9">
                    &nbsp;&nbsp;
                    <asp:Label ID="Label6" runat="server" Text="Time: "></asp:Label>
                    &nbsp; <asp:TextBox ID="TextBoxTime" runat="server" Width="158px"></asp:TextBox>
                    &nbsp;*<br />
&nbsp;<asp:ListBox ID="ListBox1" runat="server" OnSelectedIndexChanged="ListBox1_SelectedIndexChanged" style="margin-left: 0px" Width="251px">
                        <asp:ListItem Value="07:00">7:00am</asp:ListItem>
                        <asp:ListItem Value="08:00">8:00am</asp:ListItem>
                        <asp:ListItem Value="09:00">9:00am</asp:ListItem>
                        <asp:ListItem Value="10:00">10:00am</asp:ListItem>
                        <asp:ListItem Value="11:00">11:00am</asp:ListItem>
                        <asp:ListItem Value="12:00">12:00pm</asp:ListItem>
                        <asp:ListItem Value="13:00">1:00pm</asp:ListItem>
                        <asp:ListItem Value="14:00">2:00pm</asp:ListItem>
                        <asp:ListItem Value="15:00">3:00pm</asp:ListItem>
                        <asp:ListItem Value="16:00">4:00pm</asp:ListItem>
                        <asp:ListItem Value="17:00">5:00pm</asp:ListItem>
                    </asp:ListBox>
                    <br />
                    <br />
                    <br />
                    <br />
                </td>
                <td class="auto-style12">
                    &nbsp;&nbsp;&nbsp;
                    <asp:Label ID="Label7" runat="server" Text="Date: "></asp:Label>
&nbsp;&nbsp;&nbsp;&nbsp;
                    <asp:TextBox ID="TextBoxDate" runat="server" TextMode="DateTime"></asp:TextBox>
                    &nbsp;*<asp:Calendar ID="Calendar1" runat="server" OnSelectionChanged="Calendar1_SelectionChanged" Height="154px" style="margin-left: 4px" Width="293px"></asp:Calendar>
                &nbsp;</td>
            </tr>
            <tr>
                <td class="auto-style19">&nbsp;
                    <asp:Button ID="SubmitButton" runat="server" OnClick="SubmitButton_Click" Text="Schedule Appointment" />
&nbsp;<br />
                    <br />
                </td>
                <td class="auto-style18">&nbsp;<asp:Button ID="ClearButton" runat="server" OnClick="ClearButton_Click" Text="Clear" />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <asp:Button ID="CancelButton" runat="server" OnClick="CancelButton_Click" Text="Cancel" />
                    <br />
                    <br />
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
