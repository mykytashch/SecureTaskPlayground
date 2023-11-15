document.getElementById('loginForm').addEventListener('submit', function (e) {
    e.preventDefault();
    loginUser();
});

function loginUser() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    // Отправляем данные на сервер для аутентификации
    fetch('/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `username=${username}&password=${password}`,
    })
        .then((response) => {
            if (response.status === 200) {
                console.log('Login successful');
                window.location.href = '/tasks.html'; // Перенаправляем на страницу задач
            } else {
                console.error('Login failed');
                // Здесь можно добавить логику для обработки ошибки входа
            }
        })
        .catch((error) => console.error('Error logging in:', error));
}
