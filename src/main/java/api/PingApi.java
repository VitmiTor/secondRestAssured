package api;

import io.restassured.http.Method;
import io.restassured.response.Response;

public class PingApi extends BaseApi {
    private final String path = "ping";

    public PingApi(boolean isAuth) {
        super(isAuth);
    }

    public Response healthCheck() {
        setResourcePath(path);
        return apiCallManager(Method.GET);
    }
}
