import { useCallback, useMemo } from 'react'
import { Conference } from '..'
import { useAppConfiguration } from '../../app'
import { useGet, usePost } from '../../shared'

export const useGetConferences = (): [Conference[], boolean, boolean] => {
  const appConfiguration = useAppConfiguration()

  const options = useMemo(
    () => ({
      url: `${appConfiguration.conferencesUrl}/conference`,
      initialState: [],
    }),
    [appConfiguration.conferencesUrl]
  )

  return useGet(options) as [Conference[], boolean, boolean]
}

export const useGetNextConferenceVolume = (): [string, boolean, boolean] => {
  const appConfiguration = useAppConfiguration()

  const options = useMemo(
    () => ({
      url: `${appConfiguration.conferencesUrl}/conference/volume`,
    }),
    [appConfiguration.conferencesUrl]
  )

  return useGet(options) as [string, boolean, boolean]
}

export const usePostConference = <T>(): [void, boolean, (data: T) => void] => {
  const appConfiguration = useAppConfiguration()

  const [data, error, execute] = usePost()

  const post = useCallback(
    (postData) => {
      execute(`${appConfiguration.conferencesUrl}/conference`, postData)
    },
    [appConfiguration.conferencesUrl, execute]
  )

  return useMemo(
    () => [data, error, post] as [void, boolean, () => void],
    [data, error, post]
  )
}
