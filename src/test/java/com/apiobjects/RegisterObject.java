package com.apiobjects;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class RegisterObject implements Jsonable {

    private String email;
    private String password;

    public String getEmail() {return email;}
	public String getPassword() {return password;}
	public void setEmail(String value) { email=value;}
	public void setPassword(String value) {password=value;}

    @Override
    public String toJson() {
        final StringWriter writable = new StringWriter();
        try {
            this.toJson(writable);
        } catch (final IOException e) {
        }
        return writable.toString();
    }

    @Override
    public void toJson(Writer writer) throws IOException {
        final JsonObject json = new JsonObject();
        json.put("email", this.getEmail());
        json.put("password", this.getPassword());
        json.toJson(writer);

    }
}
