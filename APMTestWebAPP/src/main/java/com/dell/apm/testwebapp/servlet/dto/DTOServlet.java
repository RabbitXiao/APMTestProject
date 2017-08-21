package com.dell.apm.testwebapp.servlet.dto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class DTOServlet extends HttpServlet{

    public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{
        execute(req, res);
    }

    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
        execute(req, res);
    }

    protected void execute(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
        StringBuilder sb = new StringBuilder();
        VertragDTO vertragDTO = new VertragDTO();
        VertragEntityDTO vertragEntityDTO = new VertragEntityDTO(vertragDTO);

        outputContent(res, vertragEntityDTO.getContent());
    }

    private void outputContent(HttpServletResponse res, String content) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><head><title>DTOServlet</title></head>");
        out.println("<body> current Time:" + new Date() + "<br/>" + content + "</body></html>");
        out.flush();
    }

    protected void test() {
        KontofondsDTO dto1 = new KontofondsDTO();
        KontofondsDTO dto2 = new KontofondsDTO();

        ArrayList kfeList = new ArrayList();
        kfeList.add(new KontofondsEntityDTO(dto1));
        kfeList.add(new KontofondsEntityDTO(dto2));
        kontoFonds = kfeList;
    }

    public static class KontofondsDTO {

    }
    public static class KontofondsEntityDTO {
        private KontofondsDTO dto;

        public KontofondsEntityDTO(KontofondsDTO dto) {
            this.dto = dto;
        }
    }
    private List<KontofondsEntityDTO> kontoFonds;
}
