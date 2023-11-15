document.getElementById('createTaskForm').addEventListener('submit', function(e) {
    e.preventDefault();
    createTask();
});

function createTask() {
    const title = document.getElementById('title').value;
    const description = document.getElementById('description').value;

    fetch('/tasks', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ title, description })
    })
    .then(response => response.json())
    .then(task => {
        console.log('Task created:', task);
        loadTasks(); // Обновляем список задач
    })
    .catch(error => console.error('Error creating task:', error));
}

function loadTasks() {
    fetch('/tasks')
        .then(response => response.json())
        .then(tasks => {
            const taskList = document.getElementById('taskList');
            taskList.innerHTML = '';
            tasks.forEach(task => {
                const li = document.createElement('li');
                li.textContent = `${task.title} - ${task.description}`;
                taskList.appendChild(li);
            });
        })
        .catch(error => console.error('Error loading tasks:', error));
}

// Загрузка задач при первой загрузке страницы
loadTasks();
