package space.harbour.java.movies;

import javax.json.JsonObject;

interface Jsonable {
    String toJsonString();
    JsonObject toJsonObject();
}
