package eu.nohaapav.example.imtool.endpoint;

import eu.nohaapav.example.imtool.domain.dto.ProjectDTO;
import eu.nohaapav.example.imtool.domain.entity.Project;
import eu.nohaapav.example.imtool.endpoint.mappper.InboundMapper;
import eu.nohaapav.example.imtool.endpoint.mappper.OutboundMapper;
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
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Project controller.
 *
 * @author Pavol Noha <pavol.noha@teliasonera.com>
 */
@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private InboundMapper inMapper;

    @Autowired
    private OutboundMapper outMapper;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = APPLICATION_JSON_VALUE
    )
    public void save(
            @RequestBody ProjectDTO projectDto) {
        Project project = inMapper.mapToProject(projectDto);
        projectService.save(project);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE
    )
    public @ResponseBody List<ProjectDTO> list() {
        List<Project> projects = projectService.list();
        return outMapper.mapToProjects(projects);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = "/{projectId}",
            method = RequestMethod.DELETE
    )
    public void delete(
            @PathVariable(value = PARAM_PROJECT_ID) Long projectId) {
        projectService.delete(projectId);
    }
}
