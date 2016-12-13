package activity;

/**
 * Created by Administrator on 2016/12/12.
 */
public class Mfile {
    private long Id;
    private String Name;
    private String Time;
    private int Url;

    public Mfile(long id, String name, String time, int url) {
        Id = id;
        Name = name;
        Time = time;
        Url = url;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getUrl() {
        return Url;
    }

    public void setUrl(int url) {
        Url = url;
    }
}
