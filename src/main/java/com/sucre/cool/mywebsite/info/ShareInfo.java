package com.sucre.cool.mywebsite.info;

public class ShareInfo {
    private Integer id;    /*	len: 10*/
    private String title;    /*	len: 140*/
    private String content;    /*	len: 140*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
