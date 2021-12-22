import { useCallback } from 'react'
import { useAppConfiguration } from './configuration'

export const useFetch = <Result, Body>(
  url: string,
  method = 'GET'
): ((body?: Body) => Promise<Result>) => {
  const appConfiguration = useAppConfiguration()

  return useCallback(
    async (body) => {
      const headers: Record<string, string> = {}

      if (body) {
        headers['Content-Type'] = 'application/json'
      }

      const respone = await fetch(`${appConfiguration.conferencesUrl}/${url}`, {
        method,
        body: body ? JSON.stringify(body) : undefined,
        headers,
      })
      const responseBody = await respone.json()

      return responseBody as Promise<Result>
    },
    [appConfiguration.conferencesUrl, method, url]
  )
}
