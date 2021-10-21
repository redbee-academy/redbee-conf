import { FunctionComponent } from "react";
import { App } from "../App";
import './style.css'
import img from './isologo_rbconf.png'
import { Button, Col, Container, Row } from "react-bootstrap";
import UserLoginGoogleButton from "../LoginGoogle/UserLoginGoogleButton";

const EndUserApp: FunctionComponent = () => {
  return <App>
    <main className="frontPage">
      <Container>
        <Row>
          <Col className="header">
           <img src={img} alt="isologo redbeeConf" className="logo"/>
           <Button variant="outline-light" className="LogButton">Ingresar</Button>
          </Col>
        </Row>
        </Container>
        <Container className="content">
          <Row>
            <Col id="left">
              <h1>Sumate a la redbee conference</h1>
              <p>Sé parte de una experiencia única dentro del mundo de TI, con más de +50 Speakers, +30 workshops en diferentes áreas, +100 charlas en vivo.</p>
              <div className="buttons">
                <Button variant="light">Inscribirme</Button>
              </div>
              <p>¿Querés sumarte a romperla como Speaker en la redbeeConf?</p>
              <div className="buttons">
              <Button variant="light">Postulá tu charla aquí</Button> 
              </div>
            </Col>
            <Col id="right">2 of 2</Col>
          </Row>
          <div><UserLoginGoogleButton/></div>
      </Container>
   
    </main>
    </App>
}

export default EndUserApp;