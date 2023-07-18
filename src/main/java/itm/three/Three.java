package itm.three;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import itm.http.Http;

import java.io.IOException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Three {
    private final Http http;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Three(String siteAddr) {
        http = new Http(siteAddr);
    }

    private String getTodos(String userId) throws IOException, InterruptedException {
        return http.get("/users/" + userId + "/todos").body();
    }

    public <T> void getTodosFiltered(String userId, String filterBy, T value) throws IOException, InterruptedException {
        JsonNode jsonNode = objectMapper.readTree(getTodos(userId));
        Stream<JsonNode> stream = StreamSupport.stream(jsonNode.spliterator(), false);

        stream
                .filter(todo -> todo.get(filterBy).asText().equals(String.valueOf(value)))
                .forEach(System.out::println);
    }
}
