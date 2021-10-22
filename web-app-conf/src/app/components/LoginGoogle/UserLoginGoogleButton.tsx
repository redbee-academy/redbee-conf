import { FunctionComponent } from "react";
import { Button } from "react-bootstrap";
import { GoogleLogin } from "react-google-login";
import { GoogleUserInfo } from "../../domain/googleUserInfo";


const loginSucessful = (response: any) => {
    const userInfo: GoogleUserInfo = {
        email: response['profileObj'].email,
        name: response['profileObj'].name
    }

    console.log(userInfo)
}

const loginFailure = (response: any) => {
    console.log('ingresar un mail de google para postular charla');
}

const UserLoginGoogleButton : FunctionComponent = () => {
    return <GoogleLogin
    clientId={process.env.REACT_APP_GOOGLE_CLIENT_ID as string}
    render={renderProps => (
        <Button variant="light" onClick={renderProps.onClick} disabled={renderProps.disabled}>Postulá tu charla aquí</Button>
      )}
    onSuccess={loginSucessful}
    onFailure={loginFailure}
    cookiePolicy={'single_host_origin'}
  />
}

export default UserLoginGoogleButton