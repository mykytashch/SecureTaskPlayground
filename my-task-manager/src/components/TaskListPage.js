// src/components/TaskListPage.js
import React, { useState, useEffect } from 'react';
import './TaskListPage.css'; // Путь к CSS-файлу для TaskListPage

function TaskListPage() {
  const [tasks, setTasks] = useState([]);

  useEffect(() => {
    // Загрузка задач
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
