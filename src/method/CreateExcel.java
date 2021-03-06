package method;

import java.io.File;
import java.sql.*;
import java.util.UUID;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class CreateExcel {
	
	public static String create_excel(String sql, String relativePath)
	{
		WritableWorkbook workbook = null;
        String dir = CreateExcel.class.getResource("/../../").getPath();
		String fileName = UUID.randomUUID().toString() + ".xls";
		String filePath = (relativePath == null || relativePath.equals("")) ?
                "output" + File.separator + fileName :
                relativePath + File.separator + fileName;
		File file = new File(dir + filePath);
		try {
			file.createNewFile();
			workbook = Workbook.createWorkbook(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Connection conn = getConn();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int sheetCount = workbook.getNumberOfSheets();
            WritableSheet s = workbook.createSheet("Sheet" + (sheetCount + 1), sheetCount);
			for (int i = 0; i < rsmd.getColumnCount(); i++)
			{
				Label label = new Label(i, 0, rsmd.getColumnName(i + 1));
                s.addCell(label);
			}
			int rowCount = 1;
			while(rs.next()) {
				for (int i = 0; i < rsmd.getColumnCount(); i++)
				{
					s.addCell(new Label(i, rowCount, rs.getString(i + 1)));
				}
				rowCount++;
			}

			workbook.write();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				workbook.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filePath;

	}

	private static Connection getConn() {

	    try {
	    	File f = new File(/*"src" + File.separator + "config" + File.separator + "database_config.xml"*/CreateExcel.class.getResource("/config" + File.separator + "database_config.xml").getPath());
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(f);
			NodeList nl = doc.getElementsByTagName("database_connect");
			NodeList attrs = nl.item(0).getChildNodes();
			String[] values = new String[4];
            int count = 0;
            for (int i = 0; i < attrs.getLength(); i++)
            {
            	if (attrs.item(i).getNodeType() == Node.ELEMENT_NODE)
            	{
            		values[count++] = attrs.item(i).getTextContent();
            	}
            }
		    String driver = values[0];
		    String url = values[1];
		    String username = values[2];
		    String password = values[3];
	        Class.forName(driver);
	        return DriverManager.getConnection(url, username, password);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public static void main(String[] args)
	{
//
//		System.out.println(CreateExcel.class.getResource("/config" + File.separator + "database_config.xml").getPath());
	}

}