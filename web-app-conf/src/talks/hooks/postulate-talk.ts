import axios, { AxiosResponse } from 'axios'
import { useCallback } from 'react'
import { useAppConfiguration } from '../../app'

export const usePostulateTalk = (): ((
  form: any
) => Promise<AxiosResponse<any, any>>) => {
  const appConfiguration = useAppConfiguration()

  return useCallback(
    (form: any) => axios.post(`${appConfiguration.talksUrl}/talks`, form),
    [appConfiguration.talksUrl]
  )
}
