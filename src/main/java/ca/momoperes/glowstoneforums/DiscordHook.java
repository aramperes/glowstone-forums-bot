package ca.momoperes.glowstoneforums;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class DiscordHook {

    public static String WEBHOOK = null;

    public static void sendMessage(DiscordMessage message) {
        if (WEBHOOK == null) {
            return;
        }
        HttpClient client = HttpClientBuilder.create().build();
        String json = message.build();
        HttpPost post = new HttpPost(WEBHOOK);
        post.setHeader("Content-Type", "application/json");
        try {
            post.setEntity(new StringEntity(json));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void onNewTopic(ForumTopic topic) {
        String message = "**[New annoucement](" + topic.getUrl() + ") by " + topic.getAuthor() + ".**";
        sendMessage(new DiscordMessage(topic.getAuthorImage(), message));
    }
}
