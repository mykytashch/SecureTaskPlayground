// src/components/TaskFormPage.js
import React, { useState } from 'react';
import './TaskFormPage.css'; // Путь к CSS-файлу для TaskFormPage

function TaskFormPage() {
  const [task, setTask] = useState({ title: '', description: '' });

  const handleChange = (e) => {
    setTask({ ...task, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Обработка создания или обновления задачи
  };

  return (
    <div className="task-form-page">
      <h1>Задача</h1>
      <form onSubmit={handleSubmit} className="task-form">
        <input
          type="text"
          name="title"
          placeholder="Название задачи"
          value={task.title}
          onChange={handleChange}
        />
        <textarea
          name="description"
          placeholder="Описание задачи"
          value={task.description}
          onChange={handleChange}
        />
        <button type="submit">Сохранить</button>
      </form>
    </div>
  );
}

export default TaskFormPage;
