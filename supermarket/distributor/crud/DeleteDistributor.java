package supermarket.distributor.crud;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import supermarket.distributor.service.*;

/**
 * Servlet implementation class DeleteDistributor
 */
@WebServlet("/DeleteDistributor")
public class DeleteDistributor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteDistributor() {
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
        	int distributorID = Integer.parseInt(request.getParameter("distID"));
    		Service.deleteDistributor(distributorID);
    		response.sendRedirect("distributor.jsp");
        }else {
        	out.println("<script type=\"text/javascript\">");
			out.println("alert('you dont have permissions');");
			out.println("location='distributor.jsp';");
			out.println("</script>");
        }
        
	}

}
