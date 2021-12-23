import { FunctionComponent } from 'react'
import {
  BrowserRouter as Router,
  Switch,
  Route,
  useRouteMatch,
} from 'react-router-dom'
import { UpdateConference } from './update-conference'
import { ConferenceRoute } from './conference-route'
import { CreateConference } from './create-conference'
import { ConferencesPage } from './conferences-page'

export const ConferencesHome: FunctionComponent = () => {
  const { path } = useRouteMatch()
  return (
    <Router>
      <Switch>
        <Route path={`${path}/new`}>
          <CreateConference />
        </Route>
        <Route path={`${path}/:id/edit`}>
          <UpdateConference />
        </Route>
        <Route path={`${path}/:id`}>
          <ConferenceRoute />
        </Route>
        <Route path={`${path}`}>
          <ConferencesPage />
        </Route>
      </Switch>
    </Router>
  )
}
