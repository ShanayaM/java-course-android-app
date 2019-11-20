package space.harbour.java.movies;

import javax.json.Json;
import javax.json.JsonObject;

public final class Writer implements Jsonable {
    private String name;
    private String type;

    public void setName(final String n) {
        this.name = n;
    }

    public void setType(final String t) {
        this.type = t;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void fromJsonObject(final JsonObject jsonObject) {
        this.name = jsonObject.getString("Name");
        this.type = jsonObject.getString("Type");
    }

    @Override
    public JsonObject toJsonObject() {
        return Json.createObjectBuilder()
                .add("Name", name)
                .add("Type", type)
                .build();
    }

    @Override
    public String toJsonString() {
        return toJsonObject().toString();
    }
}
