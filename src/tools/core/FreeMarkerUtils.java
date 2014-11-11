package tools.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import tools.core.model.ModelClassDesc;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerUtils {

	private static Configuration config = new Configuration();

	static {
		String url = FreeMarkerUtils.class.getResource("/").getPath()
				+ "tools/core/template/";
		System.out.println(url);
		try {
			config.setDirectoryForTemplateLoading(new File(url));
			config.setObjectWrapper(new DefaultObjectWrapper());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void outModel(ModelClassDesc modelClassDesc)
			throws IOException, TemplateException {
		Template temp = config.getTemplate("model.ftl");
		String path = modelClassDesc.getPackgeName().replace(".", "/") + "/model/";

		File file = new File(FreeMarkerUtils.class.getResource("/").getPath()
				+ "/src/" + path);
		if (!file.exists()) {
			file.mkdirs();
		}
		File file2 = new File(FreeMarkerUtils.class.getResource("/").getPath()
				+ "src/" + path + modelClassDesc.getClassName() + ".java");
		if (file2.exists()) {
			file2.delete();
		}
		file2.createNewFile();
		FileWriter fw = new FileWriter(file2);
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("modelClassDesc", modelClassDesc);
		temp.process(root, fw);
		temp.process(root, new OutputStreamWriter(System.out));
		fw.flush();
		fw.close();
	}

	public static void outMapper(ModelClassDesc modelClassDesc)
			throws IOException, TemplateException {
		Template temp = config.getTemplate("mapper.ftl");
		String path = modelClassDesc.getPackgeName().replace(".", "/")
				+ "/model/mapper/";
		File dir = new File(FreeMarkerUtils.class.getResource("/").getPath()
				+ "/src/" + path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File file = new File(FreeMarkerUtils.class.getResource("/").getPath()
				+ "/src/" + path + modelClassDesc.getClassName() + "Mapper.xml");
		if(file.exists()){
			file.delete();
		}
		file.createNewFile();
		FileWriter fw = new FileWriter(file);
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("modelClassDesc", modelClassDesc);
		temp.process(root, fw);
		temp.process(root, new OutputStreamWriter(System.out));
		fw.flush();
		fw.close();

	}

	public static void main(String[] args) {

		File file = new File("C:/aa");
		try {
			file.mkdirs();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
