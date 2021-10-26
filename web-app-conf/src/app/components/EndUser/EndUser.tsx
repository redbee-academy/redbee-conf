import { FunctionComponent } from "react";
import { App } from "../App";
import './style.css'
import { ConferenceComponent } from "./ConferenceComponent";

const EndUserApp: FunctionComponent = () => {
  return <App>
    <ConferenceComponent/>
    </App>
}

export default EndUserApp;