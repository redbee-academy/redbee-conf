import { FunctionComponent } from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  useHistory
} from "react-router-dom";
import { App } from "../App";
import UserLoginGoogleButton from "../LoginGoogle/UserLoginGoogleButton";
import Postulate from "./Postulate/Postulate";



const PrivateRoute = (props: any) => {
  const { children, ...rest } = props;

  const history: any = useHistory()

  if (!history?.location?.state?.name) {
    history.push("/")
    return null
  }

  return <Route {...rest}>{children}</Route>
}

const Landing = () => <div>Esta es una hermosa landing <UserLoginGoogleButton /> </div>


const EndUserApp: FunctionComponent = () => {

  return <Router>
    <div>
      {/* A <Switch> looks through its children <Route>s and
        renders the first one that matches the current URL. */}
      <Switch>
        <Route path="/" exact>
          <Landing />
        </Route>
        <PrivateRoute path="/postulate">
          <Postulate />
        </PrivateRoute>
      </Switch>
    </div>
  </Router>

}

export default EndUserApp;