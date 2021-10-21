import { FunctionComponent } from 'react';
import { AppConfiguration } from '../domain';
import { AppConfigurationContext } from '../context';
import { ConferenceComponent } from './Staff/Conferences/components/Conference/Conference';
import { MuiPickersUtilsProvider } from '@material-ui/pickers';
import DateFnsUtils from '@date-io/date-fns'

const CONFIGURATION: AppConfiguration = {
  conferencesUrl: process.env.REACT_APP_MS_CONFERENCES_URL as string,
  speakersUrl: process.env.REACT_APP_MS_SPEAKERS_URL as string,
  talksUrl: process.env.REACT_APP_MS_TALKS_URL as string
}

export const App: FunctionComponent = ({ children }) => {
  return (
    <AppConfigurationContext.Provider value={CONFIGURATION}>
      <MuiPickersUtilsProvider utils={DateFnsUtils}>
      {children}
      <ConferenceComponent></ConferenceComponent>  
      </MuiPickersUtilsProvider>
      
    </AppConfigurationContext.Provider>
  )
}
