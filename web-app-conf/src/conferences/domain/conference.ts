import moment, { Moment } from 'moment'

export interface Conference {
  id: string
  name: string
  startDate: Moment
  endDate: Moment
  description: string
  status: boolean
  volume?: number
}

export interface UnparsedConference
  extends Omit<Conference, 'startDate' | 'endDate'> {
  startDate: string
  endDate: string
}

export const parseConference = (
  conference: UnparsedConference
): Conference => ({
  ...conference,
  startDate: moment(conference.startDate),
  endDate: moment(conference.endDate),
})
