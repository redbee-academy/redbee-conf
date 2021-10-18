import { FunctionComponent } from "react";
import { Button, FloatingLabel, Form } from "react-bootstrap";

const UpdateConf: FunctionComponent = () => {
  return <div  style={{ maxWidth: '600px', padding: '20px' }}>
      <h2>Editar Redbee Conf vol.5</h2>
  <Form>
  <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
    <Form.Label>Fecha Inicio</Form.Label>
    <Form.Control type="date" />
  </Form.Group>
  <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
    <Form.Label>Fecha Fin</Form.Label>
    <Form.Control type="date" placeholder="name@example.com" />
  </Form.Group>
  <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
        <Form.Label>Descripción</Form.Label>
        <Form.Control as="textarea" rows={3} />
    </Form.Group>
    <Form.Label>Estado</Form.Label>
    <Form.Select>
        <option>Visible</option>
        <option value="1">Oculto</option>
    </Form.Select>
    <div style= {{display:"flex", justifyContent:"space-between", paddingTop: '20px'}} >
      <Button variant="outline-danger">Cancelar</Button>{' '}
      <Button variant="outline-primary">Guardar</Button>{' '}
    </div>
    </Form>
  </div>

}

export default UpdateConf