import axios from 'axios'
import { useCallback } from 'react'
import { Conference } from '..'
import { useAppConfiguration } from '../../app'

export const useGetConferences = (): (() => Promise<Conference[]>) => {
  const appConfiguration = useAppConfiguration()

  return useCallback(
    () =>
      axios
        .get<Conference[]>(`${appConfiguration.conferencesUrl}/conference`)
        .then((res) => res.data),
    [appConfiguration.conferencesUrl]
  )
}

export const useGetNextConferenceVolume = (): (() => Promise<string>) => {
  const appConfiguration = useAppConfiguration()

  return useCallback(
    () =>
      axios
        .get<string>(`${appConfiguration.conferencesUrl}/conference/volume`)
        .then((res) => res.data),
    [appConfiguration.conferencesUrl]
  )
}

export const useCreateConference = (): ((
  data: Conference
) => Promise<Conference>) => {
  const appConfiguration = useAppConfiguration()

  return useCallback(
    (data: Conference) =>
      axios
        .post<Conference>(`${appConfiguration.conferencesUrl}/conference`, data)
        .then((res) => res.data),
    [appConfiguration.conferencesUrl]
  )
}
