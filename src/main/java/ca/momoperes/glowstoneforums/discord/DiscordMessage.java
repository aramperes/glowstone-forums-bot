package ca.momoperes.glowstoneforums.discord;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DiscordMessage {

    private final String content;
    private final String username;
    private final String avatarUrl;
    private final DiscordEmbed[] embeds;

    public DiscordMessage(String content, String username, String avatarUrl, DiscordEmbed... embeds) {
        this.content = content;
        this.username = username;
        this.avatarUrl = avatarUrl;
        this.embeds = embeds;
    }

    public String getContent() {
        return content;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public DiscordEmbed[] getEmbeds() {
        return embeds;
    }

    public JSONObject build() {
        JSONObject object = new JSONObject();
        object.put("content", getContent());
        if (username != null) {
            object.put("username", username);
        }
        if (avatarUrl != null) {
            object.put("avatar_url", avatarUrl);
        }
        if (embeds.length > 0) {
            JSONArray array = new JSONArray();
            for (DiscordEmbed embed : getEmbeds()) {
                array.add(embed.build());
            }
            object.put("embeds", array);
        }
        return object;
    }
}
