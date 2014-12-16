package eu.nohaapav.example.imtool.domain.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Issue entity.
 *
 * @author Pavol Noha <pavol.noha@teliasonera.com>
 */
@Entity
@Table(name = "ISSUE")
public class Issue {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name="PROJECT_ID")
    private Project project;

    @OneToMany(mappedBy = "issue", cascade = CascadeType.REMOVE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Comment> comments;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Project getProject() {
        return project;
    }

    public String getDescription() {
        return description;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public static class Builder {

        private Issue issue;

        public Builder() {
            issue = new Issue();
        }

        public Builder withName(String name) {
            this.issue.name = name;
            return this;
        }

        public Builder withDescription(String description) {
            this.issue.description = description;
            return this;
        }

        public Builder withComments(List<Comment> comments) {
            this.issue.comments = comments;
            return this;
        }

        public Issue build() {
            return this.issue;
        }
    }
}
