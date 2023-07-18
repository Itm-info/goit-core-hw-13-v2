package itm.two;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import itm.http.Http;

import java.io.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Two {
    private final Http http;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Two(String siteAddr){
        http = new Http(siteAddr);
    }

    private String getPosts(String userId) throws IOException, InterruptedException {
        return http.get("/users/" + userId + "/posts").body();
    }

    private String getMaxPostId(String userId) throws IOException, InterruptedException {
        JsonNode jsonNode = objectMapper.readTree(getPosts(userId));
        Stream<JsonNode> stream = StreamSupport.stream(jsonNode.spliterator(), false);

        return stream
                    .max((a, b) -> a.get("id").asInt() - b.get("id").asInt())
                    .get().get("id").asText();
    }

    public String getComments(String postId) throws IOException, InterruptedException {
        return http.get("/posts/" + postId + "/comments").body();
    }

    public void dumpComments(String userId) throws IOException, InterruptedException {
        String maxPostId = getMaxPostId(userId);
        String fileName = "user-" + userId + "-post-" + maxPostId + "-comments.json";
        PrintWriter out = new PrintWriter(fileName);
        out.write(getComments(maxPostId));
        out.close();
    }
}
