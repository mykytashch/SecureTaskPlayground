import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import HomePage from './components/HomePage';
import SignUpPage from './components/SignUpPage';
import TaskListPage from './components/TaskListPage';
import TaskFormPage from './components/TaskFormPage';


function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/signup" element={<SignUpPage />} />
        <Route path="/tasks" element={<TaskListPage />} />
        <Route path="/task/:id" element={<TaskFormPage />} />
        {/* Дополнительные маршруты могут быть добавлены здесь */}
      </Routes>
    </Router>
  );
}

export default App;
