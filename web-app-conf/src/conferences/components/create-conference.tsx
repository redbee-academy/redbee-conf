import { FunctionComponent } from 'react'
import { useForm, SubmitHandler } from 'react-hook-form'
import { Button, Col, Form, Row } from 'react-bootstrap'
import { Conference } from '../domain'
import { useGetNextConferenceVolume, usePostConference } from '../hooks'

export const CreateConference: FunctionComponent = () => {
  const { register, handleSubmit } = useForm<Conference>()

  const [nextConferenceVolume, isLoading] = useGetNextConferenceVolume()

  const [postData, postErrors, execute] = usePostConference()

  const onSubmit: SubmitHandler<Conference> = (data) => {
    console.log(JSON.stringify(data))
    execute(data)
  }

  if (isLoading) {
    return <div>cargando...</div>
  } else {
    return (
      <Row>
        <Col className="conferenceMainForm">
          <Form onSubmit={handleSubmit(onSubmit)}>
            <Form.Group className="mb-3" controlId="formBasicName">
              <Form.Label>Nombre de la proxima CONF</Form.Label>
              <Form.Control
                type="name"
                defaultValue={`redbee conf vol. ${nextConferenceVolume}`}
                placeholder="Ingrese un nombre"
              />{' '}
            </Form.Group>
            <div className="d-flex justify-content-between">
              <Form.Group className="mb-3" controlId="formBasicStartDate">
                <Form.Label>Fecha de inicio</Form.Label>
                <Form.Control
                  type="date"
                  {...register('startDate')}
                  name="startDate"
                />{' '}
              </Form.Group>
              <Form.Group className="mb-3" controlId="formBasicEndDate">
                <Form.Label>Fecha de finalización</Form.Label>
                <Form.Control
                  type="date"
                  {...register('endDate')}
                  name="endDate"
                />{' '}
              </Form.Group>
            </div>
            <Form.Group className="mb-3" controlId="formBasicDescription">
              <Form.Label>Descripcion</Form.Label>
              <Form.Control
                {...register('description')}
                name="description"
                as="textarea"
                rows={3}
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicCheckbox">
              <Form.Check
                type="checkbox"
                label="Visible"
                {...register('status')}
              />
            </Form.Group>
            <div className="d-flex justify-content-between pt-4">
              <div />
              <Button variant="primary" type="submit">
                Submit
              </Button>
            </div>
          </Form>
        </Col>
      </Row>
    )
  }
}
