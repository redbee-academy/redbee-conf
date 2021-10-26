import { FunctionComponent } from "react";
import { useForm, SubmitHandler } from "react-hook-form";
import * as moment from 'moment';
import { Button, Form } from "react-bootstrap";
import { Conference } from "../../domain/Conference";
import { useGet, usePost } from "../../../../../hooks/useHTTP";
import "./conference.css";
import { KeyboardDatePicker } from "@material-ui/pickers";

export const ConferenceComponent: FunctionComponent = () => {
  const {
    register,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm<Conference>();
  const onSubmit: SubmitHandler<Conference> = (data) => {
    console.log(JSON.stringify(data));
    execute("conference",data);

  }

  const [data, isLoading, error] = useGet({
    url: `conference/volume`,
  });

  const [postData, postErrors,execute] = usePost();




  if (isLoading) {
    return <div>cargando...</div>;
  } else {
    return (
      <div className="conferenceMainForm">
        <Form onSubmit={handleSubmit(onSubmit)} style={{ maxWidth: "1000px" }}>
          <Form.Group className="mb-3" controlId="formBasicName">
            <Form.Label>Nombre de la proxima CONF</Form.Label>
            <Form.Control
              type="name"
              defaultValue={`redbee conf vol. ${data}`}
              placeholder="Ingrese un nombre"
            />{" "}
          </Form.Group>
          <div className="d-flex justify-content-between">
            <Form.Group className="mb-3" controlId="formBasicStartDate">
              <Form.Label>Fecha de inicio</Form.Label>
             {/*  <KeyboardDatePicker
                      value={startDate}
                      format="dd/MM/yyyy"
                      label="Fecha de comienzo"
                      onChange={()=>{register("startDate")}}
                      disablePast
                      invalidDateMessage="Formato de fecha incorrecto"
                      minDateMessage="No debe ser menor a la fecha de hoy"
                    /> */}
              <Form.Control
                type="date"
                {...register("startDate")}
                name="startDate"
              />{" "}
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicEndDate">
              <Form.Label>Fecha de finalización</Form.Label>
              <Form.Control
                type="date"
                {...register("endDate")}
                name="endDate"
              />{" "}
            </Form.Group>
          </div>
          <Form.Group className="mb-3" controlId="formBasicDescription">
            <Form.Label>Descripcion</Form.Label>
            <Form.Control
              {...register("description")}
              name="description"
              as="textarea"
              rows={3}
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="formBasicCheckbox">
            <Form.Check
              type="checkbox"
              label="Visible"
              {...register("status")}
            />
          </Form.Group>
          <Button variant="primary" type="submit">
            Submit
          </Button>
        </Form>
      </div>
    );
  }
};
