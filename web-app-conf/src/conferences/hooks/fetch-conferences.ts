import { useCallback } from 'react'
import { useAppConfiguration, useHttpErrorHandler } from '../../app'
import { Conference, parseConference, UnparsedConference } from '../domain'
import axios from 'axios'

export const useGetConferenceById = (): ((
  id: string
) => Promise<Conference>) => {
  const appConfiguration = useAppConfiguration()

  return useCallback(
    (id: string) =>
      axios
        .get<UnparsedConference>(
          `${appConfiguration.conferencesUrl}/conference/${id}`
        )
        .then((res) => parseConference(res.data)),
    [appConfiguration.conferencesUrl]
  )
}

export const useGetConferences = (): (() => Promise<Conference[]>) => {
  const appConfiguration = useAppConfiguration()

  return useCallback(
    () =>
      axios
        .get<UnparsedConference[]>(
          `${appConfiguration.conferencesUrl}/conference`
        )
        .then((res) => res.data.map(parseConference)),
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
  data: UnparsedConference
) => Promise<Conference>) => {
  const appConfiguration = useAppConfiguration()
  const errorHandler = useHttpErrorHandler()

  return useCallback(
    (data: UnparsedConference) =>
      axios
        .post<UnparsedConference>(
          `${appConfiguration.conferencesUrl}/conference`,
          data
        )
        .catch(errorHandler)
        .then((res) => parseConference(res.data)),
    [appConfiguration.conferencesUrl, errorHandler]
  )
}

export const useUpdateConference = (): ((
  conference: Conference
) => Promise<Conference>) => {
  const appConfiguration = useAppConfiguration()
  const errorHandler = useHttpErrorHandler()

  return useCallback(
    (conference: Conference) =>
      axios
        .put<UnparsedConference>(
          `${appConfiguration.conferencesUrl}/conference/${conference.id}`,
          conference
        )
        .catch(errorHandler)
        .then((r) => parseConference(r.data)),
    [appConfiguration.conferencesUrl, errorHandler]
  )
}

export const useDeleteConference = (): ((id: string) => Promise<void>) => {
  const appConfiguration = useAppConfiguration()
  const errorHandler = useHttpErrorHandler()

  return useCallback(
    (id: string) =>
      axios
        .delete(`${appConfiguration.conferencesUrl}/conference/${id}`)
        .catch(errorHandler)
        .then(() => undefined),
    [appConfiguration.conferencesUrl, errorHandler]
  )
}
