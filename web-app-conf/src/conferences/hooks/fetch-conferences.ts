import { useCallback } from 'react'
import { useAppConfiguration } from '../../app'
import { Conference } from '../domain'
import axios from 'axios'

export const useFetchConferences = (): (() => Promise<Conference[]>) => {
  const appConfiguration = useAppConfiguration()

  return useCallback(
    () =>
      axios
        .get<Conference[]>(
          `${appConfiguration.conferencesUrl}/conference?visible=true`
        )
        .then((response) =>
          response.data.map((conf) => ({
            ...conf,
            ...{
              startDate: new Date(conf.startDate),
              endDate: new Date(conf.endDate),
            },
          }))
        ),
    [appConfiguration.conferencesUrl]
  )
}

export const useFetchConfereceById = (): ((
  id: string
) => Promise<Conference>) => {
  const appConfiguration = useAppConfiguration()

  return useCallback(
    (id: string) =>
      axios.get(`${appConfiguration.conferencesUrl}/conference/${id}`),
    [appConfiguration.conferencesUrl]
  )
}

export const useUpdateConference = (): ((
  conference: Conference
) => Promise<Conference>) => {
  const appConfiguration = useAppConfiguration()

  return useCallback(
    (conference: Conference) =>
      axios
        .put<Conference>(
          `${appConfiguration.conferencesUrl}/conference/${conference.id}`
        )
        .then((r) => r.data),
    [appConfiguration.conferencesUrl]
  )
}
