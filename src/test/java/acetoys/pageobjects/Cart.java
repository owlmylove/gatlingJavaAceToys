package acetoys.pageobjects;

import io.gatling.javaapi.core.ChainBuilder;

import static acetoys.session.UserSession.*;
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
    public static ChainBuilder increaseCartQuantity =
            exec(increaseItemsInBasketForSession)
                    .exec(increaseSessionBasketTotal)
                    .exec(
                    http("Increase Product Quantity in Cart - #{name}")
                            .get("/cart/add/#{id}?cartPage=true")
                            .check(css("#grandTotal").isEL("$#{basketTotal}"))

                    );

    public static ChainBuilder decreaseCartQuantity =
            exec(decreaseItemsInBasketForSession)
                    .exec(decreaseSessionBasketTotal)
                    .exec(
                    http("Decrease Product Quantity in Cart - #{name}")
                            .get("/cart/subtract/#{id}")
                            .check(css("#grandTotal").isEL("$#{basketTotal}"))

            );


    public static ChainBuilder checkoutCart =
            exec(
                    http("Checkout")
                            .get("/cart/checkout")
                            .check(substring("Your products are on their way to you now!!"))
            );

}
