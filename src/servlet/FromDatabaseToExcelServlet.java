package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import method.*;
import java.util.Base64;

/**
 * Servlet implementation class ToExcelServlet
 */
@WebServlet("/FromDatabaseToExcelServlet")
public class FromDatabaseToExcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FromDatabaseToExcelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String rawSql = request.getParameter("sql");
		String path = request.getParameter("path");
		String sql = new String(Base64.getDecoder().decode(rawSql));
		System.out.println(sql);
		String filePath = CreateExcel.create_excel(sql, path);
        request.setAttribute("message", filePath);
		response.setContentType("text/html;charset=utf-8");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
