package ca.momoperes.glowstoneforums;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.List;

public class ForumsPuller implements Runnable {

    private final String POLL_URL = "https://forums.glowstone.net/api/category/12/announcements"; // Annoucements
    private boolean firstRun = true;
    private final List<String> posts = new ArrayList<>();

    @Override
    public void run() {
        while (true) {
            HttpClient client = HttpClientBuilder.create().build();
            try {
                HttpResponse response = client.execute(new HttpGet(POLL_URL));
                String content = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
                JSONObject object = (JSONObject) new JSONParser().parse(content);
                JSONArray topics = (JSONArray) object.get("topics");
                for (int i = 0; i < topics.size(); i++) {
                    JSONObject topicObject = (JSONObject) topics.get(i);
                    String id = (String) topicObject.get("tid");
                    if (posts.contains(id)) {
                        continue;
                    }
                    String title = (String) topicObject.get("title");
                    String url = "https://forums.glowstone.net/topic/" + topicObject.get("slug");
                    JSONObject authorObject = (JSONObject) topicObject.get("user");
                    String author = (String) authorObject.get("username");
                    String authorUrl = "https://forums.glowstone.net/user/" + authorObject.get("userslug");
                    String authorAvatar = "https://forums.glowstone.net" + authorObject.get("picture");
                    posts.add(id);
                    if (!firstRun) {
                        String postContent = getContent((String) topicObject.get("slug"));
                        ForumTopic topic = new ForumTopic(id, title, url, author, authorUrl, authorAvatar, postContent);
                        DiscordHook.onNewTopic(topic);
                        Thread.sleep(5000l);
                    }
                }
            } catch (Exception e) {
                System.out.println("Warning: Error while getting forum topics, " + e.getMessage());
                e.printStackTrace();
                continue;
            }
            firstRun = false;
            try {
                Thread.sleep(10000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String getContent(String slug) throws Exception {
        String api = "https://forums.glowstone.net/api/topic/" + slug;
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(new HttpGet(api));
        String content = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
        JSONObject object = (JSONObject) new JSONParser().parse(content);
        JSONObject post = (JSONObject) ((JSONArray) object.get("posts")).get(0);
        String postContent = ((String) post.get("content")).replaceAll("\\<[^>]*>", "");
        if (postContent.length() > 300) {
            postContent = postContent.substring(0, 300) + "...";
        }
        return postContent;
    }
}
