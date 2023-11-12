// src/components/TaskFormPage.js
import React, { useState } from 'react';
import axios from 'axios';
import './TaskFormPage.css';

function TaskFormPage() {
  const [task, setTask] = useState({ title: '', description: '' });

  const handleChange = (e) => {
    setTask({ ...task, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/tasks', task);
      console.log('Задача добавлена:', response.data);
      // Опционально: редирект или обновление UI
    } catch (error) {
      console.error('Ошибка при создании задачи:', error);
    }
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
