/* eslint-disable react/jsx-no-comment-textnodes */
import { FunctionComponent } from "react";
import { Button } from "react-bootstrap";
import { App } from "../App";
import { AppNavBar } from "../NavBar";
import './EndUser.css'


const EndUserApp: FunctionComponent = () => {
  return <App>
  <AppNavBar/>
	<body className="cuerpo" >
  	<header>
        <h1 className="title">redbeeConf-VOL5</h1>
        <div  className="logotipo" >
          <img className="imagen" src="https://media.discordapp.net/attachments/872157975920386071/899729747339247646/unknown.png" alt="Logo Redbee"/>
        </div>
    </header>
    <div className="Mensajes">
        <p> La próxima redbeeConf será el 28 de Marzo de 2022 a las 14:00</p>
        <Button variant="light">Postulá tu charla!</Button>
    </div>
    <footer className="redes-sociales">
      <a href="https://www.facebook.com/redbeestudios" className="btn-red-social"><i className="fab fa-facebook-f">Facebook</i></a>
      <a href="https://www.twitter.com/redbeestudios" className="btn-red-social"><i className="fab fa-twitter">Twitter</i></a>
      <a href="https://www.instagram.com/redbeestudios" className="btn-red-social"><i className="fab fa-instagram">Instagram</i></a>
      <a href="https://www.youtube.com/redbeestudios" className="btn-red-social"><i className="fab fa-instagram">YouTube</i></a>
  </footer>
  </body>
</App>
}

export default EndUserApp