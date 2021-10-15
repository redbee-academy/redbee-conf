import { FunctionComponent } from "react";
import { FloatingLabel, Form } from "react-bootstrap";

const UpdateConf: FunctionComponent = () => {
  return <div  style={{ maxWidth: '600px', padding: '20px' }}>
      <h2>Editar Redbee Conf vol.5</h2>
  <Form>
  <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
    <Form.Label>Fecha Incio</Form.Label>
    <Form.Control type="date" />
  </Form.Group>
  <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
    <Form.Label>Fecha Fin</Form.Label>
    <Form.Control type="date" placeholder="name@example.com" />
  </Form.Group>
  <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
        <Form.Label>Descripcion</Form.Label>
        <Form.Control as="textarea" rows={3} />
    </Form.Group>
    <FloatingLabel controlId="floatingSelect" label="Estado">
    <Form.Select aria-label="Floating label select example">
        <option>Visible</option>
        <option value="1">Oculto</option>
    </Form.Select>
    </FloatingLabel>
   
    </Form>
  </div>

}

export default UpdateConf