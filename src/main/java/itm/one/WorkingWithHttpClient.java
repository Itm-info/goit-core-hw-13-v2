package itm.one;

import java.io.IOException;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import itm.http.Http;

public class WorkingWithHttpClient implements IWorkingWithHttpClient {
    private final Http http;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public WorkingWithHttpClient(String siteAddr){
        http = new Http(siteAddr);
    }

    @Override
    public User createUser(User user) throws IOException, InterruptedException {
        String json = objectMapper.writeValueAsString(user);

        HttpResponse<String> response = http.post("/users", json);
        if( response.statusCode()==201 ) {
            return objectMapper.readValue(response.body(), User.class);
        }
        return null;
    }

    @Override
    public User updateUser(User user, int id) throws IOException, InterruptedException {
        String json = objectMapper.writeValueAsString(user);

        HttpResponse<String> response = http.put("/users/"+id, json);
        if( response.statusCode()==200 || response.statusCode()==201 ) {
            return objectMapper.readValue(response.body(), User.class);
        }
        return null;
    }

    @Override
    public HttpResponse<String> deleteUser(int id) throws IOException, InterruptedException {
        return http.delete("/users/"+id);
    }

    @Override
    public List<User> getUsers() throws IOException {
        return objectMapper.readValue(new URL(http.SITE_ADDR + "/users"), new TypeReference<List<User>>(){});
    }

    @Override
    public User getUserById(int id) throws IOException {
        return objectMapper.readValue(new URL(http.SITE_ADDR + "/users/" + id),  User.class);
    }

    @Override
    public List<User> getUsersByUsername(String username) throws IOException {
        return objectMapper.readValue(new URL(http.SITE_ADDR + "/users?username=" + username),  new TypeReference<List<User>>(){});
    }
}
