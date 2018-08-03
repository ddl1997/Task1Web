package servlet;

import method.ExcelToJson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet("/FromExcelToJsonServlet")
public class FromExcelToJsonServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FromExcelToJsonServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String result = null;
        System.out.println("aaaaa");
        try{
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            //2、创建一个文件上传解析器
            //3、判断提交上来的数据是否是上传表单的数据
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
//            if(!ServletFileUpload.isMultipartContent(request)){
//                //按照传统方式获取数据
//                System.out.println("bbbb");
//                return;
//            }
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item : list){
                if(!item.isFormField()){
                    InputStream in = item.getInputStream();
                    result = ExcelToJson.excel_to_json(in);
                    in.close();
                    item.delete();
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
        request.setAttribute("json",result);
        request.getRequestDispatcher("/display.jsp").forward(request, response);

    }
}
