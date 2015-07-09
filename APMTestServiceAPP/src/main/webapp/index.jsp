<%
String requestPath = request.getServletPath();
String requestURL = request.getRequestURL().toString();
String servletPath = request.getServletPath();
String contextPath = request.getSession().getServletContext().getContextPath();
%>
<html>
<body>

<h2>sessionID:<%=session.getId() %></h2>
<ul>
    <li>requestPath: <%=requestPath%></li>
    <li>requestURL: <%=requestURL%></li>
    <li>servletPath: <%=servletPath%></li>
    <li>contextPath: <%=contextPath%></li>
</ul>

<h3>Our App includes following Services:</h3>
<h4>Web Service</h4>
<ul>
    <li><a href="<%=requestURL%>webservice/sayHello">/webservice/sayHello</a></li>
</ul>
</body>
</html>
