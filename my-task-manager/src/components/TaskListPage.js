// src/components/TaskListPage.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './TaskListPage.css';

function TaskListPage() {
  const [tasks, setTasks] = useState([]);

  useEffect(() => {
    const fetchTasks = async () => {
      try {
        const response = await axios.get('http://localhost:8080/tasks');
        setTasks(response.data);
      } catch (error) {
        console.error('Ошибка при загрузке задач:', error);
      }
    };

    fetchTasks();
  }, []);

  return (
    <div className="task-list-page">
      <h1>Список Задач</h1>
      <ul className="task-list">
        {tasks.map(task => (
          <li key={task.id}>
            <span>{task.title}</span>
            {/* Дополнительные действия, такие как редактирование и удаление */}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default TaskListPage;
