package model.PAF;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class fundAPI
*/
@WebServlet("/fundAPI")
public class fundAPI extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	Fund fundObj = new Fund();
       
    /**
     * @see HttpServlet#HttpServlet()
    */
    public fundAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String output = fundObj.insertFunds(
						request.getParameter("fundCode"), 
						request.getParameter("fundName"), 
						request.getParameter("fundEmail"), 
						request.getParameter("fundAddress"), 
						request.getParameter("fundContact"),  
						request.getParameter("fundAmount"));
		
		response.getWriter().write(output);
	}
	
	private Map getParasMap(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		Map<String, String> map = new HashMap<String, String>();
		try
		{
			 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			 String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			 scanner.close();
			 
			 String[] params = queryString.split("&");
			 for (String param : params)
			 { 
				 String[] p = param.split("=");
				 map.put(p[0], p[1]);
			 }
		}
		catch (Exception e)
		{
		}
		return map;
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	*/
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map<String, String> paras = getParasMap(request);
		String output = fundObj.updateFunds(
						paras.get("hidFundIDSave").toString(),
				 		paras.get("fundCode").toString(),
				 	    paras.get("fundName").toString(),
				 		paras.get("fundEmail").toString(),
				 		paras.get("fundAddress").toString(),
				 		paras.get("fundContact").toString(),
				 		paras.get("fundAmount").toString());
		 
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	*/
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map<String, String> paras = getParasMap(request);
		String output = fundObj.deleteFunds(paras.get("fundID").toString());
		response.getWriter().write(output);
	}

}
