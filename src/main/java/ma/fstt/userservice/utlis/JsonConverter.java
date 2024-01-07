package ma.fstt.userservice.utlis;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {
    public static String convertObjectToJsonString(Object object) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
