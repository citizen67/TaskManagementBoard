import './App.css';
import Dashboard from './components/Dashboard';
import Header from './components/Layout/Header';
import "bootstrap/dist/css/bootstrap.min.css"
import { BrowserRouter, Routes, Route } from "react-router-dom";
import AddProject from './components/Project/AddProject';
import { Provider } from "react-redux";
import store from './store';

function App() {
  return (
    <Provider store={store}>
      {/* Upgrading to React Router V6 */}
      <BrowserRouter>
        <Header />
        <Routes>
          {/* This route included for pre-load main App page */}
          <Route path="/" element={<Dashboard />} />

          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/addProject" element={<AddProject />} />
        </Routes>
      </BrowserRouter>
    </Provider>
  );
}

export default App;
