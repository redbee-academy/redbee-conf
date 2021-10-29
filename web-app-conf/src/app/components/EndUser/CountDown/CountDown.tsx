import {useEffect, useState} from "react";
import {Stack} from "react-bootstrap";
import './style.css'

function countDownFromNow(date: Date) {
    const now = new Date();
    const diff = date.getTime() - now.getTime();
    return {
        days: Math.floor(diff / (1000 * 60 * 60 * 24)),
        hours: 24 - (now.getUTCHours() - date.getUTCHours()),
        minutes: 60 - (now.getUTCMinutes() - date.getUTCMinutes()),
        seconds: 60 - (now.getUTCSeconds() - date.getUTCSeconds()),
    };
}

export function CountDown(props: { date: Date, className: string }) {
    const [counter, setCounter] = useState(countDownFromNow(props.date))

    useEffect(() => {
        const interval = setInterval(() => {
            setCounter(countDownFromNow(props.date))
        }, 1000);
        return () => clearInterval(interval);
    }, [props.date]);

    return <Stack direction="horizontal" gap={3} className={`text-center pill p-3 ps-4 pe-4 ${props.className}`}>
        <Stack><p className="counter">{counter.days}</p><p>Días</p></Stack>
        <Stack><p className="counter">{counter.hours}</p><p>Horas</p></Stack>
        <Stack><p className="counter">{counter.minutes}</p><p>Minutos</p></Stack>
        <Stack><p className="counter">{counter.seconds}</p><p>Segundos</p></Stack>
    </Stack>;
}