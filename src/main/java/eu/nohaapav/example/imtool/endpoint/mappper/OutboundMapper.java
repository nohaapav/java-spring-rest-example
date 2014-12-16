package eu.nohaapav.example.imtool.endpoint.mappper;

import eu.nohaapav.example.imtool.domain.dto.CommentDTO;
import eu.nohaapav.example.imtool.domain.dto.IssueDTO;
import eu.nohaapav.example.imtool.domain.dto.ProjectDTO;
import eu.nohaapav.example.imtool.domain.entity.Comment;
import eu.nohaapav.example.imtool.domain.entity.Issue;
import eu.nohaapav.example.imtool.domain.entity.Project;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Common outbound mapper.
 *
 * @author Pavol Noha <pavol.noha@teliasonera.com>
 */
@Component
public class OutboundMapper {

    public List<ProjectDTO> mapToProjects(List<Project> projects) {
        return projects.stream()
                .map(project -> new ProjectDTO(project.getName(), project.getDescription()))
                .collect(Collectors.toList());
    }

    public List<IssueDTO> mapToIssues(List<Issue> issues) {
        return issues.stream()
                .map(issue -> new IssueDTO(issue.getName(), issue.getDescription()))
                .collect(Collectors.toList());
    }

    public List<CommentDTO> mapToComments(List<Comment> comments) {
        return comments.stream()
                .map(comment -> new CommentDTO(comment.getText(), comment.getAuthor()))
                .collect(Collectors.toList());
    }
}
