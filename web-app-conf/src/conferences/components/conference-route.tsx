import { FunctionComponent, useEffect, useState } from 'react'
import { Container } from 'react-bootstrap'
import { useParams } from 'react-router-dom'
import { ConferencePage } from '.'
import { Spinner } from '../../shared'
import { Conference } from '../domain'
import { useGetConferenceById } from '../hooks'

export const ConferenceRoute: FunctionComponent = () => {
  const { id } = useParams<{ id: string }>()
  const [conference, setConference] = useState<Conference | undefined>(
    undefined
  )
  const [loading, setLoading] = useState(true)
  const getConferenceById = useGetConferenceById()

  useEffect(() => {
    getConferenceById(id)
      .then(setConference)
      .finally(() => setLoading(false))
  }, [getConferenceById, id])

  if (loading) {
    return <Spinner center style={{ marginTop: '20%' }} />
  }

  if (!conference) {
    return null
  }

  return (
    <Container style={{ marginTop: '20px' }}>
      <ConferencePage conference={conference} />
    </Container>
  )
}
