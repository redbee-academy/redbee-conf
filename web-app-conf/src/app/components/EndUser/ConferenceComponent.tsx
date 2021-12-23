import { FunctionComponent, useEffect, useState } from 'react'
import { Button, Col, Container, Row } from 'react-bootstrap'
import {
  Conference,
  useGetVisibileConferences,
  CountDown,
} from '../../../conferences'
import { UserLoginGoogleButton } from '../../../auth'
import img from './isologo_rbconf.png'

import './style.css'

export const ConferenceComponent: FunctionComponent = () => {
  const [data, setData] = useState<Conference>()
  const getVisibleConferences = useGetVisibileConferences()

  useEffect(() => {
    getVisibleConferences().then((response) => {
      if (response.length) setData(response[0])
    })
  }, [getVisibleConferences])

  let logo = (
    <Row className="pt-5 mb-5">
      <Col xs="6">
        <a href="/">
          <img src={img} alt="isologo redbeeConf" className="logo" />
        </a>
      </Col>
      <Col xs={6} className="text-end">
        <Button variant="outline-light">Ingresar</Button>
        {''}
      </Col>
    </Row>
  )

  return (
    <main className="pb-5 pt-5">
      <Container>
        {logo}
        {data ? (
          <div>
            <Row className="mb-3">
              <Col xs="12">
                <h1>
                  Sumate a la
                  <br />
                  {data.name} {data.volume}
                </h1>
              </Col>
            </Row>
            <Row className="mb-3">
              <Col sm={7} lg={6}>
                <h3 className="fs-5">{data.description}</h3>
              </Col>
              <Col sm={5} lg={6} className="text-end mt-3 mt-sm-0">
                <h2 className="me-4">
                  Del {data.startDate.get('date')}
                  <br className="d-none d-sm-inline" /> al{' '}
                  {data.endDate.get('date')} de{' '}
                  <span className="text-capitalize">
                    {data.endDate
                      .toDate()
                      .toLocaleString('es-ar', { month: 'long' })}
                  </span>
                </h2>
                <CountDown className="ms-auto" date={data.startDate} />
              </Col>
            </Row>
            <Row className="mt-4 mb-5">
              <Col>
                <Button variant="light" className="btn-lg me-3 mb-3 mb-sm-0">
                  📻 Quiero participar
                </Button>
                <UserLoginGoogleButton>
                  🎙️ Quiero dar una charla
                </UserLoginGoogleButton>
              </Col>
            </Row>
          </div>
        ) : (
          <Row className="mt-5 pt-5">
            <Col xs="12">
              <h1 className="mt-5">
                Estamos preparando la próxima redbeeConf!
              </h1>
            </Col>
          </Row>
        )}
      </Container>
    </main>
  )
}
