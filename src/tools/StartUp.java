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
import tools.core.MySQLUtils;
import tools.core.model.ModelClassDesc;
import tools.core.model.TableColumnDesc;
import tools.core.model.TableDesc;

public class StartUp {
	
	
	public static Connection con;
	static {
		String url = "jdbc:mysql://localhost:3306/shoop";
		String username = "root";
		String password = "zhushunshan";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<String> getAllTables(String sql, String dbName)
			throws SQLException {
		List<String> list = new ArrayList<String>();
		Statement stt = con.createStatement();
		ResultSet rs = stt.executeQuery(sql);
		while (rs.next()) {
			list.add(rs.getString("Tables_in_" + dbName));
		}
		return list;
	}

	public static List<TableColumnDesc> getColumns(String sql) throws SQLException{
		List<TableColumnDesc> result = new ArrayList<TableColumnDesc>();
		Statement stt = con.createStatement();
		ResultSet rs =  stt.executeQuery(sql);
		System.out.println("*****************************"+sql+"****************************************");
		while (rs.next()) {
			TableColumnDesc tcd = new TableColumnDesc();
			tcd.setField(rs.getString("FIELD"));
			tcd.setType(rs.getString("TYPE"));
			tcd.setIsKey(rs.getString("KEY"));
			tcd.setIsNull(rs.getString("NULL"));
			tcd.setExtra(rs.getString("EXTRA"));
			tcd.setDefault_(rs.getString("DEFAULT"));
			result.add(tcd);
		}
		return result;
	}
	
	
	public static ModelClassDesc getClassDesc(TableDesc tableDesc,String packageName) {
		return	 MySQLUtils.tableParseToMode(tableDesc);
	}

	public static void main(String[] args) {
		try {
		List<String> list =  StartUp.getAllTables("show tables", "shoop");
		
		String packeName = "com.web.core";
		
		Iterator<String> it = list.iterator();
		List<ModelClassDesc> mcds = new ArrayList<ModelClassDesc>();
		while (it.hasNext()) {
			String tableName = it.next();
			TableDesc td = new TableDesc();
			td.setTableName(tableName);
			td.setTable(StartUp.getColumns("DESC "+tableName));
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
