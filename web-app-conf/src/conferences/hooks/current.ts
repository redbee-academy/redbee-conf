import { useContext } from 'react'
import { useObservableSubscription } from '../../shared'
import { CurrentConferenceContext } from '../context'
import { Conference } from '../domain'
import { CurrentConferenceService } from '../services/current-conference'

export const useCurrentConferenceService = (): CurrentConferenceService => {
  const context = useContext(CurrentConferenceContext)

  if (!context) {
    throw new Error('No CurrentConferenceContext')
  }

  return context
}

export const useCurrentConference = (): Conference | undefined => {
  const service = useCurrentConferenceService()

  return useObservableSubscription(service.conference)
}
