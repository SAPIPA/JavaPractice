package SpringBootRestAPI.PhohtoTech.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private Long phototechId;

    private String  path_of_the_samurai;

    public Image(Long id, String title, Long phototechId, String path_of_the_samurai) {
        this.id = id;
        this.title = title;
        this.phototechId = phototechId;
        this.path_of_the_samurai = path_of_the_samurai;
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

    public Long getPhototechId() {
        return phototechId;
    }

    public void setPhototechId(Long phototechId) {
        this.phototechId = phototechId;
    }

    public String getPath_of_the_samurai() {
        return path_of_the_samurai;
    }

    public void setPath_of_the_samurai(String path_of_the_samurai) {
        this.path_of_the_samurai = path_of_the_samurai;
    }
}