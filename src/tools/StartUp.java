package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import tools.core.FreeMarkerUtils;
import tools.core.MySQLConnect;
import tools.core.MySQLUtils;
import tools.core.model.ModelClassDesc;
import tools.core.model.TableColumnDesc;
import tools.core.model.TableDesc;

public class StartUp {
	
	public static ModelClassDesc getClassDesc(TableDesc tableDesc,String packageName) {
		return	 MySQLUtils.tableParseToMode(tableDesc);
	}

	public static void main(String[] args) {
		try {
		List<String> list =  MySQLConnect.getAllTables("show tables", "shoop");
		
		String packeName = "com.web.core";
		
		Iterator<String> it = list.iterator();
		List<ModelClassDesc> mcds = new ArrayList<ModelClassDesc>();
		while (it.hasNext()) {
			String tableName = it.next();
			TableDesc td = new TableDesc();
			td.setTableName(tableName);
			td.setTable(MySQLConnect.getColumns("DESC "+tableName));
			ModelClassDesc mcd = MySQLUtils.tableParseToMode(td);
			mcd.setTableName(tableName);
			mcds.add(mcd);
			mcd.setPackgeName(packeName);
			System.out.println(mcd);
			FreeMarkerUtils.outModel(mcd);
			FreeMarkerUtils.outMapper(mcd);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
