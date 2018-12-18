package jmeter.helpers

import groovy.json.JsonSlurper

/**
  * Helper for common actions against TestRail API.
  */
class TestRailAPI {
	
	/** URL to TestRail API */
	private String testRailURL
	/** Username to authenticate against TestRail API */
	private String testRailUser
	/** Password or API key to authenticate against TestRail API */
	private String testRailAPIKey
	
	/**
     * TestRailAPI constructor.
     *
     * @param testRailURL URL to TestRail API
     * @param testRailUser Username to authenticate against TestRail API
	 * @param testRailAPIKey Password or API key to authenticate against TestRail API
     */
	TestRailAPI(testRailURL, testRailUser, testRailAPIKey) {
		this.testRailURL = testRailURL
		this.testRailUser = testRailUser
		this.testRailAPIKey = testRailAPIKey
	}

	/**
      * Retrieves name of the test run for a given test run ID.
      *
      * @param testRunId Unique identifier of a test run
      * @return Test run name
      */
	def getTestRunName(testRunId) {
     	def testRailGetTestRun = Helpers.getBodyBySendGetWithAuthorization(
     		"${this.testRailURL}/index.php?/api/v2/get_run/$testRunId", 
     		new LinkedHashMap<>(), 
     		this.testRailUser, 
     		this.testRailAPIKey)
     	def jsonSlurper = new JsonSlurper()
     	def response = jsonSlurper.parseText(testRailGetTestRun)
     	return response.name
	}

	/**
      * Retrieves test case name and associated suite ID for a given test case ID.
      *
      * @param testCaseId Unique identifier of a test case
      * @return Test case name and associated suite ID
      */
	def getTestCaseNameAndSuiteId(testCaseId) {
		def testRailGetTestCase = Helpers.getBodyBySendGetWithAuthorization(
     		"${this.testRailURL}/index.php?/api/v2/get_case/$testCaseId", 
     		new LinkedHashMap<>(), 
     		this.testRailUser, 
     		this.testRailAPIKey)
     	def jsonSlurper = new JsonSlurper()
		response = jsonSlurper.parseText(testRailGetTestCase)
		def testCaseNameAndSuiteId = new String[2]
		testCaseNameAndSuiteId[0] = response.title
		testCaseNameAndSuiteId[1] = response.suite_id 
		return testCaseNameAndSuiteId
	}	

	/**
      * Retrieves test suite name for a given test suite ID.
      *
      * @param testSuiteID Unique identifier of a test suite
      * @return Test suite name
      */
	def getTestSuiteName(testSuiteId) {
		def testRailGetSuite = Helpers.getBodyBySendGetWithAuthorization(
     		"${this.testRailURL}/index.php?/api/v2/get_suite/$testSuiteId", 
     		new LinkedHashMap<>(), 
     		testRailUser, 
     		testRailAPIKey)
     	def jsonSlurper = new JsonSlurper()
     	def response = jsonSlurper.parseText(testRailGetSuite)
     	return response.name
	}
}
