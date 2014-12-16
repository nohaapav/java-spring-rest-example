package eu.nohaapav.example.imtool.service;

import eu.nohaapav.example.imtool.dao.CommentDao;
import eu.nohaapav.example.imtool.domain.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Comment service.
 *
 * @author Pavol Noha <pavol.noha@teliasonera.com>
 */
@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    @Transactional
    public void save(Comment comment) {
        commentDao.persist(comment);
    }

    @Transactional
    public void delete(Long commentId) {
        Comment comment = commentDao.find(commentId);
        commentDao.remove(comment);
    }
}
