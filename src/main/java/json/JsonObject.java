package json;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private Map<String, Json> jsonObject;

    public JsonObject(JsonPair... jsonPairs) {
        jsonObject = new HashMap<>();
        for (JsonPair pair: jsonPairs) {
            jsonObject.put(pair.key, pair.value);
        }
    }

    @Override
    public String toJson() {
        StringBuilder jsonBuilder = new StringBuilder();
        Iterator<Map.Entry<String, Json>> iterator = jsonObject
                .entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Json> pair = iterator.next();
            jsonBuilder.append(pair.getKey()).append(": ")
                    .append(pair.getValue().toJson());
            if (iterator.hasNext()) {
                jsonBuilder.append(", ");
            }
        }
        return "{" + jsonBuilder.toString() + "}";
    }

    public void add(JsonPair jsonPair) {
        this.jsonObject.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        return this.jsonObject.get(name);
    }

    public JsonObject projection(String... names) {
        JsonObject newJson = new JsonObject();
        for (String name : names) {
            if (jsonObject.containsKey(name)) {
                newJson.add(new JsonPair(name, jsonObject.get(name)));
            }
        }
        return newJson;
    }
}
