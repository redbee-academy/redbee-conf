import { FunctionComponent, useEffect, useState } from 'react'
import { Stack } from 'react-bootstrap'
import './count-down.css'

function countDownFromNow(date: Date) {
  const now = new Date()
  const diff = date.getTime() - now.getTime()
  return {
    days: Math.floor(diff / (1000 * 60 * 60 * 24)),
    hours: 24 - Math.abs(now.getHours() || 24 - date.getHours() || 24),
    minutes: 60 - Math.abs(now.getMinutes() || 60 - date.getMinutes() || 60),
    seconds: 60 - Math.abs(now.getSeconds() || 60 - date.getSeconds() || 60),
  }
}

export interface CountDownProps {
  date: Date
  className: string
}

export const CountDown: FunctionComponent<CountDownProps> = (props) => {
  const [counter, setCounter] = useState(() => countDownFromNow(props.date))

  useEffect(() => {
    const interval = setInterval(() => {
      setCounter(countDownFromNow(props.date))
    }, 1000)
    return () => clearInterval(interval)
  }, [props.date])

  return (
    <Stack
      direction="horizontal"
      gap={3}
      className={`text-center pill p-3 ps-4 pe-4 ${props.className}`}
    >
      <Stack>
        <p className="counter">{counter.days}</p>
        <p>Días</p>
      </Stack>
      <Stack>
        <p className="counter">{counter.hours}</p>
        <p>Horas</p>
      </Stack>
      <Stack>
        <p className="counter">{counter.minutes}</p>
        <p>Minutos</p>
      </Stack>
      <Stack>
        <p className="counter">{counter.seconds}</p>
        <p>Segundos</p>
      </Stack>
    </Stack>
  )
}
