import moment from 'moment'
import { FunctionComponent, useEffect, useMemo, useState } from 'react'
import { Button, Card } from 'react-bootstrap'
import { BsFillEyeFill, BsFillEyeSlashFill } from 'react-icons/bs'
import { Link } from 'react-router-dom'
import { useAppConfiguration } from '../../app'
import {
  DATE_FORMAT,
  Spinner,
  SUCCESS_COLOR,
  WARNING_COLOR,
} from '../../shared'
import { Talk, TalkTable, useGetConferenceTalks } from '../../talks'
import { Conference } from '../domain'
import { CountDown } from './count-down/count-down'

export interface ConferencePageProps {
  conference: Conference
}

export const ConferencePage: FunctionComponent<ConferencePageProps> = ({
  conference,
}) => {
  const { staffConferencesBasePath } = useAppConfiguration()
  const getTalksPendingApproval = useGetConferenceTalks(conference.id, false)
  const getApprovedTalks = useGetConferenceTalks(conference.id, true)

  const [talksPendingApproval, setTalksPendingApproval] = useState<
    Talk[] | undefined
  >()
  const [approvedTalks, setApprovedTalks] = useState<Talk[] | undefined>()

  const [talksPendingApprovalLoading, setTalksPendiongApprovalLoading] =
    useState(true)
  const [approvedTalksLoading, setApprovedTalksLoading] = useState(true)

  const started = useMemo(() => {
    return Boolean(conference && conference.startDate.isSameOrBefore(moment()))
  }, [conference])

  useEffect(() => {
    getTalksPendingApproval()
      .then(setTalksPendingApproval)
      .finally(() => setTalksPendiongApprovalLoading(false))

    getApprovedTalks()
      .then(setApprovedTalks)
      .finally(() => setApprovedTalksLoading(false))
  }, [getApprovedTalks, getTalksPendingApproval])

  return (
    <>
      <Card style={{ padding: '20px', boxShadow: '0 0 10px 2px #DDD' }}>
        <Card.Title>
          <span>
            {conference.name} {conference.volume}
          </span>
          <span style={{ marginLeft: '20px' }}>
            {conference.status ? (
              <BsFillEyeFill color={SUCCESS_COLOR} title="Visible" />
            ) : (
              <BsFillEyeSlashFill color={WARNING_COLOR} title="No visible" />
            )}
          </span>
        </Card.Title>
        <Card.Subtitle>
          <span>
            {conference.startDate.format(DATE_FORMAT)} -{' '}
            {conference.endDate.format(DATE_FORMAT)}
          </span>
        </Card.Subtitle>
        <Card.Body>
          <p>{conference.description}</p>
        </Card.Body>
        <div
          style={{
            display: 'flex',
            justifyContent: 'space-between',
            alignItems: 'flex-end',
          }}
        >
          <Link to={`${staffConferencesBasePath}/${conference.id}/edit`}>
            <Button variant="primary">Editar</Button>
          </Link>
          {!started ? (
            <CountDown
              style={{ alignSelf: 'flex-end' }}
              date={conference.startDate}
            />
          ) : undefined}
        </div>
      </Card>
      {talksPendingApprovalLoading ? (
        <Spinner center />
      ) : talksPendingApproval?.length ? (
        <>
          <h3>Pendientes de aprobación</h3>
          <TalkTable talks={talksPendingApproval} />
        </>
      ) : undefined}
      {approvedTalksLoading ? (
        <Spinner center />
      ) : approvedTalks?.length ? (
        <>
          <h3>Aprobadas</h3>
          <TalkTable talks={approvedTalks} />
        </>
      ) : undefined}
    </>
  )
}
