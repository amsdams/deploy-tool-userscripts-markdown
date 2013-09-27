package amsdams;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

public class MDUtil {
	public static String getMDScriptBlock(File fileJS, File filePNG) throws IOException {
		StringBuffer stringBuffer = new StringBuffer();

		stringBuffer.append("# " + FilenameUtils.getName(fileJS.getAbsolutePath()));
		stringBuffer.append(System.getProperty("line.separator"));
		stringBuffer.append(System.getProperty("line.separator"));

		stringBuffer.append("## description from JS:");
		stringBuffer.append(System.getProperty("line.separator"));
		stringBuffer.append(System.getProperty("line.separator"));

		List<String> ids = USUtil.readJSProperty(fileJS, USUtil.ID);
		for (String id : ids) {
			stringBuffer.append(getMDCode("id: " + id));
			stringBuffer.append(System.getProperty("line.separator"));
			stringBuffer.append(System.getProperty("line.separator"));
		}

		List<String> names = USUtil.readJSProperty(fileJS, USUtil.NAME);
		for (String name : names) {
			stringBuffer.append(getMDCode("name: " + name));
			stringBuffer.append(System.getProperty("line.separator"));
			stringBuffer.append(System.getProperty("line.separator"));
		}

		List<String> categories = USUtil.readJSProperty(fileJS, USUtil.CATEGORY);
		for (String category : categories) {
			stringBuffer.append(getMDCode("category: " + category));
			stringBuffer.append(System.getProperty("line.separator"));
			stringBuffer.append(System.getProperty("line.separator"));
		}

		List<String> versions = USUtil.readJSProperty(fileJS, USUtil.VERSION);
		for (String version : versions) {
			stringBuffer.append(getMDCode("version: " + version));
			stringBuffer.append(System.getProperty("line.separator"));
			stringBuffer.append(System.getProperty("line.separator"));
		}

		List<String> namespaces = USUtil.readJSProperty(fileJS, USUtil.NAMESPACE);
		for (String namespace : namespaces) {
			stringBuffer.append(getMDCode("namespace: " + getMDCode(namespace)));
			stringBuffer.append(System.getProperty("line.separator"));
			stringBuffer.append(System.getProperty("line.separator"));
		}

		List<String> updateurls = USUtil.readJSProperty(fileJS, USUtil.UPDATE_URL);
		for (String updateurl : updateurls) {
			stringBuffer.append(getMDCode("updateurl: " + updateurl));
			stringBuffer.append(System.getProperty("line.separator"));
			stringBuffer.append(System.getProperty("line.separator"));
		}

		List<String> dowloadurls = USUtil.readJSProperty(fileJS, USUtil.DOWNLOAD_URL);
		for (String dowloadurl : dowloadurls) {
			stringBuffer.append(getMDCode("dowloadurl: " + dowloadurl));
			stringBuffer.append(System.getProperty("line.separator"));
			stringBuffer.append(System.getProperty("line.separator"));
		}

		List<String> descriptions = USUtil.readJSProperty(fileJS, USUtil.DESCRIPTION);
		for (String description : descriptions) {
			stringBuffer.append(getMDCode("description: " + description));
			stringBuffer.append(System.getProperty("line.separator"));
			stringBuffer.append(System.getProperty("line.separator"));
		}

		List<String> includes = USUtil.readJSProperty(fileJS, USUtil.INCLUDE);
		for (String include : includes) {
			stringBuffer.append(getMDCode("include: " + include));
			stringBuffer.append(System.getProperty("line.separator"));
			stringBuffer.append(System.getProperty("line.separator"));
		}

		List<String> matches = USUtil.readJSProperty(fileJS, USUtil.MATCH);
		for (String match : matches) {
			stringBuffer.append(getMDCode("match: " + match));
			stringBuffer.append(System.getProperty("line.separator"));
			stringBuffer.append(System.getProperty("line.separator"));
		}

		List<String> grants = USUtil.readJSProperty(fileJS, USUtil.GRANT);
		for (String grant : grants) {
			stringBuffer.append(getMDCode("grant: " + grant));
			stringBuffer.append(System.getProperty("line.separator"));
			stringBuffer.append(System.getProperty("line.separator"));
		}

		stringBuffer.append("## install link: " + getMDLink(fileJS));
		stringBuffer.append(System.getProperty("line.separator"));
		stringBuffer.append(System.getProperty("line.separator"));

		stringBuffer.append("## preview image: " + getMDImage(filePNG));
		stringBuffer.append(System.getProperty("line.separator"));
		stringBuffer.append(System.getProperty("line.separator"));

		return stringBuffer.toString();

	}

	private static String getMDImage(File filePNG) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("![" + FilenameUtils.getName(filePNG.getAbsolutePath()) + "](./" + FilenameUtils.getName(filePNG.getAbsolutePath()) + " \"" + FilenameUtils.getName(filePNG.getAbsolutePath()) + " \")");
		return stringBuffer.toString();

	}

	private static String getMDCode(String line) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("\t" + line);
		return stringBuffer.toString();

	}

	private static String getMDLink(File fileJS) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("[" + FilenameUtils.getName(fileJS.getAbsolutePath()) + "](./" + FilenameUtils.getName(fileJS.getAbsolutePath()) + " \"" + FilenameUtils.getName(fileJS.getAbsolutePath()) + " \")");
		return stringBuffer.toString();

	}
}
