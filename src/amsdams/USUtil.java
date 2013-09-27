package amsdams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class USUtil {
	private boolean verbose = false;

	public boolean isVerbose() {
		return verbose;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	public static String ID = "// @id ";// iitc-plugin-highlight-portals-missing-mods@amsdams
	public static String NAME = "// @name ";// IITC plugin: highlight portals
	public static String CATEGORY = "// @category ";// Highlighter
	public static String VERSION = "// @version ";// 0.1.2.@@DATETIMEVERSION@@
	public static String NAMESPACE = "// @namespace ";// https://github.com/jonatkins/ingress-intel-total-conversion
	public static String UPDATE_URL = "// @updateURL ";// @@UPDATEURL@@
	public static String DOWNLOAD_URL = "// @downloadURL ";// @@DOWNLOADURL@@
	public static String DESCRIPTION = "// @description ";// [@@BUILDNAME@@-@@BUILDDATE@@]
															// // highlight
															// portals missing
															// mods
	public static String INCLUDE = "// @include ";// https://www.ingress.com/intel*
	public static String MATCH = "// @match ";// https://www.ingress.com/intel*
	public static String GRANT = "// @grant";// none

	public static List<String> readJSProperty(File fileJS, String property) throws IOException {
		ArrayList<String> values = new ArrayList<String>();
		List<String> lines = FileUtils.readLines(fileJS);
		
		for (String line:lines){
			
			if (line.startsWith(property)) {
				String value = line.replaceAll(property, "").trim();
				values.add(value);
				
			} 
			
		}
		
		/*BufferedReader br;
		

		br = new BufferedReader(new FileReader(fileJS));
		String line;

		while ((line = br.readLine()) != null) {
			// process the line.

			if (line.startsWith(property)) {
				values.add(line.replaceAll(property, ""));

			}
		}

		br.close();
*/
		return values;
	}

	public static List<String> writeJSProperty(File outputJS, String property, String value) throws IOException {

		ArrayList<String> values = new ArrayList<String>();
		List<String> lines = FileUtils.readLines(outputJS);
		int linexIndex = 0;
		for (String line:lines){
			
			if (line.startsWith(property)) {
				String newLine = line.replaceAll(line.substring(property.length(), line.length()), value);
				
				values.add(newLine);
				lines.set(linexIndex,newLine); 
			} 
			linexIndex++;
		}
		FileUtils.writeLines(outputJS, lines);
		return values;
	}
}
