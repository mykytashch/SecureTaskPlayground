// GreetingResource.java in SecureTaskPlayground/code-with-quarkus/src/main/java/org/acme


package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }
}



// MyEntity.java in SecureTaskPlayground/code-with-quarkus/src/main/java/org/acme


package org.acme;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Example JPA entity.
 *
 * To use it, get access to a JPA EntityManager via injection.
 *
 * {@code
 *     @Inject
 *     EntityManager em;
 *
 *     public void doSomething() {
 *         MyEntity entity1 = new MyEntity();
 *         entity1.field = "field-1";
 *         em.persist(entity1);
 *
 *         List<MyEntity> entities = em.createQuery("from MyEntity", MyEntity.class).getResultList();
 *     }
 * }
 */
@Entity
public class MyEntity {
    @Id
    @GeneratedValue
    public Long id;

    public String field;
}




// Task.java in /Users/mykyta/Desktop/SecureTaskPlayground/code-with-quarkus/src/main/java/org/acme

package org.acme;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;

    // Конструктор по умолчанию
    public Task() {
    }

    // Конструктор с параметрами
    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}





// TaskRepository.java in SecureTaskPlayground/code-with-quarkus/src/main/java/org/acme


package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
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




// TaskResource.java in SecureTaskPlayground/code-with-quarkus/src/main/java/org/acme


package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

    @Inject
    TaskRepository taskRepository;

    @GET
    public List<Task> listTasks() {
        return taskRepository.findAllTasks();
    }

    @POST
    public Task addTask(Task task) {
        return taskRepository.persistTask(task);
    }

    @GET
    @Path("/{id}")
    public Task getTask(@PathParam("id") Long id) {
        Task task = taskRepository.findTaskById(id);
        if (task == null) {
            throw new WebApplicationException("Task with id of " + id + " does not exist.", Response.Status.NOT_FOUND);
        }
        return task;
    }

    @PUT
    @Path("/{id}")
    public Task updateTask(@PathParam("id") Long id, Task task) {
        if (taskRepository.findTaskById(id) == null) {
            throw new WebApplicationException("Task with id of " + id + " does not exist.", Response.Status.NOT_FOUND);
        }
        task.setId(id);
        return taskRepository.updateTask(task);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTask(@PathParam("id") Long id) {
        taskRepository.deleteTask(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}





// src/components/HomePage.js
import React, { useState } from 'react';
import { useKeycloak } from '@react-keycloak/web';
import './HomePage.css';

function HomePage() {
  const { keycloak, initialized } = useKeycloak();
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = async (e) => {
    e.preventDefault();
    // Здесь должен быть код для отправки запроса на бэкенд,
    // который затем аутентифицирует пользователя через Keycloak API.
    // Пример:
    // await authenticateUser(username, password);
  };

  return (
    <div className="homepage">
      <header className="header">
        <h1>Добро пожаловать в TaskMaster!</h1>
        <p>Профессиональное решение для управления задачами</p>
      </header>

      {keycloak && !keycloak.authenticated ? (
        <section className="login-section">
          <form onSubmit={handleLogin} className="login-form">
            <input
              type="text"
              placeholder="Имя пользователя"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
            <input
              type="password"
              placeholder="Пароль"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            <button type="submit">Войти</button>
          </form>
          <a href="/signup">Регистрация</a>
        </section>
      ) : (
        <section className="welcome-section">
          <p>Добро пожаловать, {username}!</p>
        </section>
      )}

      <section className="about-section">
        <h2>О нашем приложении</h2>
        <p>TaskMaster помогает вам организовывать и отслеживать ваши повседневные задачи. С нашим интуитивно понятным интерфейсом и мощными инструментами вы сможете легко управлять своими делами.</p>
      </section>

      {/* Дополнительные секции и элементы */}
    </div>
  );
}

export default HomePage;




import React, { useState } from 'react';
import './SignUpPage.css'; // Путь к CSS-файлу для SignUpPage

function SignUpPage() {
  const [formData, setFormData] = useState({
    username: '',
    email: '',
    password: '',
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Обработка данных формы
  };

  return (
    <div className="signup-page">
      <h1>Регистрация</h1>
      <form onSubmit={handleSubmit} className="signup-form">
        <input type="text" name="username" placeholder="Имя пользователя" onChange={handleChange} />
        <input type="email" name="email" placeholder="Электронная почта" onChange={handleChange} />
        <input type="password" name="password" placeholder="Пароль" onChange={handleChange} />
        <button type="submit">Зарегистрироваться</button>
      </form>
    </div>
  );
}

export default SignUpPage;





















