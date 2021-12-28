import moment, { Moment } from 'moment'

export interface Talk {
  id: number
  redbee_employee: boolean
  reference: string
  talk_name: string
  talk_topic: string
  talk_description: string
  speaker_name: string
  speaker_email: string
  conference_id: number
  creation_date: Moment
}

export interface UnparsedTalk extends Omit<Talk, 'creation_date'> {
  creation_date: string
}

export const parseTalk = (talk: UnparsedTalk): Talk => ({
  ...talk,
  creation_date: moment(talk.creation_date),
})
