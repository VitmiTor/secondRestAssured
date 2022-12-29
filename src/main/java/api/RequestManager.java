package api;

import base.BaseModel;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.Logs;
import utilities.RequestFilter;

public class RequestManager {
    private final RequestSpecification requestSpecification;
    private final String baseUrl = "https://restful-booker.herokuapp.com/";
    private final Logs logs = new Logs();
    private final boolean isAuth;

    public RequestManager(boolean isAuth) {
        this.isAuth = isAuth;
        this.requestSpecification = buildRequestSpecification(isAuth);
    }

    private RequestSpecification buildRequestSpecification(boolean isAuth) {
        var specCustom = RestAssured.requestSpecification =
                new RequestSpecBuilder()
                        .setBaseUri(baseUrl)
                        .setContentType(ContentType.JSON)
                        .build();
        assignAuth();

        return RestAssured.given().spec(specCustom).filter(new RequestFilter());
    }

    private void assignAuth() {
        if (isAuth) {
            logs.debug("Authentication Schema");
            final var authSchema = new PreemptiveBasicAuthScheme();
            authSchema.setUserName("admin");
            authSchema.setPassword("password123");

            RestAssured.authentication = authSchema; //1:34:41- es ist schöner
        } else {
            RestAssured.authentication = RestAssured.DEFAULT_AUTH;
        }
    }

    public void setRequestBody(BaseModel baseModel) {
        logs.debug("Setting requestBody");
        requestSpecification.body(baseModel);
    }

    public void serResourcePath(String value) {
        logs.debug("Setting basepath " + value);
        requestSpecification.basePath(value);
    }

    public void setPathParameter(String key, String value) {
        logs.debug("Setting path Parameter " + value);
        requestSpecification.pathParam(key, value);
    }

    public Response callApi(Method method) {
        return requestSpecification.request(method);
    }
}
