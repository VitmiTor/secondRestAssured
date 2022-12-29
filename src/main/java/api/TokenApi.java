package api;

import io.restassured.http.Method;
import io.restassured.response.Response;
import models.booker.TokenBody;

public class TokenApi extends BaseApi {

    private final String path = "auth";


    public TokenApi(boolean isAuth) {
        super(isAuth);
    }

    public Response authentication(TokenBody token) {
        logs.debug("User athentication");
        return setResourcePath(path)
                .setRequestBody(token)
                .apiCallManager(Method.POST);
    }
}
