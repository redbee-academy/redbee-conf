import { FunctionComponent, useEffect, useState } from "react";
import { Button, Col, Container, Row } from "react-bootstrap";
import UserLoginGoogleButton from "../LoginGoogle/UserLoginGoogleButton";
import './style.css'
import img from './isologo_rbconf.png'
import * as conferenceService from "../../services/conferenceService";
import { Conference } from "../../domain/conference";



export const ConferenceComponent: FunctionComponent = () => {
  const [data, setData] = useState<Conference>();

  useEffect(() => {
    conferenceService
      .getConferenceByVisibility()
      .then(response => {
        setData(response)
      })
  },[])
  if(data){
  return (
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
              <h1>Sumate a la {data?.name}</h1>
              <div>
                <p>{data?.description}</p>
              
                <Button id="button" variant="light">Inscribirme</Button>
             </div>
             <div>
                <p>¿Querés sumarte a romperla como Speaker en la redbeeConf?</p>
               <UserLoginGoogleButton/>
              </div>
            </Col>
            <Col className="right">
              <div id="fecha">Entre el {data?.startDate.getDate()} y el {data?.endDate.getDate()} de <span className="capitalLetter">{data?.endDate.toLocaleString("es-ar", {month:'long'})}</span></div>
            </Col> 
          </Row>
      </Container>
   </main>)}
   else{
     return (<main>
     <Container>
       <Row>
         <Col className="header">
          <img src={img} alt="isologo redbeeConf" className="logo"/>
          <Button variant="outline-light">Ingresar</Button>{''}
         </Col>
       </Row>
       </Container>
       <Container className="content">
           <Col className="left" id="noData">
             <h1>Estamos preparando la próxima redbeeConf!</h1>
          </Col>
          </Container>
        </main>)
   }
}