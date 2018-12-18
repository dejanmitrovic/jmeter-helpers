package jmeter.helpers

import spock.lang.Specification

class RequestTest extends Specification {

	def "Initializing TestRailAPI with empty testRailURL throws IllegalArgumentException with message 'testRailURL parameter must be valid URL'"() {
    	when:
		def api = new TestRailAPI("", "", "")

        then:
        def ex = thrown(IllegalArgumentException)
        ex.message == "testRailURL parameter must be valid URL"
	}

	def "Initializing TestRailAPI with null testRailURL throws IllegalArgumentException with message 'testRailURL parameter must be valid URL'"() {
         when:
         def api = new TestRailAPI(null, "", "")

         then:
         def ex = thrown(IllegalArgumentException)
         ex.message == "testRailURL parameter must be valid URL"
     }

	def "Initializing TestRailAPI with invalid testRailURL throws IllegalArgumentException with message 'testRailURL parameter must be valid URL'"() {
         when:
         def api = new TestRailAPI("1sdlsmkms.12.sss", "", "")

         then:
         def ex = thrown(IllegalArgumentException)
         ex.message == "testRailURL parameter must be valid URL"
     }
}
