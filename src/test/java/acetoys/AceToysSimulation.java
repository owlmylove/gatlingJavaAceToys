package acetoys;

import acetoys.simulations.TestSetUps;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;


public class AceToysSimulation extends Simulation {

// private static final String TEST_TYPE = System.getProperty("TEST_TYPE", "INSTANT_USERS");
  private static final String TEST_TYPE = System.getenv("TEST_TYPE");

  private static final String DOMAIN = "acetoys.uk";

  private  HttpProtocolBuilder httpProtocol = http
          .baseUrl("https://" + DOMAIN)
          .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*detectportal\\.firefox\\.com.*"))
          .acceptEncodingHeader("gzip, deflate")
          .acceptLanguageHeader("ru-RU,ru;q=0.9,en;q=0.8");


  {
    if (TEST_TYPE.equals("INSTANT_USERS")) {
      setUp(TestSetUps.instantUsers).protocols(httpProtocol);
    /*          .assertions(
                      global().responseTime().mean().lt(3),
                      global().successfulRequests().percent().gt(99.0),
                     forAll().responseTime().max().lt(5));
              */

    } else if (TEST_TYPE.equals("RAMP_USERS")) {
      setUp(TestSetUps.rampUsers).protocols(httpProtocol);
    } else if (TEST_TYPE.equals("COMPLEX_INJECTION")) {
      setUp(TestSetUps.complexInjection).protocols(httpProtocol);
    } else if (TEST_TYPE.equals("CLOSED_MODEL")) {
      setUp(TestSetUps.closedModel).protocols(httpProtocol);
    } else {
      setUp(TestSetUps.instantUsers).protocols(httpProtocol);
    }
//   setUp(TestSetUps.rampUsers).protocols(httpProtocol);

    /*
    to run locally go tp folder
    lilit@Hevorhians-MacBook-Pro / % cd /Users/lilit/.jenkins/workspace/AceToys

    this error was in jenkins job log
    Caused: java.io.IOException: Cannot run program "mvn" (in directory "/Users/lilit/.jenkins/workspace/AceToys"): error=2, No such file or directory

     lilit@Hevorhians-MacBook-Pro AceToys % mvn gatling:test -Dgatling.simulationClass=acetoys.AceToysSimulation -e
     */

  }
}

