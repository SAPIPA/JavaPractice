package SpringBootRestAPI.PhohtoTech.models;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Image {
    private Long id;
    private String title;

    private Long phototechId;

    private String  path_of_the_samurai;

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
