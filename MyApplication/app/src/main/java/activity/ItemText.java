package activity;

/**
 * Created by lenovo on 2016/11/28.
 */
public class ItemText {
    private Long id;
    private String title;
    private String content;

    public ItemText(Long id,String title,String content) {
        this.content = content;
        this.id = id;
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

<<<<<<< HEAD

=======
>>>>>>> 08300fda08af1c6be2df8046ee25477a55232755
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
