package activity;

/**
 * Created by lenovo on 2016/11/28.
 */
public class File {
    private Long id;
    private String filename;
    private String date;

    public File( Long id, String filename,String date) {
        this.date = date;
        this.filename = filename;
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
