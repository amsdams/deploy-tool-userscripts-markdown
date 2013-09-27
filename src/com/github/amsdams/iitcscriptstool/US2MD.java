package com.github.amsdams.iitcscriptstool;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class US2MD {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// create the parser
		CommandLineParser parser = new GnuParser();
		Options options = new Options();
		try {

			Option input = OptionBuilder.withArgName("directory").hasArg().withDescription("input directory").create("input");
			options.addOption(input);

			Option output = OptionBuilder.withArgName("directory").hasArg().withDescription("output directory").create("output");
			options.addOption(output);

			Option verbose = OptionBuilder.withArgName("bool").hasArg().withDescription("verbose").create("verbose");
			options.addOption(verbose);

			Option download = OptionBuilder.withArgName("url").hasArg().withDescription("download path (github)").create("download");
			options.addOption(download);

			// parse the command line arguments
			CommandLine line = parser.parse(options, args);

			
			

			String inputDirectory = line.hasOption("input")?line.getOptionValue("input"):"./";
			System.out.print("input: " + inputDirectory);
			String outputDirectory = line.hasOption("output")?line.getOptionValue("output"):"./";
			System.out.print("output: " + outputDirectory);
			String  downloadPath = line.hasOption("download")?line.getOptionValue("download"):"";
			System.out.print("download: " + downloadPath);
			Boolean verboseOutput = line.hasOption("verbose")?Boolean.parseBoolean(line.getOptionValue("verbose")):false;
			System.out.print("verbose:" + verboseOutput);
			
			Program program = new Program();
			program.setInputDirectory(inputDirectory);
			program.setOutputDirectory(outputDirectory);
			program.setVerbose(verboseOutput);
			program.setDownloadPath(downloadPath);
			program.run();

		} catch (ParseException exp) {
			// oops, something went wrong
			System.err.println("Parsing failed.  Reason: " + exp.getMessage());

			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("US2MD", options);

		}

	}

}
