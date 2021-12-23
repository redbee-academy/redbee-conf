import moment, { Duration, Moment } from 'moment'
import {
  CSSProperties,
  FunctionComponent,
  useEffect,
  useMemo,
  useState,
} from 'react'
import { Stack } from 'react-bootstrap'
import './count-down.css'

function countDownFromNow(date: Moment): Duration {
  return moment.duration(date.diff(moment()))
}

export interface CountDownProps {
  date: Moment
  className?: string
  style?: CSSProperties
}

export const CountDown: FunctionComponent<CountDownProps> = (props) => {
  const [counter, setCounter] = useState<Duration>(() =>
    countDownFromNow(props.date)
  )

  useEffect(() => {
    const interval = setInterval(() => {
      setCounter(countDownFromNow(props.date))
    }, 1000)
    return () => clearInterval(interval)
  }, [props.date])

  const stackClassName = useMemo(() => {
    const base = 'text-center pill p-3 ps-4 pe-4'

    return props.className ? base + props.className : base
  }, [props.className])

  return (
    <Stack
      style={props.style}
      direction="horizontal"
      gap={3}
      className={stackClassName}
    >
      <Stack>
        <p className="counter">{counter.get('days')}</p>
        <p>Días</p>
      </Stack>
      <Stack>
        <p className="counter">{counter.get('hours')}</p>
        <p>Horas</p>
      </Stack>
      <Stack>
        <p className="counter">{counter.get('minutes')}</p>
        <p>Minutos</p>
      </Stack>
      <Stack>
        <p className="counter">{counter.get('seconds')}</p>
        <p>Segundos</p>
      </Stack>
    </Stack>
  )
}
