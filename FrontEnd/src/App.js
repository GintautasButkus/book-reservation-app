import Login from "./components/Login"
import Register from "./components/Register";
// import HeaderComponent from './component/HeaderComponent';

import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import HomePage from "./components/HomePage";
import Menu from "./components/Menu";
import AddBook from "./components/AddBook";
import Books from "./components/Books";
import Admin from "./components/AdminBook";


function App() {
  return (
   
      <Router>
        {/* <main className="App"> */}
          <Routes>
            <Route path="/" index element={<HomePage/>}/>
            <Route path="/login" element={<Login/>}/>
            <Route path="/register" element={<Register/>}/>
            <Route path="/books" element={<Books/>}/>
            <Route path="/menu/:id" element = {<Menu/>}/>
            <Route path="/admin" element = {<Admin/>}/>
            <Route path="/add-book" element={<AddBook/>} />
            <Route path="/edit-book/:id" element={<AddBook/>} />
          </Routes>
        {/* </main> */}
      </Router>
    
  );
}

export default App;