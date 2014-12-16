package eu.nohaapav.example.imtool.endpoint;

import eu.nohaapav.example.imtool.domain.dto.CommentDTO;
import eu.nohaapav.example.imtool.domain.entity.Comment;
import eu.nohaapav.example.imtool.domain.entity.Issue;
import eu.nohaapav.example.imtool.endpoint.mappper.InboundMapper;
import eu.nohaapav.example.imtool.endpoint.mappper.OutboundMapper;
import eu.nohaapav.example.imtool.service.CommentService;
import eu.nohaapav.example.imtool.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static eu.nohaapav.example.imtool.endpoint.ServiceParameters.PARAM_COMMENT_ID;
import static eu.nohaapav.example.imtool.endpoint.ServiceParameters.PARAM_ISSUE_ID;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Comment controller.
 *
 * @author Pavol Noha <pavol.noha@teliasonera.com>
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private IssueService issueService;

    @Autowired
    private InboundMapper inMapper;

    @Autowired
    private OutboundMapper outMapper;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = "/issues/{issueId}/comments",
            method = RequestMethod.POST,
            consumes = APPLICATION_JSON_VALUE
    )
    public void save(
            @PathVariable(value = PARAM_ISSUE_ID) Long issueId,
            @RequestBody CommentDTO commentDTO) {
        Issue issue = issueService.get(issueId);
        Comment comment = inMapper.mapToComment(commentDTO);
        comment.setIssue(issue);
        commentService.save(comment);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = "/issues/{issueId}/comments",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE
    )
    public @ResponseBody
    List<CommentDTO> list(
            @PathVariable(value = PARAM_ISSUE_ID) Long issueId) {
        Issue issue = issueService.get(issueId);
        List<Comment> comments = issue.getComments();
        return outMapper.mapToComments(comments);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = "/comments/{commentId}",
            method = RequestMethod.DELETE
    )
    public void delete(
            @PathVariable(value = PARAM_COMMENT_ID) Long commentId) {
        commentService.delete(commentId);
    }
}
