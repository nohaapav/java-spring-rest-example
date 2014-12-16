package eu.nohaapav.example.imtool.service;

import eu.nohaapav.example.imtool.dao.IssueDao;
import eu.nohaapav.example.imtool.domain.entity.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Issue service.
 *
 * @author Pavol Noha <pavol.noha@teliasonera.com>
 */
@Service
public class IssueService {

    @Autowired
    private IssueDao issueDao;

    @Transactional(readOnly = true)
    public Issue get(Long issueId) {
        return issueDao.find(issueId);
    }

    @Transactional
    public void save(Issue issue) {
        issueDao.persist(issue);
    }

    @Transactional
    public void delete(Long issueId) {
        Issue issue = issueDao.find(issueId);
        issueDao.remove(issue);
    }
}
