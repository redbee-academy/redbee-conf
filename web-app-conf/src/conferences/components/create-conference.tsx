import { FunctionComponent, useEffect, useState } from 'react'
import { SubmitHandler } from 'react-hook-form'
import { Container } from 'react-bootstrap'
import { useGetNextConferenceVolume, useCreateConference } from '../hooks'
import { API_DATE_FORMAT, Spinner } from '../../shared'
import { ConferenceForm, ConferenceFormData } from './conference-form'
import moment from 'moment'

export const CreateConference: FunctionComponent = () => {
  const [nextConferenceVolume, setNextConferenceVolume] = useState<string>('')
  const [isLoading, setIsLoading] = useState(true)
  const [error, setError] = useState<Error | undefined>()

  const getNextConferenceVolume = useGetNextConferenceVolume()

  const createConference = useCreateConference()

  const onSubmit: SubmitHandler<ConferenceFormData> = (data) => {
    setError(undefined)
    createConference({
      ...data,
      startDate: moment(data.startDate).format(API_DATE_FORMAT),
      endDate: moment(data.endDate).format(API_DATE_FORMAT),
    }).catch(setError)
  }

  useEffect(() => {
    getNextConferenceVolume()
      .then(setNextConferenceVolume)
      .finally(() => setIsLoading(false))
  }, [getNextConferenceVolume])

  if (isLoading) {
    return <Spinner center style={{ marginTop: '20%' }} />
  }

  return (
    <Container style={{ marginTop: '20px' }}>
      <ConferenceForm
        onSubmit={onSubmit}
        volume={nextConferenceVolume}
        error={error?.message}
      />
    </Container>
  )
}
