package amsdams;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class Program {

	private boolean verbose = true;

	public boolean isVerbose() {
		return verbose;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	public String getOutputDirectory() {

		return outputDirectory;
	}

	public void setOutputDirectory(String outputDirectory) {
		if (!outputDirectory.endsWith(File.separator)) {
			this.outputDirectory = outputDirectory + "" + File.separator;
		} else {
			this.outputDirectory = outputDirectory;
		}

	}

	public String getInputDirectory() {
		return inputDirectory;
	}

	public void setInputDirectory(String inputDirectory) {

		if (!inputDirectory.endsWith(File.separator)) {
			this.inputDirectory = inputDirectory + "" + File.separator;
		} else {
			this.inputDirectory = inputDirectory;
		}

	}

	public String getDownloadPath() {
		return downloadPath;
	}

	public void setDownloadPath(String downloadPath) {

		if (!downloadPath.endsWith("/")) {
			this.downloadPath = downloadPath + "/";
		} else {
			this.downloadPath = downloadPath;
		}

	}

	private String outputDirectory = ".";
	private String inputDirectory = ".";
	private String downloadPath = "https://github.com/amsdams/iitc-plugins/blob/master";

	public void run() {

		try {
			if (this.isVerbose()) {
				System.out.println("deleting output directory and creating a new one");
			}
			cleanOutputDirectory();

			if (this.isVerbose()) {
				System.out.println("getting files from input directory");
			}

			List<File> files = this.getFiles();
			StringBuffer stringBuffer = new StringBuffer();

			for (File fileJS : files) {

				String fileJSAbsolutePath = fileJS.getAbsolutePath();
				String extJS = FilenameUtils.getExtension(fileJSAbsolutePath);
				String nameJS = FilenameUtils.getBaseName(fileJSAbsolutePath);
				String fileJSName = FilenameUtils.getName(fileJSAbsolutePath);

				if (extJS.equalsIgnoreCase("js")) {
					for (File filePNG : files) {
						String filePNGAbsolutePath = filePNG.getAbsolutePath();
						String extPNG = FilenameUtils.getExtension(filePNGAbsolutePath);
						String namePNG = FilenameUtils.getBaseName(filePNGAbsolutePath);

						if (extPNG.equalsIgnoreCase("png") && nameJS.equalsIgnoreCase(namePNG)) {
							System.out.println("found a pair! " + nameJS + " " + namePNG);

							File outputJS = createJSOutputFile(fileJS, fileJSName);
							if (this.isVerbose()) {
								System.out.println("writing  js file to ouput directory as " + outputJS.getAbsolutePath());
							}
							String downloadUrl = getDownloadUrl(fileJSName);
							if (this.isVerbose()) {
								System.out.println("download url created  " + downloadUrl);
							}
							USUtil.writeJSProperty(outputJS, USUtil.DOWNLOAD_URL, downloadUrl);
							if (this.isVerbose()) {
								System.out.println("modified download url in " + outputJS.getAbsolutePath());
							}
							USUtil.writeJSProperty(outputJS, USUtil.UPDATE_URL, downloadUrl);
							if (this.isVerbose()) {
								System.out.println("modified updateurl url in " + outputJS.getAbsolutePath());
							}
							String block = MDUtil.getMDScriptBlock(outputJS, filePNG);
							if (this.isVerbose()) {
								System.out.println("read file description as for markdown " + block);

							}
							stringBuffer.append(block);

							copyFileToOutputDirectory(filePNG);

							if (this.isVerbose()) {
								System.out.println("copied png file " + filePNG.getAbsolutePath() + " to  " + this.getOutputDirectory());

							}

						}
					}
				}

			}
			writeMD(stringBuffer);
			if (this.isVerbose()) {
				System.out.println("written md file  in output dir ");

			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void copyFileToOutputDirectory(File fileJS) throws IOException {
		FileUtils.copyFile(fileJS, new File(this.getOutputDirectory() + FilenameUtils.getName(fileJS.getAbsolutePath())));
	}

	private String getDownloadUrl(String fileJSName) {
		return this.getDownloadPath() + fileJSName;
	}

	private File createJSOutputFile(File inputJS, String fileJSName) throws IOException {
		File outpurJS = new File((this.getOutputDirectory() + fileJSName));

		FileUtils.copyFile(inputJS, outpurJS);
		return outpurJS;
	}

	private void cleanOutputDirectory() throws IOException {
		File outpurFileDire = new File(this.getOutputDirectory());
		FileUtils.deleteDirectory(outpurFileDire);
		outpurFileDire.mkdir();
	}

	private List<File> getFiles() {
		ArrayList<File> files = new ArrayList<File>();
		File folder = new File(this.getInputDirectory());
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				if (this.isVerbose()) {
					System.out.println("File " + listOfFiles[i].getName());
				}
				files.add(listOfFiles[i]);
			} else if (listOfFiles[i].isDirectory()) {
				if (this.isVerbose()) {
					System.out.println("Directory " + listOfFiles[i].getName());
				}
			}
		}
		return files;
	}

	public void writeMD(StringBuffer pData) throws IOException {

		System.out.println("writing to " + this.getOutputDirectory());

		BufferedWriter out = new BufferedWriter(new FileWriter(this.getOutputDirectory() + "README.md"));
		out.write(pData.toString());
		out.flush();
		out.close();

	}

}
