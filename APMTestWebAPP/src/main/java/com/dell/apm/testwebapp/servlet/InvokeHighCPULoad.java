package com.dell.apm.testwebapp.servlet;


import com.dell.apm.testwebapp.HighCPULoad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class InvokeHighCPULoad
 */
public class InvokeHighCPULoad extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public InvokeHighCPULoad() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int loopTimes = HighCPULoad.toInteger(request.getParameter("loopTimes"), 1);
		int minV = HighCPULoad.toInteger(request.getParameter("minV"), 0);
		int maxV = HighCPULoad.toInteger(request.getParameter("maxV"), 1000 * 10000);
		boolean mutlithread = false;
		if ("true".equalsIgnoreCase(request.getParameter("multithread"))) {
			mutlithread = true;
		}
		int threadCount = HighCPULoad.toInteger(request.getParameter("threadCount"), 4);

		if (mutlithread) {
			testWithMultiThreads(loopTimes, minV, maxV, threadCount, response);
		} else {
			testWith1Thread(loopTimes, minV, maxV, response);
		}

	}

	private void testWith1Thread(int loopTimes, int minV, int maxV, HttpServletResponse response) throws IOException {
		StringBuilder sb = new StringBuilder();
		long spendTime = HighCPULoad.highCPULoad("Servlet-Main-Thread", loopTimes, minV, maxV);
		sb.append("######################InvokeHighCPULoad: testWith1Thread loopTimes:" + loopTimes + ", spendTime:" + spendTime + "ms.<br/>");
		output(sb.toString(), response);
	}

	private void testWithMultiThreads(final int loopTimes, final int minV, final int maxV, final int threadCount, HttpServletResponse response) throws IOException {
		StringBuilder sb = new StringBuilder();
		long spendTime = 0;
		try {
			spendTime = HighCPULoad.highCPULoadWithMultiThreads(loopTimes, minV, maxV, threadCount);
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new IOException("Exception happens: " + e.getMessage(), e);
		}
		sb.append("######################InvokeHighCPULoad: testWithMultiThreads:" + loopTimes + ", threadCount:" + threadCount + ", spendTime:" + spendTime + "ms.<br/>");
		output(sb.toString(), response);
	}



	private void output(String output, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println(output);
		out.println(output);
	}

}
