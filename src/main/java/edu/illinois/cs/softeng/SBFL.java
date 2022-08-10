package edu.illinois.cs.softeng;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

/**
 * Implementation for Spectrum-based Fault Localization (SBFL).
 *
 */
public class SBFL
{

	/**
	 * Use Jsoup to parse the coverage file in the XML format.
	 * 
	 * @param file
	 * @return a map from each test to the set of lines that it covers
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	protected static Map<String, Set<String>> readXMLCov(File file)
			throws FileNotFoundException, IOException {
		Map<String, Set<String>> res = new HashMap<String, Set<String>>();
		// TODO
		return res;
	}

	/**
	 * Compute the suspiciousness values for all covered statements based on
	 * Tarantula
	 * 
	 * @param cov
	 * @param failedTests
	 * @return a map from each line to its suspiciousness value
	 */
	public static Map<String, Double> Tarantula(Map<String, Set<String>> cov,
			Set<String> failedTests) {
		// using LinkedHashMap so that the statement list can be ranked
		Map<String, Double> susp = new LinkedHashMap<String, Double>();
		// TODO
		return susp;
	}

	/**
	 * Get the suspiciousness value for the buggy line from the suspicious
	 * statement list
	 * 
	 * @param susp
	 * @param buggyLine
	 * @return the suspiciousness value for the buggy line
	 */
	protected static double getSusp(Map<String, Double> susp,
			String buggyLine) {
		// TODO
		return 0.0;
	}

	/**
	 * Rank all statements based on the descending order of their suspiciousness
	 * values. Get the rank (print the lowest rank for the tied cases) for the
	 * buggy line from the suspicious statement list
	 * 
	 * @param susp
	 * @param buggyLine
	 * @return the rank of the buggy line
	 */
	protected static int getRank(Map<String, Double> susp, String buggyLine) {
		List<Map.Entry<String, Double>> list = new LinkedList<Map.Entry<String, Double>>(
				susp.entrySet());
		// TODO
		return 0;
	}

}
