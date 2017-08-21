<%
String requestPath = request.getServletPath();
String requestURL = request.getRequestURL().toString();
String servletPath = request.getServletPath();
String contextPath = request.getSession().getServletContext().getContextPath();
String localAddr = request.getLocalAddr();
int localPort = request.getLocalPort();
%>
<html>
<body>

<h2>sessionID:<%=session.getId() %></h2>
<ul>
    <li>requestPath: <%=requestPath%></li>
    <li>requestURL: <%=requestURL%></li>
    <li>servletPath: <%=servletPath%></li>
    <li>contextPath: <%=contextPath%></li>
    <li>localAddr: <%=localAddr%></li>
    <li>localPort: <%=localPort%></li>
</ul>

<h3>Our App includes following Links:</h3>

<h4>Poor/Fair Hits</h4>
The jsp page will sleep 0-30 seconds randomly.
<ul>
    <li><a href="<%=requestURL%>random.jsp">/random.jsp</a></li>
</ul>
Visit a special URL /action/sleepAFixedTime with a fixed time.
<ul>
     <li><a href="<%=requestURL%>action/sleepAFixedTime?sleepTime=3">/action/sleepAFixedTime?sleepTime=3</a></li>
</ul>

<h4>DepartmentServlet</h4>
<ul>
    <li><a href="<%=requestURL%>action/department">/action/department</a></li>
</ul>

<h4>DTOServlet</h4>
<ul>
    <li><a href="<%=requestURL%>action/dto">/action/dto</a></li>
</ul>

<h4>Error Hits</h4>
Response code is 500.
<ul>
    <li><a href="<%=requestURL%>action/errorResponseCode">/action/errorResponseCode</a></li>
</ul>
Visit a special URL /action/throwException to throw an exception.
<ul>
    <li><a href="<%=requestURL%>action/throwException?exceptionName=java.lang.NullPointerException">/action/throwException?exceptionName=java.lang.NullPointerException</a></li>
</ul>
Visiting a link that never return.
<ul>
    <li><a href="<%=requestURL%>action/neverReturn">/action/neverReturn</a></li>
</ul>

<h4>DataSource</h4>
Accessing a MYSQL datasource, you can modify the datasource config in file: <b>webapp/META-INF/context.xml</b>.
Adding patermater sleepTime can let the servlet sleep a fixed time.
<ul>
    <li><a href="<%=requestURL%>action/accessMySQL?sleepTime=3">/action/accessMySQL?sleepTime=3</a></li>
</ul>
Acquire multiple MYSQL datasource without releasing them</b>.
<ul>
    <li><a href="<%=requestURL%>action/acquireMultipleDataSource?dataSourceCount=5&release=false">/action/acquireMultipleDataSource?dataSourceCount=5&release=false</a></li>
</ul>

<h4>Web Service</h4>
Accessing a webservice. You can modify the Webservice server IP and port in file: <b>webapp/META-INF/wsdl/HelloWs.wsdl</b>
<ul>
    <li><a href="<%=requestURL%>action/sayHello?content=Rabbit">/action/sayHello?content=Rabbit</a></li>
</ul>

<h4>Visit a special URL by /action/VisitUrl</h4>
<ul>
    <%
        StringBuilder sb = new StringBuilder();
        sb.append("http://");
        sb.append(localAddr);
        sb.append(":");
        sb.append(localPort);
        sb.append("/");
        String urlPrefix = sb.toString();
    %>
    <li><a href="<%=requestURL%>action/VisitUrl?count=1&url=<%=urlPrefix%>examples/jsp">/action/VisitUrl?count=1&url=<%=urlPrefix%>examples/jsp</a></li>
</ul>

<h4>Visit a special URL /action/VisitLink in the defined host for several times.</h4>
<ul>
    <li><a href="<%=requestURL%>action/VisitLink?count=5">/action/VisitLink?count=3</a></li>
</ul>






</body>
</html>
