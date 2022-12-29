package models.booker;

import base.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenBody extends BaseModel {
    public static final String schemaJson = "token/token.json";
    @JsonProperty("username")
    private final String username;
    @JsonProperty("password")
    private final String password;

    public TokenBody() {
        this.username = "admin";
        this.password = "password123";
    }
}
