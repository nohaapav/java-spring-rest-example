package eu.nohaapav.example.imtool.endpoint;

import eu.nohaapav.example.imtool.domain.dto.IssueDTO;
import eu.nohaapav.example.imtool.domain.entity.Issue;
import eu.nohaapav.example.imtool.domain.entity.Project;
import eu.nohaapav.example.imtool.endpoint.mappper.InboundMapper;
import eu.nohaapav.example.imtool.endpoint.mappper.OutboundMapper;
import eu.nohaapav.example.imtool.service.IssueService;
import eu.nohaapav.example.imtool.service.ProjectService;
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

import static eu.nohaapav.example.imtool.endpoint.ServiceParameters.PARAM_PROJECT_ID;
import static eu.nohaapav.example.imtool.endpoint.ServiceParameters.PARAM_ISSUE_ID;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Issue controller.
 *
 * @author Pavol Noha <pavol.noha@teliasonera.com>
 */
@Controller
public class IssueController {

    @Autowired
    private IssueService issueService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private InboundMapper inMapper;

    @Autowired
    private OutboundMapper outMapper;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = "/projects/{projectId}/issues",
            method = RequestMethod.POST,
            consumes = APPLICATION_JSON_VALUE
    )
    public void save(
            @PathVariable(value = PARAM_PROJECT_ID) Long projectId,
            @RequestBody IssueDTO issueDto) {
        Project project = projectService.get(projectId);
        Issue issue = inMapper.mapToIssue(issueDto);
        issue.setProject(project);
        issueService.save(issue);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = "/projects/{projectId}/issues",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE
    )
    public @ResponseBody List<IssueDTO> list(
            @PathVariable(value = PARAM_PROJECT_ID) Long projectId) {
        Project project = projectService.get(projectId);
        List<Issue> issues = project.getIssues();
        return outMapper.mapToIssues(issues);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = "/issues/{issueId}",
            method = RequestMethod.DELETE
    )
    public void delete(
            @PathVariable(value = PARAM_ISSUE_ID) Long issueId) {
        issueService.delete(issueId);
    }
}
