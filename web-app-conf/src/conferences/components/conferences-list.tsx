import { FunctionComponent, useEffect, useState } from 'react'
import { Table } from 'react-bootstrap'
import { useHistory } from 'react-router-dom'
import { DATE_FORMAT, Spinner } from '../../shared'
import { Conference } from '../domain'
import { useGetConferences } from '../hooks'

export const ConferencesList: FunctionComponent = () => {
  const getConferences = useGetConferences()
  const [loading, setLoading] = useState(true)
  const [conferences, setConferences] = useState<Conference[] | undefined>()
  const history = useHistory()

  useEffect(() => {
    getConferences()
      .then(setConferences)
      .finally(() => setLoading(false))
  }, [getConferences])

  if (loading) {
    return <Spinner center style={{ marginTop: '20%' }} />
  }

  if (!conferences) {
    return null
  }

  if (!conferences.length) {
    return (
      <div style={{ textAlign: 'center', marginTop: '40px' }}>
        No hay ediciones registradas
      </div>
    )
  }

  return (
    <Table striped hover style={{ marginTop: '40px' }}>
      <thead>
        <tr>
          <th>Nombre</th>
          <th>Fecha inicio</th>
          <th>Fecha fin</th>
          <th>Visible</th>
        </tr>
      </thead>
      <tbody>
        {conferences.map((conference) => (
          <tr
            key={conference.id}
            onClick={() => history.push(`conferences/${conference.id}`)}
          >
            <td>{conference.name}</td>
            <td>{conference.startDate.format(DATE_FORMAT)}</td>
            <td>{conference.endDate.format(DATE_FORMAT)}</td>
            <td>{conference.status ? 'Sí' : 'No'}</td>
          </tr>
        ))}
      </tbody>
    </Table>
  )
}
