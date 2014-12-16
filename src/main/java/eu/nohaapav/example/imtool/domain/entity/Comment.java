package eu.nohaapav.example.imtool.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Comment entity.
 *
 * @author Pavol Noha <pavol.noha@teliasonera.com>
 */
@Entity
@Table(name = "ISSUE_COMMENT")
public class Comment {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "AUTHOR")
    private String author;

    @ManyToOne
    @JoinColumn(name="ISSUE_ID")
    private Issue issue;

    Comment() {
    }

    public Comment(String text, String author) {
        this.text = text;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }
}
