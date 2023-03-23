package SpringBootRestAPI.PhohtoTech.models;

import jakarta.persistence.*;

@Entity
@Table
public class Phototech {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String serial_number;
    private String matrix_resolution;
    private String viewfinder_type;
    private String optical_zoom;
    private String menu_language;
    private String flash_range;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getMatrix_resolution() {
        return matrix_resolution;
    }

    public void setMatrix_resolution(String matrix_resolution) {
        this.matrix_resolution = matrix_resolution;
    }

    public String getViewfinder_type() {
        return viewfinder_type;
    }

    public void setViewfinder_type(String viewfinder_type) {
        this.viewfinder_type = viewfinder_type;
    }

    public String getOptical_zoom() {
        return optical_zoom;
    }

    public void setOptical_zoom(String optical_zoom) {
        this.optical_zoom = optical_zoom;
    }

    public String getMenu_language() {
        return menu_language;
    }

    public void setMenu_language(String menu_language) {
        this.menu_language = menu_language;
    }

    public String getFlash_range() {
        return flash_range;
    }

    public void setFlash_range(String flash_range) {
        this.flash_range = flash_range;
    }
}
