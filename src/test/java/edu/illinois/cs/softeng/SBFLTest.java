package edu.illinois.cs.softeng;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * Tests for your SBFL implementation
 *
 */
public class SBFLTest
{
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("fail.log"));
		String line = reader.readLine();
		while (line != null) {
			String test = line.substring(0, line.indexOf(":")).trim();
			String meth = test.substring(0, test.indexOf("("));
			String clas = test.substring(test.indexOf("(") + 1,
					test.indexOf(")"));
			String testName = clas + "." + meth;
			System.out.println(testName);
			line = reader.readLine();
		}
	}

	@Test
	public void test1()
			throws FileNotFoundException, IOException, URISyntaxException {
		// return coverage information
		Map<String, Set<String>> cov = SBFL
				.readXMLCov(getFileFromResource("debug-info/cov.xml"));
		
		// get the list of failed tests from testing resources
		Set<String> failedTests = new HashSet<String>();
		Collections.addAll(failedTests,
				FileUtils
						.readFileToString(getFileFromResource(
								"debug-info/failedtests1.txt"), "utf-8")
						.split("\n"));
		
		// define the buggy line
		String buggyLine = "org.jsoup.parser.HtmlTreeBuilder:432";
		
		// compute suspiciousness for each statement
		Map<String, Double> susp = SBFL.Tarantula(cov, failedTests);
		
		// check the suspiciousness value of the buggy line
		assertEquals(0.8569651741293532, SBFL.getSusp(susp, buggyLine),
				0.0001);
		// check the absolute rank of the buggy line
		assertEquals(186, SBFL.getRank(susp, buggyLine));
	}

	@Test
	public void test2()
			throws FileNotFoundException, IOException, URISyntaxException {
		Map<String, Set<String>> cov = SBFL
				.readXMLCov(getFileFromResource("debug-info/cov.xml"));
		Set<String> failedTests = new HashSet<String>();
		Collections.addAll(failedTests,
				FileUtils
						.readFileToString(getFileFromResource(
								"debug-info/failedtests2.txt"), "utf-8")
						.split("\n"));
		String buggyLine = "org.jsoup.parser.HtmlTreeBuilderState:768";
		Map<String, Double> susp = SBFL.Tarantula(cov, failedTests);
		assertEquals(0.8786717752234994, SBFL.getSusp(susp, buggyLine),
				0.0001);
		assertEquals(44, SBFL.getRank(susp, buggyLine));

	}

	@Test
	public void test3()
			throws FileNotFoundException, IOException, URISyntaxException {
		Map<String, Set<String>> cov = SBFL
				.readXMLCov(getFileFromResource("debug-info/cov.xml"));
		Set<String> failedTests = new HashSet<String>();
		Collections.addAll(failedTests,
				FileUtils
						.readFileToString(getFileFromResource(
								"debug-info/failedtests3.txt"), "utf-8")
						.split("\n"));
		String buggyLine = "org.jsoup.parser.TokeniserState:219";
		Map<String, Double> susp = SBFL.Tarantula(cov, failedTests);

		// for(String key:susp.keySet())
		// System.out.println(key+" "+susp.get(key));
		// System.out.println(CovReader.getSusp(susp, buggyLine));
		// System.out.println(CovReader.getRank(susp, buggyLine));
		assertEquals(0.9407616361071932, SBFL.getSusp(susp, buggyLine),
				0.0001);
		assertEquals(124, SBFL.getRank(susp, buggyLine));

	}

	private File getFileFromResource(String fileName)
			throws URISyntaxException {
		ClassLoader classLoader = getClass().getClassLoader();
		URL resource = classLoader.getResource(fileName);
		if (resource == null) {
			throw new IllegalArgumentException("file not found! " + fileName);
		} else {
			return new File(resource.toURI());
		}
	}

}
