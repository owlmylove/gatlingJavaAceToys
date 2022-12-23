package acetoys.pageobjects;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.FeederBuilder;

import static acetoys.session.UserSession.*;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class Product {

    private static final FeederBuilder<Object> productFeeder =
            jsonFile("data/productDetails.json").random();

    public static ChainBuilder productPage =
            feed(productFeeder)
                    .exec(
                    http("Load Products Details Page - Product: #{name}")
                            .get("/product/#{slug}")
                            .check(css("#ProductDescription").isEL("#{description}"))
            );

    public static ChainBuilder addProductToCart =
            exec(increaseItemsInBasketForSession)
                    .exec(
                    http("Add Cart #{name}")
                            .get("/cart/add/#{id}")
                            .check(substring("You have <span>#{itemsInBasket}</span> products in your Basket"))
            ).exec(increaseSessionBasketTotal);

}
