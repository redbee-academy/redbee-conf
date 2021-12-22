import axios from 'axios'
import { useState, useEffect } from 'react'

export const usePost = () => {
  const [data, setData] = useState<any>({})
  const [error, setError] = useState<boolean>(false)
  const execute = async (url: string, obj: string) => {
    try {
      setData(await axios.post(url, obj))
    } catch (e) {
      setError(true)
    }
  }
  return [data, error, execute]
}

export const useGet = ({
  url,
  initialState = [],
}: {
  url: string
  initialState?: Array<any>
}) => {
  const [data, setData] = useState<any>(initialState)
  const [isLoading, setLoading] = useState(true)
  const [error, setError] = useState(false)
  useEffect(() => {
    const get = async () => {
      try {
        const res = await axios.get(url)
        setData(res.data)
        setLoading(false)
      } catch (error) {
        setError(true)
      }
    }
    get()
  }, [url])

  return [data, isLoading, error]
}
