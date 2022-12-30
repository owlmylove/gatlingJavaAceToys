package acetoys.simulations;

import io.gatling.javaapi.core.PopulationBuilder;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;

public class TestSetUps {

//    private static final int USER_COUNT = Integer.parseInt(System.getProperty("USERS", "5"));
    private static final int USER_COUNT = Integer.parseInt(System.getenv("USERS"));

//    private static final Duration RAMP_DURATION = Duration.ofSeconds(Integer.parseInt(System.getProperty("RAMP_DURATION", "20")));
private static final Duration RAMP_DURATION = Duration.ofSeconds(Integer.parseInt(System.getenv("RAMP_DURATION")));


    public static PopulationBuilder instantUsers =
            TestScenario.defaultLoadTest
                    .injectOpen(
                           nothingFor(5),
                            atOnceUsers(USER_COUNT));

    public static PopulationBuilder rampUsers =
            TestScenario.defaultLoadTest
                    .injectOpen(
                            nothingFor(5),
                            rampUsers(USER_COUNT).during(RAMP_DURATION));

    public static PopulationBuilder complexInjection =
            TestScenario.defaultLoadTest
                    .injectOpen(
                            constantUsersPerSec(USER_COUNT).during(RAMP_DURATION).randomized(),
                            rampUsersPerSec(USER_COUNT).to(20).during(RAMP_DURATION).randomized()
                    );

    public static PopulationBuilder closedModel =
            TestScenario.highPurchaseLoadTest
                    .injectClosed(
                            constantConcurrentUsers(USER_COUNT).during(RAMP_DURATION),
                            rampConcurrentUsers(USER_COUNT).to(20).during(RAMP_DURATION)
                    );



}
