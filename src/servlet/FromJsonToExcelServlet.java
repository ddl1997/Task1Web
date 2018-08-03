package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import method.JsonToExcel;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class FromJsonToExcelServlet
 */
@WebServlet("/FromJsonToExcelServlet")
public class FromJsonToExcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FromJsonToExcelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String json = request.getParameter("json");
		String path = request.getParameter("path");
		JSONObject jo = JSONObject.fromObject(json);
		String filePath = JsonToExcel.json_to_excel(jo, path);
		HttpSession session=request.getSession();
		session.setAttribute("message",filePath);
		response.sendRedirect("display.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
