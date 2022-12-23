package acetoys.pageobjects;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.FeederBuilder;

import java.util.Map;

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

    public static ChainBuilder cyclePagesOfProducts =
            exec(session -> {
                int currentPageNumber = session.getInt("productsListPageNumber");
                int totalPages = session.getInt("categoryPages");
                boolean morePages = currentPageNumber < totalPages;
                System.out.println("More pages?: " + morePages);
                return session.setAll(Map.of(
                        "currentPageNumber", currentPageNumber,
                        "nextPageNumber", (currentPageNumber + 1),
                        "morePages", morePages));
            })
                    .asLongAs("#{morePages}").on(
                            exec(http("Load page #{currentPageNumber} of Products - Category: #{categoryName}")
                                    .get("/category/#{categoryPath}?page=#{currentPageNumber}")
                                    .check(css(".page-item.active").isEL("#{nextPageNumber}")))
                                    .exec(session -> {
                                        int currentPageNumber = session.getInt("currentPageNumber");
                                        int totalPages = session.getInt("categoryPages");
                                        currentPageNumber++;
                                        boolean morePages = currentPageNumber < totalPages;
                                        return session.setAll(Map.of(
                                                "currentPageNumber", currentPageNumber,
                                                "nextPageNumber", (currentPageNumber + 1),
                                                "morePages", morePages));
                                    })

                    );


}
