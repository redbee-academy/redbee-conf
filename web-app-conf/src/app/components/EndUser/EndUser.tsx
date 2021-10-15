import { FunctionComponent } from "react";
import { App } from "../App";
import { GoogleLogin } from 'react-google-login';


const responseGoogle = (response:any) => {
  console.log(response);
}

const EndUserApp: FunctionComponent = () => {
  return (
    <App>
      <div>
        Edita <code>src/app/components/EndUser.tsx</code> para continuar
      </div>
      
      <GoogleLogin
        clientId="658977310896-knrl3gka66fldh83dao2rhgbblmd4un9.apps.googleusercontent.com"
        buttonText="Login"
        onSuccess={responseGoogle}
        onFailure={responseGoogle}
        cookiePolicy={"single_host_origin"}
      />
      
    </App>
  );
};

export default EndUserApp;