import { FunctionComponent } from 'react'
import { Container, Nav, Navbar } from 'react-bootstrap'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import { App } from '../App'
import { StaffHome } from './home'

import logo from '../../../assets/images/logo-redbee-conf.svg'
import { ConferencesHome } from '../../../conferences'

const StaffApp: FunctionComponent = () => (
  <App>
    <Navbar bg="light" expand="lg">
      <Container>
        <Navbar.Brand href="/">
          <img src={logo} alt="logo" />
        </Navbar.Brand>
        <Nav className="me-auto">
          <Nav.Link href="/">Home</Nav.Link>
          <Nav.Link href="/conferences">Ediciones</Nav.Link>
        </Nav>
      </Container>
    </Navbar>
    <Router>
      <Switch>
        <Route path="/" exact>
          <StaffHome />
        </Route>
        <Route path="/conferences">
          <ConferencesHome />
        </Route>
      </Switch>
    </Router>
  </App>
)

export default StaffApp
