package supermarket.sales.function;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import supermarket.sales.service.Service;

/**
 * Servlet implementation class SalesChartByDate
 */
@WebServlet("/SalesChartByDate")
public class SalesChartByDate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalesChartByDate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json"); 
		PrintWriter out = response.getWriter();
		String date = request.getParameter("date");
		JSONObject json = Service.salesChartByDate(date);
		out.println(json);
	}

}
