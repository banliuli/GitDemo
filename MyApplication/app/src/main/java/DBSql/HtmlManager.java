package DBSql;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import android.os.Environment;
import android.util.Log;

/**
 * Created by lenovo on 2016/12/22.
 */
public class HtmlManager {
    private StringBuffer data = new StringBuffer();
    private int i = 0;
    private int start = 0;
    private int contentstart = 0;
    private FileOutputStream out = null;
    private FileOutputStream out2 = null;
    private FileOutputStream out3 = null;
    private int end = 0;
    private int lastpos = 0;
    private String str1 = null;
    private String contentstr = null;
    private String str2 = "[";
    private String str4 = "]";
    private String iconname = null;
    private String lastcontent = null;
    File file, datafile;

    public void htmlmanager(String title, String content) {
        file = new File(Environment.getExternalStorageDirectory().toString() + "/随手记");
        if (!file.exists()) {
            file.mkdirs();
        }
        datafile = new File(file + "/" + title);
        if (!datafile.exists()) {
            datafile.mkdirs();
        }
        String helpfile1 ="使用说明1"+".txt";
        try{
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                out3 = new FileOutputStream(Environment.getExternalStorageDirectory()+"/驴友记/"+helpfile1);
            }
            StringBuffer row3 = new StringBuffer();
            row3.append("将你要分享的游记名对应的文件夹复制到电脑上，愿你使用愉快！");
            out3.write(row3.toString().getBytes("UTF-8"));
        }catch(IOException e){
        }finally{
            if(out3 !=null)
                try{
                    out3.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
        }
        String helpfile2 ="使用说明2"+".txt";
        try{
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                out2 = new FileOutputStream(Environment.getExternalStorageDirectory()+"/驴友记/"+title+"/"+helpfile2);
            }
            StringBuffer row2 = new StringBuffer();
            row2.append("将这一整个文件夹复制到电脑上后，你就可以去空间、博客、论坛分享，" +
                    "先用浏览器打开文件夹内的网页文件，你就可以看到整篇图文游记，你需要做的是先上传文件内的所有照片，" +
                    "然后从这个网页复制，粘贴文字内容，再对照这个网页简单排版一下，就可以分享了！");
            out2.write(row2.toString().getBytes("UTF-8"));
        }catch(IOException e){
        }finally{
            if(out2 !=null)
                try{
                    out2.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
        }

    }
}