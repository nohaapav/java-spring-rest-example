package eu.nohaapav.example.imtool.service;

import eu.nohaapav.example.imtool.dao.ProjectDao;
import eu.nohaapav.example.imtool.domain.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Project service.
 *
 * @author Pavol Noha <pavol.noha@teliasonera.com>
 */
@Service
public class ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Transactional(readOnly = true)
    public Project get(Long projectId) {
        return projectDao.find(projectId);
    }

    @Transactional
    public void save(Project project) {
        projectDao.persist(project);
    }

    @Transactional(readOnly = true)
    public List<Project> list() {
        return projectDao.findAll();
    }

    @Transactional
    public void delete(Long projectId) {
        Project project = projectDao.find(projectId);
        projectDao.remove(project);
    }
}
