package tools.core;

/**
 *  ������Ϣ
 * @author Administrator
 *
 */
public class ConntionInfo {
	
	//��������
	private String DriveName;
	
	//���ݿ����ӵ�ַ
	private String url;
	// ���ݿ�����
	private String dbName;
	//��¼�û���
	private String userName;
	//��¼����
	private String password;
	
	public ConntionInfo(){
		
	}
	public ConntionInfo(String driveName, String url, String dbName,
			String userName, String password) {
		super();
		DriveName = driveName;
		this.url = url;
		this.dbName = dbName;
		this.userName = userName;
		this.password = password;
	}

	public String getDriveName() {
		return DriveName;
	}

	public void setDriveName(String driveName) {
		DriveName = driveName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
