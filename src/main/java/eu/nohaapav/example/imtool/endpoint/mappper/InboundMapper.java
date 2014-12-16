package eu.nohaapav.example.imtool.endpoint.mappper;

import eu.nohaapav.example.imtool.domain.dto.CommentDTO;
import eu.nohaapav.example.imtool.domain.dto.IssueDTO;
import eu.nohaapav.example.imtool.domain.dto.ProjectDTO;
import eu.nohaapav.example.imtool.domain.entity.Comment;
import eu.nohaapav.example.imtool.domain.entity.Issue;
import eu.nohaapav.example.imtool.domain.entity.Project;
import org.springframework.stereotype.Component;

/**
 * Common inbound mapper.
 *
 * @author Pavol Noha <pavol.noha@teliasonera.com>
 */
@Component
public class InboundMapper {

    public Project mapToProject(ProjectDTO project) {
        return new Project.Builder()
                .withName(project.getName())
                .withDescription(project.getDescription())
                .build();
    }

    public Issue mapToIssue(IssueDTO issue) {
        return new Issue.Builder()
                .withName(issue.getName())
                .withDescription(issue.getDescription())
                .build();
    }

    public Comment mapToComment(CommentDTO comment) {
        return new Comment(
                comment.getText(),
                comment.getAuthor());
    }
}
