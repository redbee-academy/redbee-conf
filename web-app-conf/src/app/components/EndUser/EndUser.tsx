import { FunctionComponent } from "react";
import { App } from "../App";
import ApplicationButton from "../ApplicationButton";


const EndUserApp: FunctionComponent = () => {
  return <App>
    <div>Edita <code>src/app/components/EndUser.tsx</code> para continuar</div>
    <div><ApplicationButton/></div>
  </App>
}

export default EndUserApp