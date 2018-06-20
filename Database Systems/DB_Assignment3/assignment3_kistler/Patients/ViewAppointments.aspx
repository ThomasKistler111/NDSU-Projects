<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="ViewAppointments.aspx.cs" Inherits="assignment3_kistler.Patients.ViewAppointments" %>

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
    
        <asp:Label ID="Label1" runat="server" Text="View Appointments" Font-Size="Large"></asp:Label>
        <br />
        <hr />
        <asp:HyperLink ID="HyperLink1" runat="server" NavigateUrl="~/Patients/PatientHome.aspx">Home</asp:HyperLink>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <asp:HyperLink ID="HyperLink2" runat="server" NavigateUrl="~/Patients/MakeAppointments.aspx">Make Appointments</asp:HyperLink>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <asp:HyperLink ID="HyperLink3" runat="server" NavigateUrl="~/PageNotMadeYet.aspx">Patient Email</asp:HyperLink>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <asp:LoginStatus ID="LoginStatus1" runat="server" LoginText="Logout" LogoutText="Login" OnLoggingOut="LoginStatus1_LoggingOut" />
&nbsp;<asp:Label ID="uNameLabel" runat="server" Text="Label"></asp:Label>
    
        <br />
        <hr />
        <br />
        <asp:Label ID="Label2" runat="server" Text="Upcomming Appointments"></asp:Label>
        &nbsp;<asp:Label ID="nameLabel" runat="server" Text="for: "></asp:Label>
        <br />
        <asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="False" CellPadding="4" DataKeyNames="IdNumber" DataSourceID="EntityDataSource2" ForeColor="#333333" GridLines="None" OnRowDeleting="GridView1_RowDeleting" OnSelectedIndexChanged="GridView1_SelectedIndexChanged" Width="664px">
            <AlternatingRowStyle BackColor="White" ForeColor="#284775" />
            <Columns>
                <asp:CommandField ShowSelectButton="True" />
                <asp:BoundField DataField="Location" HeaderText="Hospital, City" ReadOnly="True" SortExpression="Location" />
                <asp:BoundField DataField="Date" HeaderText="Date &amp; Time" ReadOnly="True" SortExpression="Date" />
                <asp:BoundField DataField="Department" HeaderText="Department" ReadOnly="True" SortExpression="Department" />
                <asp:BoundField DataField="IdNumber" HeaderText="IdNumber" ReadOnly="True" SortExpression="IdNumber" Visible="False" />
            </Columns>
            <EditRowStyle BackColor="#999999" />
            <EmptyDataRowStyle BorderStyle="Dashed" />
            <FooterStyle BackColor="#5D7B9D" Font-Bold="True" ForeColor="White" />
            <HeaderStyle BackColor="#5D7B9D" Font-Bold="True" ForeColor="White" />
            <PagerStyle BackColor="#284775" ForeColor="White" HorizontalAlign="Center" />
            <RowStyle BackColor="#F7F6F3" ForeColor="#333333" />
            <SelectedRowStyle BackColor="#E2DED6" Font-Bold="True" ForeColor="#333333" />
            <SortedAscendingCellStyle BackColor="#E9E7E2" />
            <SortedAscendingHeaderStyle BackColor="#506C8C" />
            <SortedDescendingCellStyle BackColor="#FFFDF8" />
            <SortedDescendingHeaderStyle BackColor="#6F8DAE" />
        </asp:GridView>
        <asp:EntityDataSource ID="EntityDataSource2" runat="server" ConnectionString="name=appointmentDBEntities" DefaultContainerName="appointmentDBEntities" EnableFlattening="False" EntitySetName="AppointmentTables" Select="it.[Location], it.[Time], it.[Date], it.[Department], it.[IdNumber]">
        </asp:EntityDataSource>
        <br />
        <asp:Button ID="CancelAptButton" runat="server" OnClick="CancelAptButton_Click" Text="Cancel Appointment" />
    
    </div>
    </form>
</body>
</html>
