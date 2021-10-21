import { FunctionComponent } from "react";
import { App } from "../App";
import './style.css'
import img from './isologo_rbconf.png'
import { Button } from "react-bootstrap";

const EndUserApp: FunctionComponent = () => {
  return <App>
    <main className="frontPage">
      
      <div className="header">
        <img src={img} alt="isologo redbeeConf" className="logo"/>
        <Button variant="outline-light" className="LogButton">Ingresar</Button>
      </div>

      <div className="content">
        <div id='left'>
          <h1>Sumate a la redbee conference</h1>
          <p id='description'>Sé parte de una experiencia única dentro del mundo de TI, con más de +50 Speakers, +30 workshops en diferentes áreas, +100 charlas en vivo.</p>
          <div className="buttons">
            <Button variant="light">Inscribirme</Button>
            <Button variant="light">Postulá tu charla aquí</Button> 
          </div>
      </div>
        
      </div>
   
    </main>
    </App>
}

export default EndUserApp