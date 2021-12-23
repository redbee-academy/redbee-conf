import { FunctionComponent, useEffect, useState } from 'react'
import { Col, Container, FormSelect, Row } from 'react-bootstrap'
import {
  Conference,
  CreateConference,
  UpdateConference,
  useGetConferences,
} from '../../../conferences'

export const ConferenceAdministration: FunctionComponent = () => {
  const [isLoading, setIsLoading] = useState(true)
  const [conferences, setConferences] = useState<Conference[]>([])
  const getConferences = useGetConferences()
  const [selectedConference, setSelectedConference] = useState<Conference>()

  useEffect(() => {
    getConferences()
      .then((data) => {
        setConferences(data)
        setSelectedConference(data[0])
      })
      .finally(() => setIsLoading(false))
  }, [getConferences])

  const handleChange = (event: any) => {
    setSelectedConference(
      conferences.find((it) => it.id === event.target.value)
    )
  }

  return (
    <Container className="mt-3">
      <Row>
        <Col sm={{ offset: 9, span: 3 }}>
          {isLoading ? (
            <div>Buscando conferencias</div>
          ) : (
            <FormSelect onChange={handleChange}>
              {conferences.map((conf) => (
                <option key={conf.id} value={conf.id}>
                  {conf.name} {conf.volume}
                </option>
              ))}
            </FormSelect>
          )}
        </Col>
      </Row>

      <CreateConference />

      {selectedConference ? (
        <UpdateConference conf={selectedConference} />
      ) : null}
    </Container>
  )
}
