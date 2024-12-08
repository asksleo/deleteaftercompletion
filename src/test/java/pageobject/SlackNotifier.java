package pageobject;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class SlackNotifier {

    private static final String WEBHOOK_URL = "https://hooks.slack.com/services/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";

    public static void main(String[] args) {
        String message = "Test execution completed: [link to report]";
        sendSlackNotification(message);
    }

    public static void sendSlackNotification(String message) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(WEBHOOK_URL);
            String jsonPayload = "{ \"text\": \"" + message + "\" }";
            StringEntity entity = new StringEntity(jsonPayload);
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-Type", "application/json");

            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                System.out.println(EntityUtils.toString(response.getEntity()));
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
