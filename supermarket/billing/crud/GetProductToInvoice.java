package supermarket.billing.crud;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import supermarket.billing.service.Service;

/**
 * Servlet implementation class GetProductToInvoice
 */
@WebServlet("/GetProductToInvoice")
public class GetProductToInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProductToInvoice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json"); 
		PrintWriter out = response.getWriter();
		int productID = Integer.parseInt(request.getParameter("productID"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		JSONObject json = Service.getProduct(productID,quantity);
		out.println(json);
	}

}
