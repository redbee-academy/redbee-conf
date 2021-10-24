import { FunctionComponent } from "react";
import { Button, Col, Container, Row } from "react-bootstrap";
import UserLoginGoogleButton from "../LoginGoogle/UserLoginGoogleButton";
import './style.css'
import img from './isologo_rbconf.png'
interface ConferenceProps{
    name: string,
    startDate: Date,
    endDate: Date,
    description?: string,
}
export const Conference: FunctionComponent<ConferenceProps> = ({name, startDate, endDate, description}) => (
     <main>
      <Container>
        <Row>
          <Col className="header">
           <img src={img} alt="isologo redbeeConf" className="logo"/>
           <Button variant="outline-light">Ingresar</Button>{''}
          </Col>
        </Row>
        </Container>
        <Container className="content">
          <Row>
            <Col className="left">
              <h1>Sumate a la {name}</h1>
              <div>
                <p>{description}</p>
              
                <Button id="button" variant="light">Inscribirme</Button>
             </div>
             <div>
                <p>¿Querés sumarte a romperla como Speaker en la redbeeConf?</p>
               <UserLoginGoogleButton/>
              </div>
            </Col>
            <Col className="right">
              <div id="fecha">Entre el {startDate.getDate()} y el {endDate.getDate()} de {endDate.getMonth()}</div>
            </Col>
          </Row>
      </Container>
   
    </main>
)