package space.harbour.java.movies;

import javax.json.Json;
import javax.json.JsonObject;

public final class Actor implements Jsonable {
    private String name;
    private String as;

    public void setName(final String n) {
        this.name = n;
    }

    public void setAs(final String a) {
        this.as = a;
    }

    public String getName() {
        return name;
    }

    public String getAs() {
        return as;
    }

    @Override
    public JsonObject toJsonObject() {
        return Json.createObjectBuilder()
                .add("Name", name)
                .add("As", as)
                .build();
    }

    @Override
    public String toJsonString() {
        return toJsonObject().toString();
    }

    public void fromJsonObject(final JsonObject jsonObject) {
        this.name = jsonObject.getString("Name");
        this.as = jsonObject.getString("As");
    }
}

