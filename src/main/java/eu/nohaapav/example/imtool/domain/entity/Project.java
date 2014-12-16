package eu.nohaapav.example.imtool.domain.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Project entity.
 *
 * @author Pavol Noha <pavol.noha@teliasonera.com>
 */
@Entity
@Table(name = "PROJECT")
public class Project {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Issue> issues;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public static class Builder {

        private Project project;

        public Builder() {
            project = new Project();
        }

        public Builder withName(String name) {
            this.project.name = name;
            return this;
        }

        public Builder withDescription(String description) {
            this.project.description = description;
            return this;
        }

        public Builder withIssues(List<Issue> issues) {
            this.project.issues = issues;
            return this;
        }

        public Project build() {
            return this.project;
        }
    }
}
