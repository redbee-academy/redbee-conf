import { FunctionComponent } from 'react'
import { Table } from 'react-bootstrap'
import { Talk } from '../domain'

export interface TalkTableProps {
  talks: Talk[]
}

export const TalkTable: FunctionComponent<TalkTableProps> = ({ talks }) => {
  return (
    <Table striped>
      <thead>
        <tr>
          <th>Nombre</th>
          <th>Tópico</th>
          <th>Descripción</th>
          <th>Speaker</th>
          <th>Email</th>
          <th>Referencia</th>
        </tr>
      </thead>
      <tbody>
        {talks.map((talk) => (
          <tr key={talk.id}>
            <td>{talk.talk_name}</td>
            <td>{talk.talk_topic}</td>
            <td>{talk.talk_description}</td>
            <td>{talk.speaker_name}</td>
            <td>{talk.speaker_email}</td>
            <td>{talk.reference}</td>
          </tr>
        ))}
      </tbody>
    </Table>
  )
}
