package com.example.hoang.kaitea.data;

/**
 * Created by HOANG on 9/27/2018.
 */

public class Note
{
    private String title;
    private String content;

    public Note(String title, String content)
    {
        this.title = title;
        this.content = content;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getTitle()
    {

        return title;
    }

    public String getContent()
    {
        return content;
    }


}
