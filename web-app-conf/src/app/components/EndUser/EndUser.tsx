import { FunctionComponent } from "react";
import { App } from "../App";
import UserLoginGoogleButton from "../LoginGoogle/UserLoginGoogleButton";


const EndUserApp: FunctionComponent = () => {
  return <App>
    <div>Edita <code>src/app/components/EndUser.tsx</code> para continuar</div>
    <div><UserLoginGoogleButton/></div>
  </App>
}

export default EndUserApp;