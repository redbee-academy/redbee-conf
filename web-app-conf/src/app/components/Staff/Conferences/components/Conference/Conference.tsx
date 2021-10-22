import { FunctionComponent, useState } from "react";
import './conference.css'
import { Button, Form } from "react-bootstrap";
import { Conference } from "../../domain/Conference";
import { KeyboardDatePicker } from "@material-ui/pickers";


export interface ConferenceProps {
    conference: Conference
}

export const ConferenceComponent: FunctionComponent = () => {
    const [startDate, setStartDate]= useState<any>();
    const [endDate, setEndDate] = useState<any>();
    
    return (
        <div className="conferenceMainForm">
        <Form style={{ maxWidth: '1000px' }} >
            <Form.Group className="mb-3" controlId="formBasicName">
                <Form.Label>Nombre de la proxima CONF</Form.Label>
                <Form.Control type="name" placeholder="Ingrese un nombre" /> {/* El placeholder no deberia estar ya que el nombre se genera automaticamente */}
            </Form.Group>
            <div className="d-flex justify-content-between">
            <Form.Group className="mb-3" controlId="formBasicStartDate">
                <KeyboardDatePicker
                      value={startDate}
                      format="dd/MM/yyyy"
                      label="Fecha de comienzo"
                      onChange={(date)=> setStartDate(date)}
                      disablePast
                      invalidDateMessage="Formato de fecha incorrecto"
                      minDateMessage="No debe ser menor a la fecha de hoy"
                    />
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicEndDate">
                <KeyboardDatePicker
                      value={endDate}
                      format="dd/MM/yyyy"
                      label="Fecha de finalización"
                      onChange={(date)=> setEndDate(date)}
                      disablePast
                      minDate={startDate}
                      invalidDateMessage="Formato de fecha incorrecto"
                      minDateMessage="No debe ser menor a la fecha de hoy"
                    />
            </Form.Group>
            </div>
            
    
            <Form.Group className="mb-3" controlId="formBasicDescription">
                <Form.Label>Descripcion</Form.Label>
                <Form.Control as="textarea" rows={3} />
            </Form.Group>
    
            <Form.Group className="mb-3" controlId="formBasicCheckbox">
                <Form.Check type="checkbox" label="Visible" />
            </Form.Group>
            <Button variant="primary" type="submit">
                Submit
            </Button>
        </Form>
    </div>
    )
        
     
}