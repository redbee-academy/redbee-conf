import { FunctionComponent } from 'react'
import { Button, Container } from 'react-bootstrap'
import { Link } from 'react-router-dom'
import { ConferencesList } from './conferences-list'

export const ConferencesPage: FunctionComponent = () => {
  return (
    <Container style={{ marginTop: '20px' }}>
      <div style={{ display: 'flex', justifyContent: 'flex-end' }}>
        <Link to="conferences/new">
          <Button>Crear nueva edición</Button>
        </Link>
      </div>
      <ConferencesList />
    </Container>
  )
}
