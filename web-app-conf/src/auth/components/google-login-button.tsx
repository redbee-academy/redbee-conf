import { FunctionComponent } from 'react'
import { Button } from 'react-bootstrap'
import { GoogleLogin } from 'react-google-login'
import { useHistory } from 'react-router-dom'
import { GoogleUserInfo } from '../domain'

const loginFailure = (response: any) => {
  console.log('ingresar un mail de google para postular charla')
}

export const UserLoginGoogleButton: FunctionComponent = (props) => {
  const history = useHistory()

  const loginSucessful = (response: any) => {
    const userInfo: GoogleUserInfo = {
      email: response['profileObj'].email,
      name: response['profileObj'].name,
    }

    history.push('/postulate', userInfo)
  }
  return (
    <GoogleLogin
      clientId={process.env.REACT_APP_GOOGLE_CLIENT_ID as string}
      render={(renderProps) => (
        <Button
          variant="light"
          className="btn-lg"
          onClick={renderProps.onClick}
          disabled={renderProps.disabled}
        >
          {props.children}
        </Button>
      )}
      onSuccess={loginSucessful}
      onFailure={loginFailure}
    />
  )
}
