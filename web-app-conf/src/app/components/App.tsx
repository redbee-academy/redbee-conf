import { FunctionComponent } from 'react';
import { AppConfiguration } from '../domain';
import { AppConfigurationContext } from '../context';

const CONFIGURATION: AppConfiguration = {
  backendUrl: process.env.REACT_APP_BACKEND_URL as string
}

export const App: FunctionComponent = ({ children }) => {
  return (
    <AppConfigurationContext.Provider value={CONFIGURATION}>
      {children}
    </AppConfigurationContext.Provider>
  )
}
