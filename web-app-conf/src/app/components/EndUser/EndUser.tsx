import { FunctionComponent } from "react";
import { App } from "../App";
import './style.css'
import img from './isologo_rbconf.png'
import { Button, Col, Container, Row } from "react-bootstrap";
import UserLoginGoogleButton from "../LoginGoogle/UserLoginGoogleButton";

const EndUserApp: FunctionComponent = () => {
  return <App>
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
              <h1>Sumate a la redbee conference</h1>
              <p>Sé parte de una experiencia única dentro del mundo de TI, con más de +50 Speakers, +30 workshops en diferentes áreas, +100 charlas en vivo.</p>
              <div>
                <Button id="button" variant="light">Inscribirme</Button>
              <p>¿Querés sumarte a romperla como Speaker en la redbeeConf?</p>
               <div><UserLoginGoogleButton/></div>
              </div>
            </Col>
            <Col className="right">
              <div id="fecha">9 y 10 de Noviembre</div>
            </Col>
          </Row>
      </Container>
   
    </main>
    </App>
}

export default EndUserApp;