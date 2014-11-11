package tools.core.model;

import java.util.ArrayList;
import java.util.List;

public class ModelClassDesc {
	
	private String packgeName;
	
	private String className;
	
	private String tableName;
	
	private List<ModelFiledDesc>  fileds = new ArrayList<ModelFiledDesc>();
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getPackgeName() {
		return packgeName;
	}

	public void setPackgeName(String packgeName) {
		this.packgeName = packgeName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<ModelFiledDesc> getFileds() {
		return fileds;
	}

	public void setFileds(List<ModelFiledDesc> fileds) {
		this.fileds = fileds;
	}

	@Override
	public String toString() {
		return "ModelClassDesc [packgeName=" + packgeName + ", className="
				+ className + ", fileds=" + fileds + "]";
	}

}
