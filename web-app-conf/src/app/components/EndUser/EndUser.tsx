import { FunctionComponent } from 'react'
import {
  BrowserRouter as Router,
  Switch,
  Route,
  useHistory,
} from 'react-router-dom'
import { ConferenceComponent } from './ConferenceComponent'
import { AppConfigurationContext } from '../../context'
import { AppConfiguration } from '../../domain'
import { PostulateTalk } from '../../../talks'

const CONFIGURATION: AppConfiguration = {
  conferencesUrl: process.env.REACT_APP_MS_CONFERENCES_URL as string,
  speakersUrl: process.env.REACT_APP_MS_SPEAKERS_URL as string,
  talksUrl: process.env.REACT_APP_MS_TALKS_URL as string,
}

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
    <AppConfigurationContext.Provider value={CONFIGURATION}>
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
    </AppConfigurationContext.Provider>
  )
}

export default EndUserApp
