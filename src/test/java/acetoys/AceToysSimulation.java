package acetoys;

import acetoys.simulations.TestScenario;
import acetoys.simulations.TestSetUps;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;


public class AceToysSimulation extends Simulation {

  private static final String DOMAIN = "acetoys.uk";

  private HttpProtocolBuilder httpProtocol = http
          .baseUrl("https://" + DOMAIN)
          .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*detectportal\\.firefox\\.com.*"))
          .acceptEncodingHeader("gzip, deflate")
          .acceptLanguageHeader("ru-RU,ru;q=0.9,en;q=0.8");


  {
    setUp(TestSetUps.instantUsers).protocols(httpProtocol);
//    setUp(TestSetUps.rampUsers).protocols(httpProtocol);
//    setUp(TestSetUps.complexInjection).protocols(httpProtocol);
//    setUp(TestSetUps.closedModel).protocols(httpProtocol);
  }
}
