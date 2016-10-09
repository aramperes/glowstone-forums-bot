package ca.momoperes.glowstoneforums.discord;

import org.json.simple.JSONObject;

public class EmbedAuthor {

    private final String name;
    private final String url;
    private final String icon;

    public EmbedAuthor(String name, String url, String icon) {
        this.name = name;
        this.url = url;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getIcon() {
        return icon;
    }

    public JSONObject build() {
        JSONObject object = new JSONObject();
        object.put("name", name);
        if (url != null) {
            object.put("url", url);
        }
        if (icon != null) {
            object.put("icon_url", icon);
        }
        return object;
    }
}
