// src/components/HomePage.js
import React from 'react';
import { useKeycloak } from '@react-keycloak/web';
import './HomePage.css';

function HomePage() {
  const { keycloak } = useKeycloak();

  const handleLogin = () => {
    if (keycloak && !keycloak.authenticated) {
      keycloak.login();
    }
  };

  return (
    <div className="homepage">
      <header className="header">
        <h1>Добро пожаловать в TaskMaster!</h1>
        <p>Ваше удобное решение для управления задачами</p>
      </header>

      <section className="login-section">
        {keycloak && keycloak.authenticated ? (
          <p>Добро пожаловать, {keycloak.tokenParsed.preferred_username}!</p>
        ) : (
          <button onClick={handleLogin}>Войти</button>
        )}
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
