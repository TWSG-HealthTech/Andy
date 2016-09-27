package tw.healthcare.andy.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    public static <T> String serialize(T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T deserialize(String json, Class<T> valueType) {
        try {
            return mapper.readValue(json, valueType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
