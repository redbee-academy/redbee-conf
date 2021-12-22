import { useContext } from 'react'
import { AppConfigurationContext } from '../context'

export const useAppConfiguration = () => {
  const context = useContext(AppConfigurationContext)

  if (!context) {
    throw new Error('No AppConfigurationContext')
  }

  return context
}
