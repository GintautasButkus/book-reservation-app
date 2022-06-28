import { Container, Nav, Navbar, NavDropdown } from "react-bootstrap"
import {Link} from "react-router-dom"
import bg from "../img/home_bg.jpg"



function LandingPageButton() {
    return <Link to="/register" className="nav-link">
        <button className ="btn btn-warning" style={{"margin-left":"-20px"}} > 
            <span style={{"fontSize":"24px"}}>
                Pradėti kelionę!
            </span>
        </button>
    </Link>
}

function LandingPageNavRegistration() {
    return <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
    <Container fluid>
    <Navbar.Brand href="#home" style={{"fontSize":"40px", padding:"0px", marginLeft:"160px"}} >books 4 fun</Navbar.Brand>
    
      <Nav style={{"fontSize":"18px", padding:"0px"}} >
        <Nav.Link href="/login">Prisijungti</Nav.Link>
        <Nav.Link href="/register">
          Registruotis
        </Nav.Link>
      </Nav>
    
    </Container>
  </Navbar>
}

function LandingFrameMessage() {
    const style = {
        margin: "auto",
        padding: "15% 40% 0% 10%",
        color: "#484848"
    }
    return <div style={style}>
        
        
        <div style={{"fontSize": "4rem", "color": "white", "margin-left":"-10px", "margin-top":"-100px"}}>
            Knygos knygelės!!
        </div>
        
        <div style={{"fontSize": "2rem", "color": "white"}}>
            Išsirink mėgstamiausią knygą.<br/>
            Susirask mėgstamiausius autorius.<br/>
            Ir važiuok į biblioteką !!!<br/>
            
        </div>
        <br />
        <LandingPageButton />
    </div>
}
function LandingFrame() {
    const style = {
        backgroundImage: "url(" + bg + ")",
        backgroundRepeat: "no-repeat",
        backgroundSize: "cover",
        position: "relative",
        height: "100vh",
       
    }
    return <div style={style}>
        <LandingPageNavRegistration/>
        <LandingFrameMessage />
    </div>
}
function HomePage() {
    return <div >
        
        <LandingFrame />
    </div>
}
export default HomePage