package itm;

import com.fasterxml.jackson.databind.ObjectMapper;
import itm.one.*;
import itm.two.Two;
import itm.three.Three;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello world!");
        String siteAddr = "https://jsonplaceholder.typicode.com";
        ObjectMapper objectMapper = new ObjectMapper();
        WorkingWithHttpClient wwhc = new WorkingWithHttpClient(siteAddr);
        User user = objectMapper.readValue(new File("user.json"), User.class);

        // 1 - 1
        System.out.println("\nPOST user");
        System.out.println(wwhc.createUser(user));

        // 1 - 2
        System.out.println("\nUPDATE user:");
        System.out.println(wwhc.updateUser(user, 1));

        // 1 - 3
        System.out.println("\nDELETE user by id 1:");
        System.out.println(wwhc.deleteUser(1));

        // 1 - 4
        System.out.println("\nGET all users");
        System.out.println(wwhc.getUsers());

        // 1 - 5
        System.out.println("\nGET user by id 9:");
        System.out.println(wwhc.getUserById(9));

        // 1 - 6
        System.out.println("\nGET user by username Samantha:");
        System.out.println(wwhc.getUsersByUsername("Samantha"));

        // 2
        System.out.println("\nWrite all comments to the last post of the user to file");
        Two two = new Two(siteAddr);
        two.dumpComments("3");

        // 3
        System.out.println("\nPrint all Todos filtered by completed = false");
        Three three = new Three(siteAddr);
        three.getTodosFiltered("1", "completed", false);

    }
}