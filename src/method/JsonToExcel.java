package method;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonToExcel {
	
	public static String json_to_excel(JSONObject input, String relativePath)
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
		
		Map<String, Object> map = json_to_map(input);
		Set<String> keySet = map.keySet();
		try {
			for (Object s : keySet)
			{
				Object o = map.get(s);
				if (o instanceof List)
				{
					List<Map<String, Object>> l = (List<Map<String, Object>>) o;
					WritableSheet ws = null;
					int sheetCount = workbook.getNumberOfSheets();
					ws = workbook.createSheet("Sheet" + (sheetCount + 1), sheetCount);
					for (int i = 0; i < l.size(); i++)
					{
						Map<String, Object> m = (Map<String, Object>) l.get(i);
						Object[] title = m.keySet().toArray();
						for (int j = 0; j < title.length; j++)
						{
							Label cell = new Label(j, i, m.get(title[j]).toString());
							ws.addCell(cell);
						}
					}
				}
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
		return filePath;
	}
	
	public static Map<String, Object> json_to_map(JSONObject json)
	{
		Map<String, Object> map = new LinkedHashMap<String, Object>();//(1) not HashMap
		for(Object key : json.keySet())
		{
			Object o = json.get(key);
			if (o instanceof JSONArray)
			{
				List<Map<String, Object>> l = new ArrayList<>();
				Iterator<JSONObject> it = ((JSONArray) o).iterator();
				while (it.hasNext())
				{
					JSONObject jo = it.next();
					l.add(json_to_map(jo));
				}
				map.put(key.toString(), l);
			}
			else
			{
				map.put(key.toString(), o);
			}
		}
		
		return map;
	}

	public static void main(String[] args) {
		File file = new File("D:"+ File.separator +"1.xls");
		String excelpath = file.getAbsolutePath();
		InputStream is = null;
		try {
			is = new FileInputStream(excelpath);
			String temp = ExcelToJson.excel_to_json(is);
			System.out.println(temp);
			System.out.println(json_to_excel(JSONObject.fromObject(temp), ""));
//			System.out.println(FlexibleExcel.generate_flexible_excel(JSONObject.fromObject(temp), ""));
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
