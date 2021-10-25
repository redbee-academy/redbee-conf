import { FunctionComponent } from "react"
import { Button, Col, Container, Row } from "react-bootstrap";
import './style.css'
interface TalkProps {
    speaker_name: string,
    email: string,
    redbee_employee: boolean,
    reference: string,
    talk_name: string,
    topic: string,
    description: string,
}
export const TalkForm: FunctionComponent<TalkProps> = ({speaker_name, email, redbee_employee, reference, talk_name, topic, description}) => (
    <main>
        <Container>
            <h1>Acá va el formulario de postular charla</h1>

        </Container>
    </main>
) 