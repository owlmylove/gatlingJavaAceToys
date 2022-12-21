package acetoys.pageobjects;

import io.gatling.javaapi.core.ChainBuilder;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class Cart {

    public static ChainBuilder cartOverviewPage =
            doIf(session -> !session.getBoolean("customerLoggedIn"))
                    .then(exec(Customer.login))
                    .exec(
                    http("Cart View")
                            .get("/cart/view")
 //                           .check(css("#CategoryHeader").is("Cart Overview"))
            );
    public static ChainBuilder increaseCartQuantity20 =
            exec(
                    http("Add Cart 20 count")
                            .get("/cart/add/20?cartPage=true")

            );

    public static ChainBuilder decreaseCartQuantity20 =
            exec(
                    http("Subtract Cart 20")
                            .get("/cart/subtract/20")
            );

    public static ChainBuilder increaseCartQuantity13 =
            exec(
                    http("Add Cart 13 count")
                            .get("/cart/add/13?cartPage=true")
            );

    public static ChainBuilder checkoutCart =
            exec(
                    http("Checkout")
                            .get("/cart/checkout")
                            .check(substring("Your products are on their way to you now!!"))
            );

}
