package activity;

/**
 * Created by lenovo on 2016/11/28.
 */
public class ItemText {
    private Long id;
    private String title;
    private String content;
    public boolean checked;

    public ItemText(Long id,String title,String content,Boolean checked) {
        this.content = content;
        this.id = id;
        this.title = title;
        this.checked=checked;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }
}
