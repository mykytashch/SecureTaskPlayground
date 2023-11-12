import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './components/HomePage';
import SignUpPage from './components/SignUpPage';
import LoginPage from './components/LoginPage';
import TaskListPage from './components/TaskListPage';
import TaskFormPage from './components/TaskFormPage';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/signup" element={<SignUpPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/tasks" element={<TaskListPage />} />
        <Route path="/task/:id" element={<TaskFormPage />} />
      </Routes>
    </Router>
  );
}

export default App;
