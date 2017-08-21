package com.dell.apm.testwebapp.servlet;

import com.dell.apm.testwebapp.com.dell.apm.testwebapp.servlet.util.HttpConnUtil;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: rlin2
 * Date: 14-7-3
 * Time: 下午5:05
 * To change this template use File | Settings | File Templates.
 */
public class VisitTheSameLinkServlet extends HttpServlet{

    private String configHost;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);    //To change body of overridden methods use File | Settings | File Templates.
        configHost = config.getInitParameter("configHost");
    }

    public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{
        HttpURLConnection urlConn = null;
        try{
            /*String calls = req.getParameter("count");
            int count = 1;
            if( calls != null ){
                count = Integer.parseInt( calls );
            }*/

            int count = Integer.parseInt(req.getParameter("count"));

            if (count <= 0) {
                outputContent(res, "Stop call. count=" + count);
                return ;
            }

            long t0 = System.currentTimeMillis();
            String url = buildUrl(count);

            String responseContent = HttpConnUtil.visitURL(url);
            long spentTime = System.currentTimeMillis() - t0;

            StringBuilder sb = new StringBuilder();
            sb.append("Visit link: " + url + "<br/>");
            sb.append("Spent Time: " + spentTime + "ms. <br/>");
            sb.append("Response Content:<br/> " + responseContent);
            outputContent(res, sb.toString());
        }catch(Exception ex){
            ex.printStackTrace();
        } finally {
            if (urlConn != null) {
                urlConn.disconnect();
            }
        }
    }

    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{
        this.doGet(req, res);
    }

    private void outputContent(HttpServletResponse res, String content) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><head><title>VisitTheSameLinkServlet</title></head>");
        out.println("<body> current Time:" + new Date() + "<br/>" +content +"</body></html>");
        out.flush();
    }

    private String buildUrl(int count) {
        StringBuilder sb = new StringBuilder();
        sb.append(configHost);
        sb.append("/APMTestWebApp/action/VisitLink");
        sb.append("?count=");
        sb.append(count-1);
        return sb.toString();
    }

}
