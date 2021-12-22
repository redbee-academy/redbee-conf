import { FunctionComponent, useEffect, useState } from 'react'
import { Col, Container, FormSelect, Row } from 'react-bootstrap'
import {
  Conference,
  CreateConference,
  UpdateConference,
  useGetConferences,
} from '../../../conferences'

export const ConferenceAdministration: FunctionComponent = () => {
  const [data, isLoading] = useGetConferences()
  const [conf, setConf] = useState<Conference>()

  useEffect(() => {
    setConf(data[0])
  }, [data])

  const handleChange = (event: any) => {
    setConf(data.find((it: Conference) => it.id == event.target.value))
  }

  return (
    <Container className="mt-3">
      <Row>
        <Col sm={{ offset: 9, span: 3 }}>
          {isLoading ? (
            <div>Buscando conferencias</div>
          ) : (
            <FormSelect onChange={handleChange}>
              {data.map((conf: Conference) => (
                <option key={conf.id} value={conf.id}>
                  {conf.name} {conf.volume}
                </option>
              ))}
            </FormSelect>
          )}
        </Col>
      </Row>

      <CreateConference />

      {conf ? <UpdateConference conf={conf} /> : null}
    </Container>
  )
}
