/*
References:
* https://eclipse-ee4j.github.io/angus-mail/
* https://mvnrepository.com/artifact/com.google.code.gson/gson/2.10.1
* https://www.jsonschema2pojo.org/
* https://myaccount.google.com/security?hl=en
 */
import com.google.gson.Gson;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Gson g = new Gson();
            Scanner fs = new Scanner(
                    Paths.get(
                            Objects.requireNonNull(
                                    Main.class.getClassLoader().getResource("config.json")).toURI()),
                    StandardCharsets.UTF_8);
            Config c = g.fromJson(fs.useDelimiter("\\A").next(), Config.class);

            // Recipient's email ID needs to be mentioned.
            String to = "trevor-hartman@redwoods.edu";
            // Sender's email ID needs to be mentioned
            String from = "viable2005@gmail.com";
            // Subject and Body
            String subject = "Hello There";
            String body = "This is a Java Mail Test";
            SendMail client = new SendMail(
                    c.getMailHost(), c.getMailPort(), c.getUserName(), c.getPassword(),
                    c.getEncryptionType(), c.getDoAuth());
            client.send(to, from, subject, body);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

}