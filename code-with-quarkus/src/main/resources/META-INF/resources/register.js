document.getElementById('registrationForm').addEventListener('submit', function (e) {
    e.preventDefault();

    const username = document.getElementById('username').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    // Создаем объект данных для отправки на сервер
    const data = {
        username: username,
        email: email,
        password: password
    };

    // Отправляем данные на сервер
    fetch('/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(result => {
        // В этом месте вы можете обработать ответ от сервера, например, отобразить сообщение об успешной регистрации
        console.log('Registration successful:', result);
        alert('Registration successful');
        // Перенаправление на страницу входа
        window.location.href = 'login.html';
    })
    .catch(error => {
        // В случае ошибки регистрации
        console.error('Error during registration:', error);
        alert('Registration failed. Please try again.');
    });
});
