import './App.css';
import Dashboard from './components/Dashboard';
import Header from './components/Layout/Header';
import "bootstrap/dist/css/bootstrap.min.css"
import { BrowserRouter, Routes, Route } from "react-router-dom";
import AddProject from './components/Project/AddProject';

function App() {
  return (
    // Upgrading to React Router V6
    <BrowserRouter>
      <Header />
      <Routes>
        {/* This part need for pre-load main App page */}
        <Route path="/" element={<Dashboard />} />

        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/addProject" element={<AddProject />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
