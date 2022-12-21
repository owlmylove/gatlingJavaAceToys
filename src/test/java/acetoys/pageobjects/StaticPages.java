package acetoys.pageobjects;
import io.gatling.javaapi.core.ChainBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class StaticPages {

    public static ChainBuilder homePage =
            exec(
                    http("Home Page")
                            .get("/")
                            .check(substring("<title>Ace Toys Online Shop</title>"))
                            .check(status().is(200))
                            .check(status().not(404), status().not(500))
                            .check(css("#_csrf", "content").saveAs("csrfToken"))
            );

    public static ChainBuilder ourStory =
            exec(
                    http("Our Story Page")
                            .get("/our-story")
                            .check(regex("Our fictional toy store was founded online in \\d{4}"))
            );

    public static ChainBuilder getInTouch =
            exec(
                    http("Get in Touch Page")
                            .get("/get-in-touch")
                            .check(substring("Sorry about that! Hope you enjoy your stay here anyway..."))
            );

}
