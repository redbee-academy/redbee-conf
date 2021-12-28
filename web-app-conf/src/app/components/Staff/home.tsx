import { FunctionComponent } from 'react'
import { Container } from 'react-bootstrap'
import { Link } from 'react-router-dom'
import { ConferencePage, useCurrentConference } from '../../../conferences'
import { STAFF_CONFERENCES_PATH } from './const'

export const StaffHome: FunctionComponent = () => {
  const currentConference = useCurrentConference()

  return (
    <Container style={{ padding: '20px 0' }}>
      {currentConference ? (
        <ConferencePage conference={currentConference} />
      ) : (
        <div style={{ textAlign: 'center', paddingTop: '50px' }}>
          No hay ninguna conferencia programada,{' '}
          <Link to={`${STAFF_CONFERENCES_PATH}/new`}>agrega una</Link>
        </div>
      )}
    </Container>
  )
}
