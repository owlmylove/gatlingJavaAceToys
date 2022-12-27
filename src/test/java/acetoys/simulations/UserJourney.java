package acetoys.simulations;

import io.gatling.javaapi.core.ChainBuilder;
import java.time.Duration;
import static io.gatling.javaapi.core.CoreDsl.*;
import static acetoys.session.UserSession.*;
import acetoys.pageobjects.*;


public class UserJourney {

    private static final Duration LOW_PAUSE = Duration.ofMillis(1000);
    private static final Duration HIGH_PAUSE = Duration.ofMillis(3000);

    public static ChainBuilder browseStore =
            exec(initSession)
                    .exec(StaticPages.homePage)
                    .pause(LOW_PAUSE)
                    .exec(StaticPages.ourStory)
                    .pause(LOW_PAUSE, HIGH_PAUSE)
                    .exec(StaticPages.getInTouch)
                    .pause(LOW_PAUSE, HIGH_PAUSE)
                    .repeat(3).on(
                            exec(Category.allCategoriesAllProducts)
                                    .pause(LOW_PAUSE, HIGH_PAUSE)
                                    .exec(Category.cyclePagesOfProducts)
                                    .pause(LOW_PAUSE, HIGH_PAUSE)
                                    .exec(Product.productPage)
                    );

    public static ChainBuilder abandonBasket =
            exec(initSession)
                    .exec(StaticPages.homePage)
                    .pause(LOW_PAUSE, HIGH_PAUSE)
                    .exec(Category.allCategoriesAllProducts)
                    .pause(LOW_PAUSE, HIGH_PAUSE)
                    .exec(Product.productPage)
                    .pause(LOW_PAUSE, HIGH_PAUSE)
                    .exec(Product.addProductToCart);

    public static ChainBuilder completePurchase =
            exec(initSession)
                    .exec(StaticPages.homePage)
                    .pause(LOW_PAUSE, HIGH_PAUSE)
                    .exec(Category.allCategoriesAllProducts)
                    .pause(LOW_PAUSE, HIGH_PAUSE)
                    .exec(Product.productPage)
                    .pause(LOW_PAUSE, HIGH_PAUSE)
                    .exec(Product.addProductToCart)
                    .exec(Cart.cartOverviewPage)
                    .pause(LOW_PAUSE, HIGH_PAUSE)
                    .exec(Cart.increaseCartQuantity)
                    .pause(LOW_PAUSE, HIGH_PAUSE)
                    .exec(Cart.decreaseCartQuantity)
                    .pause(LOW_PAUSE, HIGH_PAUSE)
                    .exec(Cart.checkoutCart)
                    .pause(LOW_PAUSE, HIGH_PAUSE)
                    .exec(Customer.logout);

}
