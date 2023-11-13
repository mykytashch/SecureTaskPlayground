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
