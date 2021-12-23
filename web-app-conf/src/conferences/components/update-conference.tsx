import { FunctionComponent, useCallback, useEffect, useState } from 'react'
import { Button, Container } from 'react-bootstrap'
import { SubmitHandler } from 'react-hook-form'
import { useHistory, useParams } from 'react-router-dom'
import { Spinner } from '../../shared'
import { Conference, parseConference } from '../domain'
import {
  useDeleteConference,
  useGetConferenceById,
  useUpdateConference,
} from '../hooks'
import {
  ConferenceForm,
  ConferenceFormData,
  conferenceFormDataToUnparsedFormData,
} from './conference-form'
import './conference.css'

export const UpdateConference: FunctionComponent = () => {
  const { id } = useParams<{ id: string }>()
  const history = useHistory()

  const [conference, setConference] = useState<Conference | undefined>()
  const [isLoading, setIsLoading] = useState(true)
  const [error, setError] = useState<Error | undefined>()
  const getConferenceById = useGetConferenceById()
  const updateData = useUpdateConference()
  const deleteConference = useDeleteConference()

  const onSubmit: SubmitHandler<ConferenceFormData> = (data) => {
    setError(undefined)
    updateData(parseConference(conferenceFormDataToUnparsedFormData(data)))
      .then(() => history.goBack())
      .catch(setError)
  }

  const onDelete = useCallback(() => {
    setError(undefined)
    deleteConference(id)
      .then(() => history.goBack())
      .catch(setError)
  }, [deleteConference, history, id])

  useEffect(() => {
    getConferenceById(id)
      .then(setConference)
      .catch((error) => {
        console.log(error)
      })
      .finally(() => setIsLoading(false))
  }, [id, getConferenceById])

  if (isLoading) {
    return <Spinner center style={{ marginTop: '20px' }} />
  }

  if (!conference) {
    return null
  }

  return (
    <Container style={{ marginTop: '20px' }}>
      <h2>
        Editar {conference.name} {conference.volume}
      </h2>

      <ConferenceForm
        onSubmit={onSubmit}
        defaultValues={{
          ...conference,
          startDate: conference.startDate.toDate(),
          endDate: conference.endDate.toDate(),
        }}
        error={error?.message}
        extraButtonsAside={
          <Button variant="danger" onClick={onDelete}>
            Borrar
          </Button>
        }
      />
    </Container>
  )

  //TODO: - agregar mensaje de error o de exito
  //      - arreglar checkbox
  //      -? deshabilitar boton guardar si hay errores
}
