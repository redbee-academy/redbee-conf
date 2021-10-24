import { FunctionComponent } from "react";
import { App } from "../App";
import './style.css'
import { Conference } from "./Conference";

const EndUserApp: FunctionComponent = () => {
  return <App>
    <Conference name="redbeeConf-Vol5" startDate={new Date(2022, 4, 13, 17, 23, 42, 11)} endDate={new Date(2022, 4, 24, 17, 0, 0, 0)} description="Sé parte de una experiencia única dentro del mundo de TI, con más de +50 Speakers, +30 workshops en diferentes áreas, +100 charlas en vivo."></Conference>
    </App>
}

export default EndUserApp;