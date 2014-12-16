package eu.nohaapav.example.imtool.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Comment DTO.
 *
 * @author Pavol Noha <pavol.noha@teliasonera.com>
 */
public class CommentDTO {

    private String text;
    private String author;

    public CommentDTO(
            @JsonProperty("text") String text,
            @JsonProperty("author") String author) {
        this.text = text;
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }
}
