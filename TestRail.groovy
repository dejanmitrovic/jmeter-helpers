@Grapes([
        @Grab(
            group="org.slf4j",
            module="slf4j-simple",
            version="1.7.25"
            ),
        @Grab(
            group="io.github.http-builder-ng",
            module="http-builder-ng-core",
            version="1.0.3"
            )
])
import static groovyx.net.http.HttpBuilder.configure
import static groovyx.net.http.ContentTypes.JSON
import groovyx.net.http.*
import java.net.URLEncoder

def prepareReportFile(testRailURL, testRailUser, testRailAPIKey, testRunId) {
    HttpBuilder.configure {
        request.raw = "${testRailURL}/index.php?/api/v2/get_run/${testRunId}"
        request.auth.basic testRailUser, testRailAPIKey
    }.get {
        response.success { fromServer, body ->
            println body.toString()
        }
    }
}
