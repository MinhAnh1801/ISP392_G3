/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author khucx
 */
public class News {
    private int id;
    private String title;
    private String img; // Store image as byte array
    private String content;
    private String uploadDate;

    public News(int id, String title, String img, String content, String uploadDate) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.content = content;
        this.uploadDate = uploadDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public String toString() {
        return "News{" + "id=" + id + ", title=" + title + ", img=" + img + ", content=" + content + ", uploadDate=" + uploadDate + '}';
    }
    
}
