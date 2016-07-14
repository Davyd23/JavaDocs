<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ro.teamnet.zth.appl.dao.LocationDao" %>
<%@ page import="ro.teamnet.zth.appl.domain.Location" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Locations List</title>
</head>
<body>
<table border="1">
    <thead>
    <tr>
        <td>Id</td>
        <td>Street address</td>
        <td>Postal code</td>
        <td>City</td>
        <td>State province</td>
    </tr>
    </thead>
    <tbody>
    <%
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    %>
    <tr>
        <!--TODO de completat cu cod pentru a afisa detaliile locatiei cu id-ul trimis din locationlist.jsp in momentul in care se acceseaza link-ul 'View'-->
         <%
            String url=request.getQueryString();
            String[] tokens=url.split("=");

            Location viewLocation=new LocationDao().getLocationById(Integer.parseInt(tokens[1]));
         %>
        <td><%=viewLocation.getId()%></td>
        <td><%=viewLocation.getStreetAddress()%></td>
        <td><%=viewLocation.getPostalCode()%></td>
        <td><%=viewLocation.getCity()%></td>
        <td><%=viewLocation.getStateProvince()%></td>

    </tr>

    </tbody>
</table>
<a href="locationList.jsp">Locations List</a>
</body>
</html>
