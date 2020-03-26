package io.javabrains.movieinfoservices.dto;

public class Movie {
    private Integer id;
    private String title;
    private String name;

    public Movie() {
    }

    public Movie(Integer id, String title, String name) {
        this.id = id;
        this.title = title;
        this.name = name;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
