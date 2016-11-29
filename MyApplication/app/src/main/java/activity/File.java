package activity;

/**
 * Created by lenovo on 2016/11/28.
 */
public class File {
    private Long id;
    private String filename;

    public File( Long id, String filename) {
        this.filename = filename;
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
