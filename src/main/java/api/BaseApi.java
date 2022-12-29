package api;

import base.BaseModel;
import io.restassured.http.Method;
import io.restassured.response.Response;
import utilities.Logs;

public abstract class BaseApi {
    private final RequestManager requestManager;
    protected final Logs logs = new Logs();

    public BaseApi(boolean isAuth) {
        requestManager = new RequestManager(isAuth);
    }

    protected Response apiCallManager(Method method) {
        return requestManager.callApi(method);
    }

    protected BaseApi setResourcePath(String path) {
        requestManager.serResourcePath(path);
        return this;
    }

    protected BaseApi setPathParameter(String key, String value) {
        requestManager.setPathParameter(key, value);
        return this;
    }

    protected BaseApi setRequestBody(BaseModel model) {
        requestManager.setRequestBody(model);
        return this;
    }
}

