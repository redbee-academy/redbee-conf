import { CSSProperties, FunctionComponent } from 'react'
import { Spinner as BootstrapSpinner } from 'react-bootstrap'

export interface SpinnerProps {
  center?: boolean
  style?: CSSProperties
}

export const Spinner: FunctionComponent<SpinnerProps> = ({ style, center }) => {
  const spinner = (
    <BootstrapSpinner variant="primary" animation="border" style={style} />
  )

  if (center) {
    return (
      <div
        style={{
          display: 'flex',
          justifyContent: 'center',
          alignItems: 'center',
        }}
      >
        {spinner}
      </div>
    )
  }

  return spinner
}
