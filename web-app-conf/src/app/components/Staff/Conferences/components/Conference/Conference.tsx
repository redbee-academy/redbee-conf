import { FunctionComponent } from "react";
import './conference.css'
import { Button, Form, FormControl } from "react-bootstrap";
import { Conference } from "../../domain/Conference";
import { KeyboardDatePicker } from "@material-ui/pickers";


export interface ConferenceProps {
    conference: Conference
}

export const ConferenceComponent: FunctionComponent = () => (
    
    <Form className="conferenceMainForm" style={{ maxWidth: '1000px'}} >
        <div>
        <Form.Group className="mb-3" controlId="formBasicName">
            <Form.Label>Nombre de la proxima CONF</Form.Label>
            <Form.Control type="name" placeholder="Ingrese un nombre" /> {/* El placeholder no deberia estar ya que el nombre se genera automaticamente */}
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicStartDate">
            {/* <KeyboardDatePicker
                  value={Date.now}
                  format="dd/MM/yyyy"
                  label="Fecha de finalización"
                  onChange={(date: date, value?: string | null) => }
                />*/}
        </Form.Group> 

        <Form.Group className="mb-3" controlId="formBasicDescription">
            <Form.Label>Descripcion</Form.Label>
            <Form.Control as="textarea" rows={3} />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicCheckbox">
            <Form.Check type="checkbox" label="Visible"/>
        </Form.Group>
        <Button variant="primary" type="submit">
            Submit
        </Button>
        </div>
        
</Form>
)