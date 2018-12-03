@Grapes([
        @Grab(
            group="org.slf4j",
            module="slf4j-simple",
            version="1.7.25"
            ),
        @Grab(
            group="io.github.http-builder-ng",
            module="http-builder-ng-apache",
            version="1.0.3"
            )
])
import static groovyx.net.http.HttpBuilder.configure
import static groovyx.net.http.ContentTypes.JSON
import groovyx.net.http.*

def prepareReportFile(testRailURL, testRailUser, testRailAPIKey, testRunId) {
    HttpBuilder.configure {
        request.uri = testRailURL
        request.auth.basic testRailUser, testRailAPIKey
    }.get {
        request.uri.path = "/index.php?api/v2/get_run/$testRunId"
        response.success { fromServer, body ->
            println body
        }
    }
}

def testRailUser = "automation@leovegas.com"
def testRailAPIKey = "21r5CNabjFgBbpr9iqUu-MAtiUW5OWRrWejWPV2bp"
def testRunId = "232"
def testRailURL = "https://leogears.testrail.net"

prepareReportFile(testRailURL, testRailUser, testRailAPIKey, testRunId)
