import { FunctionComponent, useEffect, useRef } from 'react'
import { useAppConfiguration } from '../../app'
import { CurrentConferenceContext } from '../context'
import {
  CurrentConferenceService,
  CurrentConferenceServiceOptions,
} from '../services'

export const ConferencesModule: FunctionComponent<
  CurrentConferenceServiceOptions
> = ({ children, ...options }) => {
  const appConfiguration = useAppConfiguration()
  const { current: currentConferenceService } = useRef(
    new CurrentConferenceService(appConfiguration, options)
  )

  useEffect(() => {
    currentConferenceService.init()
  }, [currentConferenceService])

  return (
    <CurrentConferenceContext.Provider value={currentConferenceService}>
      {children}
    </CurrentConferenceContext.Provider>
  )
}
