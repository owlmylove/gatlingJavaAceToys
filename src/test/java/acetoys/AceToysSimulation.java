package acetoys;

import acetoys.pageobjects.*;
import acetoys.session.UserSession;
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


  private ScenarioBuilder scn = scenario("AceToysSimulation")
         .exec(UserSession.initSession)
    .exec(StaticPages.homePage)
    .pause(2)
    .exec(StaticPages.ourStory)
    .pause(2)
    .exec(StaticPages.getInTouch)
    .pause(2)
    .exec(Category.allCategoriesAllProducts)
    .pause(2)
    .exec(Category.allCategoriesAllProducts)
    .pause(2)
    .exec(Category.allCategoriesAllProducts)
    .pause(2)
    .exec(Category.allCategoriesAllProducts)
    .pause(2)
    .exec(Category.cyclePagesOfProducts)
    .pause(2)
    .exec(Product.productPage)
    .pause(2)
    .exec(Product.addProductToCart)
    .pause(2)
    .exec(Product.addProductToCart)
    .pause(2)
    .exec(Product.addProductToCart)
    .pause(2)
    .exec(Cart.cartOverviewPage)
    .pause(2)
    .exec(Cart.increaseCartQuantity)
    .pause(2)
    .exec(Cart.increaseCartQuantity)
    .pause(2)
    .exec(Cart.increaseCartQuantity)
    .pause(2)
    .exec(Cart.decreaseCartQuantity)
    .pause(2)
          .exec(Cart.decreaseCartQuantity)
          .pause(2)
          .exec(Cart.decreaseCartQuantity)
          .pause(2)
          .exec(Cart.decreaseCartQuantity)
          .pause(2)
    .exec(Cart.checkoutCart)
    .pause(2)
    .exec(Customer.logout);

  {
	  setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
  }
}
