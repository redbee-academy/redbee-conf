import React, { useCallback, useMemo, useState } from 'react'
import { Button, Form, Alert } from 'react-bootstrap'
import { useForm } from 'react-hook-form'
import { useHistory } from 'react-router-dom'

import { ReactComponent as Logo } from '../../assets/images/logo-redbee-conf.svg'
import { GoogleUserInfo } from '../../auth'
import { PostulateTalkRequestUserData, usePostulateTalk } from '../hooks'

const redbeeDomains = ['redb.ee', 'redbee.io']

interface Result {
  isSuccess: boolean
  message: string
}

export const PostulateTalk = () => {
  const history = useHistory<GoogleUserInfo>()
  const userData = history.location.state
  const isABee = useMemo(
    () => redbeeDomains.some((domain) => userData.email.endsWith(domain)),
    [userData.email]
  )
  const { register, handleSubmit } = useForm<PostulateTalkRequestUserData>({
    defaultValues: {
      redbee_employee: isABee,
      speaker_email: userData.email,
      speaker_name: userData.name,
    },
  })

  const [result, setResult] = useState<Result>()
  const postulateTalk = usePostulateTalk()

  const onSubmit = useCallback(
    (request: PostulateTalkRequestUserData) => {
      postulateTalk(request)
        .then((_) =>
          setResult({
            isSuccess: true,
            message: 'Tu charla se postuló con exito!',
          })
        )
        .catch((_) =>
          setResult({
            isSuccess: false,
            message:
              'Ocurrió un error postulando tu charla. Por favor, intentá nuevamente.',
          })
        )
    },
    [postulateTalk]
  )

  return (
    <div>
      <div style={{ padding: '40px 0px 20px 40px' }}>
        <div style={{ paddingBottom: '20px' }}>
          <Logo />
        </div>
        <p style={{ color: '#515357', fontSize: '56px', lineHeight: '56px' }}>
          Sumate a la <br /> redbee Conference
        </p>
      </div>
      <div
        style={{
          backgroundColor: '#F9F9F9',
          padding: '20px 40px 20px',
          height: '100%',
        }}
      >
        <Form onSubmit={handleSubmit(onSubmit)}>
          <Form.Group className="mb-3" controlId="formBasicName">
            <Form.Label>Nombre y apellido</Form.Label>
            <Form.Control
              type="name"
              placeholder="Ingrese un nombre"
              readOnly
              {...register('speaker_name')}
            />
          </Form.Group>

          <Form.Group className="mb-3" controlId="formBasicName">
            <Form.Label>Email</Form.Label>
            <Form.Control
              type="name"
              placeholder="Ingrese un mail"
              readOnly
              {...register('speaker_email')}
            />
          </Form.Group>
          {!isABee && (
            <Form.Group className="mb-3" controlId="formBasicName">
              <Form.Label>Linkedin url</Form.Label>
              <Form.Control
                type="text"
                placeholder="Ingrese una referencia"
                {...register('reference')}
              />
            </Form.Group>
          )}

          <Form.Group className="mb-3" controlId="formBasicName">
            <Form.Label>Nombre de la charla</Form.Label>
            <Form.Control
              type="name"
              placeholder="Ingrese un nombre"
              {...register('talk_name')}
            />
          </Form.Group>

          <Form.Group className="mb-3" controlId="formBasicName">
            <Form.Label>Topico</Form.Label>
            <Form.Control
              type="name"
              placeholder="Ingrese un topico"
              {...register('talk_topic')}
            />
          </Form.Group>

          <Form.Group className="mb-3" controlId="formBasicDescription">
            <Form.Label>Descripcion</Form.Label>
            <Form.Control
              as="textarea"
              rows={3}
              {...register('talk_description')}
            />
          </Form.Group>

          <Button variant="primary" type="submit">
            Submit
          </Button>
        </Form>
        {result && (
          <Alert
            className="mt-3"
            variant={result.isSuccess ? 'success' : 'danger'}
          >
            {result.message} <Alert.Link href="/">Volver al home.</Alert.Link>
          </Alert>
        )}
      </div>
    </div>
  )
}
