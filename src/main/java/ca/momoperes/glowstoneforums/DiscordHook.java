package ca.momoperes.glowstoneforums;

import ca.momoperes.glowstoneforums.discord.DiscordEmbed;
import ca.momoperes.glowstoneforums.discord.DiscordMessage;
import ca.momoperes.glowstoneforums.discord.EmbedAuthor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.awt.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class DiscordHook {

    public static String WEBHOOK = null;

    public static void sendMessage(DiscordMessage message) {
        if (WEBHOOK == null) {
            return;
        }
        HttpClient client = HttpClientBuilder.create().build();
        String json = message.build().toJSONString();
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
        EmbedAuthor author = new EmbedAuthor(topic.getAuthor(), topic.getAuthorProfile(), topic.getAuthorImage());
        DiscordEmbed embed = new DiscordEmbed(topic.getTitle(), topic.getContent(), topic.getUrl(), new Color(189, 242, 184), author);
        sendMessage(new DiscordMessage("", "Glowstone Forums Announcement", null, embed));
    }
}
