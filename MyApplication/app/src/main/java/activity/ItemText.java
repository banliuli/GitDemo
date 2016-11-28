package activity;

/**
 * Created by lenovo on 2016/11/28.
 */
public class ItemText {
    private Long id;
    private String title;
    private String content;
    private String date;

    public ItemText(Long id,String title,String content, String date) {
        this.content = content;
        this.date = date;
        this.id = id;
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
