package org.acme;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class TaskRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Task persistTask(Task task) {
        em.persist(task);
        return task;
    }

    @Transactional
    public Task updateTask(Task task) {
        return em.merge(task);
    }

    @Transactional
    public void deleteTask(Long taskId) {
        Task task = em.find(Task.class, taskId);
        if (task != null) {
            em.remove(task);
        }
    }

    public Task findTaskById(Long id) {
        return em.find(Task.class, id);
    }

    public List<Task> findAllTasks() {
        return em.createQuery("SELECT t FROM Task t", Task.class).getResultList();
    }
}
