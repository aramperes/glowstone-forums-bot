package ca.momoperes.glowstoneforums;

import org.json.simple.JSONObject;

public class DiscordMessage {

    private String image;
    private String message;

    public DiscordMessage(String image, String message) {
        this.image = image;
        this.message = message;
    }

    public String getImage() {
        return image;
    }

    public String getMessage() {
        return message;
    }

    public String build() {
        JSONObject object = new JSONObject();
        object.put("content", getMessage());
        if (getImage() != null)
            object.put("avatar_url", getImage());
        object.put("username", "Glowstone Forums Announcements");
        return object.toJSONString();
    }
}
