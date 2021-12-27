import { FunctionComponent } from 'react'
import { Container, Nav, Navbar } from 'react-bootstrap'
import { BrowserRouter as Router, Link, Route, Switch } from 'react-router-dom'
import { App } from '../App'
import { StaffHome } from './home'
import { ConferencesHome, ConferencesModule } from '../../../conferences'
import { STAFF_BASE_PATH, STAFF_CONFERENCES_PATH } from './const'

import logo from '../../../assets/images/logo-redbee-conf.svg'

const StaffApp: FunctionComponent = () => (
  <App>
    <ConferencesModule>
      <Router>
        <Navbar bg="light" expand="lg">
          <Container>
            <Navbar.Brand href={STAFF_BASE_PATH}>
              <img src={logo} alt="logo" />
            </Navbar.Brand>
            <Nav className="me-auto">
              <Nav.Link as={Link} to={STAFF_BASE_PATH}>
                Home
              </Nav.Link>
              <Nav.Link as={Link} to={STAFF_CONFERENCES_PATH}>
                Ediciones
              </Nav.Link>
            </Nav>
          </Container>
        </Navbar>
        <Switch>
          <Route path={STAFF_BASE_PATH} exact>
            <StaffHome />
          </Route>
          <Route path={STAFF_CONFERENCES_PATH}>
            <ConferencesHome />
          </Route>
        </Switch>
      </Router>
    </ConferencesModule>
  </App>
)

export default StaffApp
