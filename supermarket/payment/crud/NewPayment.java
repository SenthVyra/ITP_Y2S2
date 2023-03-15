package supermarket.payment.crud;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import supermarket.payment.service.Service;

/**
 * Servlet implementation class NewPayment
 */
@WebServlet("/NewPayment")
public class NewPayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewPayment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
        PrintWriter out = response.getWriter();
        if(session.getAttribute("role").equals("Admin")) {
        	String nameAndId = request.getParameter("empID");
        	int empID = Integer.parseInt(nameAndId.split("_")[0]);
        	String empName = nameAndId.split("_")[1];
    		BigDecimal salary = new BigDecimal(request.getParameter("salary"));
            Service.newPayment(empID, salary, empName);
            response.sendRedirect("payments.jsp");
        }else {
        	out.println("<script type=\"text/javascript\">");
			out.println("alert('you dont have permissions');");
			out.println("location='employee.jsp';");
			out.println("</script>");
        }
	}

}
