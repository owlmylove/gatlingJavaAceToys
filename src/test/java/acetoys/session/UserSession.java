package acetoys.session;

import io.gatling.javaapi.core.ChainBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class UserSession {

    public static ChainBuilder initSession =
            exec(flushCookieJar())
                    .exec(session -> session.set("productsListPageNumber", 1))
                    .exec(session -> session.set("customerLoggedIn", false))
                    .exec(session -> session.set("itemsInBascket", 0));

    public static ChainBuilder increaseItemsinBascketForSession =
            exec(session -> {
                int itemsInBascket = session.getInt("itemsInBascket");
                return session.set("itemsInBascket", (itemsInBascket +1));
            });


}
