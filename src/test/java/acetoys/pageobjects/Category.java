package acetoys.pageobjects;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.FeederBuilder;
import net.sf.saxon.om.Chain;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class Category {

    private static final FeederBuilder<String> categoryFeeder =
            csv("data/categoryDetails.csv").circular();

    public static ChainBuilder allCategoriesAllProducts =
            feed(categoryFeeder)
                    .exec(
                http("#{categoryName}")
                        .get("/category/#{categoryPath}")
                        .check(css("#CategoryName").isEL("#{categoryName}"))
        );


    public static ChainBuilder allCategoriesFirstPage =
            exec(
                    http("Pagination Category Page 1")
                            .get("/category/all?page=0")
                            .check(css("#CategoryName").is("All Products"))
            );

    public static ChainBuilder getAllCategoriesSecondPage =
            exec(
                    http("Pagination Category Page 2")
                            .get("/category/all?page=1")
                            .check(css(".page-item.active").is("2"))
            );

    public static ChainBuilder getAllCategoriesThirdPage =
            exec(
                    http("Pagination Category Page 3")
                            .get("/category/all?page=2")
                            .check(css(".page-item.active").is("3"))
            );

}
