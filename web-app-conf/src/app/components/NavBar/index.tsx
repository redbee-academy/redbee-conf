import { FunctionComponent } from "react";
import { Container, Nav, Navbar } from "react-bootstrap";

export const AppNavBar : FunctionComponent = () => {
    return(
        <Navbar variant="dark">
        <Container>
        <Navbar.Brand href="#home">RedbeeConf</Navbar.Brand>
        <Nav className="me-auto">
          <Nav.Link href="#home">Home</Nav.Link>
          <Nav.Link href="#features">Charlas</Nav.Link>
        </Nav>
        </Container>
      </Navbar>
    )
}
export default AppNavBar;