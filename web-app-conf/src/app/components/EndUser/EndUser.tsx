import { FunctionComponent } from 'react'
import {
  BrowserRouter as Router,
  Switch,
  Route,
  useHistory,
} from 'react-router-dom'
import { ConferenceComponent } from './ConferenceComponent'
import { PostulateTalk } from '../../../talks'
import { App } from '../App'
import { ConferencesModule } from '../../../conferences'

const PrivateRoute = (props: any) => {
  const { children, ...rest } = props

  const history: any = useHistory()

  if (!history?.location?.state?.name) {
    history.push('/')
    return null
  }

  return <Route {...rest}>{children}</Route>
}

const Landing = () => (
  <div>
    <ConferenceComponent />
  </div>
)

const EndUserApp: FunctionComponent = () => {
  return (
    <App>
      <ConferencesModule visible>
        <Router>
          <div>
            {/* A <Switch> looks through its children <Route>s and
        renders the first one that matches the current URL. */}
            <Switch>
              <Route path="/" exact>
                <Landing />
              </Route>
              <PrivateRoute path="/postulate">
                <PostulateTalk />
              </PrivateRoute>
            </Switch>
          </div>
        </Router>
      </ConferencesModule>
    </App>
  )
}

export default EndUserApp
