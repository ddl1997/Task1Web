package method;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FlexibleExcel {
	
	public static String generate_flexible_excel(JSONObject input, String relativePath)
	{
		WritableWorkbook workbook = null;
		String fileName = UUID.randomUUID().toString() + ".xls";
		String filePath = (relativePath == null || relativePath.equals("")) ?
				"web" + File.separator + "output" + File.separator + fileName :
				"web" + File.separator + relativePath + File.separator + fileName;
		File file = new File(filePath);
		try {
			file.createNewFile();
			workbook = Workbook.createWorkbook(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<List<Cell>> sheets = json_parse(input);
		
		try {
			for (int i = 0; i < sheets.size(); i++)
			{
				WritableSheet ws = null;
				int sheetCount = workbook.getNumberOfSheets();
				ws = workbook.createSheet("Sheet" + (sheetCount + 1), sheetCount);
				List<Cell> cells = sheets.get(i);
				for (Cell c : cells)
				{
					Label l = new Label(c.initX, c.initY, c.content);
					ws.mergeCells(c.initX, c.initY, c.initX + c.width - 1, c.initY + c.height - 1);
					ws.addCell(l);
				}
			}
			workbook.write();
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally {
			try {
				workbook.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return filePath;
	}
	
	public static List<List<Cell>> json_parse(JSONObject json)
	{
		List<List<Cell>> result = new ArrayList<List<Cell>>();
		
		for(Object key : json.keySet())
		{
			Object o = json.get(key);
			List<Cell> sheet = new ArrayList<Cell>();
			if (o instanceof JSONArray)
			{
				Iterator<JSONObject> it = ((JSONArray) o).iterator();
				while (it.hasNext())
				{
					JSONObject jo = it.next();
					Cell c = new Cell();
					for (Object name : jo.keySet())
					{
						switch (name.toString())// >= jdk 1.7
						{
							case "initX" : c.initX = (int) jo.get(name); break;
							case "initY" : c.initY = (int) jo.get(name); break;
							case "width" : c.width = (int) jo.get(name); break;
							case "height" : c.height = (int) jo.get(name); break;
							case "content" : c.content =  jo.get(name).toString(); break;
						}
					}
					sheet.add(c);
				}
			}
			result.add(sheet);
		}
		
		return result;
	}
	
	public static void main(String[] args)
	{
		String json = "{\"Sheet1\" : [{\"initX\" : 0, \"initY\" : 0, \"width\" : 2, \"height\" : 2, \"content\" : 1},"
					+ "{\"initX\" : 2, \"initY\" : 0, \"width\" : 1, \"height\" : 1, \"content\" : 2},"
					+ "{\"initX\" : 3, \"initY\" : 0, \"width\" : 1, \"height\" : 1, \"content\" : 3},"
					+ "{\"initX\" : 2, \"initY\" : 1, \"width\" : 1, \"height\" : 1, \"content\" : 4},"
					+ "{\"initX\" : 3, \"initY\" : 1, \"width\" : 1, \"height\" : 1, \"content\" : 5},"
					+ "{\"initX\" : 0, \"initY\" : 2, \"width\" : 1, \"height\" : 1, \"content\" : 6},"
					+ "{\"initX\" : 1, \"initY\" : 2, \"width\" : 1, \"height\" : 1, \"content\" : 7},"
					+ "{\"initX\" : 2, \"initY\" : 2, \"width\" : 2, \"height\" : 1, \"content\" : 8}]}";
		generate_flexible_excel(JSONObject.fromObject(json), "output");
	}

}

class Cell
{
	int initX;
	int initY;
	int width;
	int height;
	String content;
}
/*
{
	"cell" : {
		"initX" : 
		"initY" :
		"width" :
		"height" :
		"content" : 
	}
}
*/
