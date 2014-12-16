package eu.nohaapav.example.imtool.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Project DTO.
 *
 * @author Pavol Noha <pavol.noha@teliasonera.com>
 */
public class ProjectDTO {

    private String name;
    private String description;

    public ProjectDTO(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
