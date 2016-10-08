package ca.momoperes.glowstoneforums;

public class ForumTopic {

    private final String id;
    private final String title;
    private final String url;
    private final String author;
    private final String authorProfile;
    private final String authorImage;

    public ForumTopic(String id, String title, String url, String author, String authorProfile, String authorImage) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.author = author;
        this.authorProfile = authorProfile;
        this.authorImage = authorImage;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorProfile() {
        return authorProfile;
    }

    public String getAuthorImage() {
        return authorImage;
    }
}
