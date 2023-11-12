// src/components/HomePage.js
import React, { useState } from 'react';
import './HomePage.css'; // Путь к CSS-файлу для HomePage

function HomePage() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = (e) => {
    e.preventDefault();
    // Логика входа
  };

  return (
    <div className="homepage">
      <header className="header">
        <h1>Добро пожаловать в TaskMaster!</h1>
        <p>Ваше удобное решение для управления задачами</p>
      </header>

      <section className="login-section">
        <form onSubmit={handleLogin} className="login-form">
          <input 
            type="text" 
            placeholder="Имя пользователя" 
            value={username} 
            onChange={e => setUsername(e.target.value)} 
          />
          <input 
            type="password" 
            placeholder="Пароль" 
            value={password} 
            onChange={e => setPassword(e.target.value)} 
          />
          <button type="submit">Войти</button>
        </form>
        <a href="/signup">Регистрация</a>
      </section>

      <section className="about-section">
        <h2>О нашем приложении</h2>
        <p>TaskMaster помогает вам организовывать и отслеживать ваши повседневные задачи. С нашим интуитивно понятным интерфейсом вы сможете легко управлять своими делами.</p>
      </section>

      {/* Дополнительные секции и элементы */}
    </div>
  );
}

export default HomePage;
