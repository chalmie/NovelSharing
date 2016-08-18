package com.chalmie.novelsharing.models;

/**
 * Created by chalmie on 8/3/16.
 */
import org.parceler.Parcel;

import java.text.SimpleDateFormat;
import java.util.Date;

@Parcel
public class Book {
    String title;
    String author;
    String image;
    String description;
    String previewLink;
    int pageCount;
    String publishedDate;
    String category;
    private String pushId;
    private String index;

    public Book() {}

    public Book(String title, String author, String image, String description, String previewLink, int pageCount, String publishedDate, String category) {
        this.title = title;
        this.author = author;
        this.image = image;
        this.description = description;
        this.previewLink = previewLink;
        this.pageCount = pageCount;
        this.publishedDate = publishedDate;
        this.category = category;
        this.index = "not_specified";
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getCategory() {
        return category;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

}
