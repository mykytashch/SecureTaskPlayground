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
