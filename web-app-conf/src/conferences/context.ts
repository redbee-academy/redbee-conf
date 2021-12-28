import { createContext } from 'react'
import { CurrentConferenceService } from './services/current-conference'

export const CurrentConferenceContext = createContext<
  CurrentConferenceService | undefined
>(undefined)
