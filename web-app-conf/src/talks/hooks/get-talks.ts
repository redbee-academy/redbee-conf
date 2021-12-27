import axios from 'axios'
import { useCallback } from 'react'
import { useAppConfiguration } from '../../app'
import { parseTalk, Talk, UnparsedTalk } from '../domain'

export const useGetConferenceTalks = (
  conferenceId: number,
  approved?: boolean
): (() => Promise<Talk[]>) => {
  const { talksUrl } = useAppConfiguration()

  return useCallback(() => {
    let url = `${talksUrl}/conference/${conferenceId}/talks`

    if (approved != null) {
      url += '?approved=' + (approved ? '1' : '0')
    }

    return axios.get<UnparsedTalk[]>(url).then((res) => res.data.map(parseTalk))
  }, [approved, conferenceId, talksUrl])
}
