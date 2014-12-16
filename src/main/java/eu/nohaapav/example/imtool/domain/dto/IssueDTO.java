package eu.nohaapav.example.imtool.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Issue DTO.
 *
 * @author Pavol Noha <pavol.noha@teliasonera.com>
 */
public class IssueDTO {

    private String name;
    private String description;

    public IssueDTO(
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
