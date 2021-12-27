import axios from 'axios'
import { useCallback } from 'react'
import { useAppConfiguration } from '../../app'
import { useCurrentConference } from '../../conferences'
import { PostulateTalkRequest } from '../domain'

export type PostulateTalkRequestUserData = Omit<
  PostulateTalkRequest,
  'conference_id'
>

export const usePostulateTalk = (): ((
  userData: PostulateTalkRequestUserData
) => Promise<void>) => {
  const appConfiguration = useAppConfiguration()
  const currentConference = useCurrentConference()

  return useCallback(
    async (userData) => {
      if (!currentConference) {
        throw new Error(
          'Cannot postulate talk if there is no current conference'
        )
      }

      const request: PostulateTalkRequest = {
        ...userData,
        conference_id: currentConference.id,
      }

      await axios.post(`${appConfiguration.talksUrl}/talks`, request)
    },
    [appConfiguration.talksUrl, currentConference]
  )
}
