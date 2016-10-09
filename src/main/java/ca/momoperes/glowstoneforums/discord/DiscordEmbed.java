package ca.momoperes.glowstoneforums.discord;

import org.json.simple.JSONObject;

import java.awt.*;

public class DiscordEmbed {

    private final String title;
    private final String description;
    private final String url;
    private final Color color;
    private final EmbedAuthor author;

    public DiscordEmbed(String title, String description, String url, Color color, EmbedAuthor author) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.color = color;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public Color getColor() {
        return color;
    }

    public EmbedAuthor getAuthor() {
        return author;
    }

    public JSONObject build() {
        JSONObject object = new JSONObject();
        if (title != null) {
            object.put("title", title);
        }
        if (description != null) {
            object.put("description", description);
        }
        if (url != null) {
            object.put("url", url);
        }
        if (color != null) {
            object.put("color", 65536 * color.getRed() + 256 * color.getGreen() + color.getBlue());
        }
        if (author != null) {
            object.put("author", author.build());
        }
        return object;
    }
}
