import { FunctionComponent, useEffect, useState } from 'react'
import { Container } from 'react-bootstrap'
import { Link } from 'react-router-dom'
import {
  Conference,
  ConferencePage,
  useGetCurrentConference,
} from '../../../conferences'
import { Spinner } from '../../../shared'

export const StaffHome: FunctionComponent = () => {
  const [loading, setLoading] = useState(true)
  const [currentConference, setCurrentConference] = useState<
    Conference | undefined
  >(undefined)
  const getCurrentConference = useGetCurrentConference()

  useEffect(() => {
    getCurrentConference()
      .then(setCurrentConference)
      .finally(() => setLoading(false))
  }, [getCurrentConference])

  return (
    <Container style={{ padding: '20px 0' }}>
      {loading ? (
        <Spinner center style={{ marginTop: '20%' }} />
      ) : currentConference ? (
        <ConferencePage conference={currentConference} />
      ) : (
        <div style={{ textAlign: 'center', paddingTop: '50px' }}>
          No hay ninguna conferencia programada,{' '}
          <Link to="/conferences/new">agrega una</Link>
        </div>
      )}
    </Container>
  )
}
