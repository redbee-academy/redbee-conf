import { AxiosError } from 'axios'
import { useCallback } from 'react'

export const useHttpErrorHandler = () =>
  useCallback((error: AxiosError): never => {
    console.log(JSON.stringify(error))
    throw new Error(error.response?.data?.message ?? error.message)
  }, [])
