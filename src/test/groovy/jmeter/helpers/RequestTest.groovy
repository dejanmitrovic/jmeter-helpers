package jmeter.helpers

 import spock.lang.Specification

 class RequestTest extends Specification {

     def "GET request with valid URL and authorization parameters results in response body received"() {
         when:
         def response = Request.getBodyBySendGetWithAuthorization(
            "https://postman-echo.com/basic-auth", 
            new LinkedHashMap<>(), 
            "postman", 
            "password")

         then:
         response.size() > 0
     }

     def "GET request with empty URL results in IllegalArgumentException with message 'url parameter must be valid URI'"() {
          when:
          def response = Request.getBodyBySendGetWithAuthorization(
             "",
             new LinkedHashMap<>(),
             "postman",
             "password")

          then:
          def ex = thrown(IllegalArgumentException)
          ex.message == "url parameter must be valid URI"
    
    }

    def "GET request with null URL results in IllegalArgumentException with message 'url parameter must be valid URI'"() {
           when:
           def response = Request.getBodyBySendGetWithAuthorization(
              null,
              new LinkedHashMap<>(),
              "postman",
              "password")

           then:
           def ex = thrown(IllegalArgumentException)
           ex.message == "url parameter must be valid URI"
    }

    def "GET request with invalid URL results in IllegalArgumentException with message 'url parameter must be valid URI'"() {
           when:
           def response = Request.getBodyBySendGetWithAuthorization(
              "sksiojskjcsicji.1112",
              new LinkedHashMap<>(),
              "postman",
              "password")

           then:
           def ex = thrown(IllegalArgumentException)
           ex.message == "url parameter must be valid URI"
    }
}
