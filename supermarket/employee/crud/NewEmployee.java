package supermarket.employee.crud;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import supermarket.employee.service.Service;

/**
 * Servlet implementation class NewEmployee
 */
@WebServlet("/NewEmployee")
public class NewEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
        PrintWriter out = response.getWriter();
        if(session.getAttribute("role").equals("Admin")) {
        	String name = request.getParameter("name");
    		String address =  request.getParameter("address");
    		String phoneNumber = request.getParameter("phonenumber");
    		String email = request.getParameter("email");
    		String password = request.getParameter("password");
    		java.sql.Date dob = java.sql.Date.valueOf(request.getParameter("dob"));
    		String role = request.getParameter("role");
    		String designation = request.getParameter("designation");
    		BigDecimal salary = new BigDecimal(request.getParameter("salary"));
            Service.newEmployee(email, password, phoneNumber, dob, address, name, role, designation, salary);
            response.sendRedirect("employee.jsp");
        }else {
        	out.println("<script type=\"text/javascript\">");
			out.println("alert('you dont have permissions');");
			out.println("location='employee.jsp';");
			out.println("</script>");
        }
		
	}

}
