import { FunctionComponent, useEffect, useState } from 'react'
import { App } from '../App'
import { ConferenceComponent } from './Conferences/components/Conference/Conference'
import UpdateConf from './Conferences/UpdateConf'
import { Col, Container, FormSelect, Row } from 'react-bootstrap'
import { useGet } from '../../hooks/useHTTP'
import { Conference } from './Conferences/Conference'

const StaffApp: FunctionComponent = () => {
  const [data, isLoading, error] = useGet({
    url: '/conference',
    initialState: [],
  })
  const [conf, setConf] = useState<Conference>()

  useEffect(() => {
    setConf(data[0])
  }, [data])

  const handleChange = (event: any) => {
    setConf(data.find((it: Conference) => it.id == event.target.value))
  }

  return (
    <App>
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

        <ConferenceComponent />

        {conf ? <UpdateConf conf={conf} /> : null}
      </Container>
    </App>
  )
}

export default StaffApp
