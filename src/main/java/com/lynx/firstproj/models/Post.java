package com.lynx.firstproj.models;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title, announce, full_text, structure_img, code, result_img;
    private int views;

    public Post() {
    }

    public Post(String title, String announce, String full_text, String structure_img, String code, String result_img) {
        this.title = title;
        this.announce = announce;
        this.full_text = full_text;
        this.structure_img = structure_img;
        this.code = code;
        this.result_img = result_img;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
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

    public String getAnnounce() {
        return announce;
    }

    public void setAnnounce(String announce) {
        this.announce = announce;
    }

    public String getFull_text() {
        return full_text;
    }

    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }

    public String getStructure_img() {
        return structure_img;
    }

    public void setStructure_img(String structure_img) {
        this.structure_img = structure_img;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResult_img() {
        return result_img;
    }

    public void setResult_img(String result_img) {
        this.result_img = result_img;
    }

}
