package com.dell.apm.testwebapp.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by rxiao on 7/22/14.
 */
public class NeverReturnServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {
          while (true) {
              try {
                  Thread.sleep(100 * 1000L);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
    }
}
