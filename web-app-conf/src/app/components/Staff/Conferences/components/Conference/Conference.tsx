import { FunctionComponent, useState } from "react";
import { useForm, SubmitHandler } from "react-hook-form";
import { Button, Form } from "react-bootstrap";
import { Conference } from "../../domain/Conference";
import "./conference.css";



export const ConferenceComponent: FunctionComponent = () => {
  const {
    register,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm<Conference>();
  const onSubmit: SubmitHandler<Conference> = (data) => console.log(data);
  const [startDate, setStartDate] = useState<any>();
  const [endDate, setEndDate] = useState<any>();

  return (
    <div className="conferenceMainForm">
      <Form onSubmit={handleSubmit(onSubmit)} style={{ maxWidth: "1000px" }}>
        <Form.Group className="mb-3" controlId="formBasicName">
          <Form.Label>Nombre de la proxima CONF</Form.Label>
          <Form.Control
            type="name"
            name="name"
            placeholder="Ingrese un nombre"
          />{" "}
        </Form.Group>
        <div className="d-flex justify-content-between">
        <Form.Group className="mb-3" controlId="formBasicStartDate">
        <Form.Label>Fecha de inicio</Form.Label>
          <Form.Control
            type="date"
            {...register("start_date")}
            name="start_date"
          />{" "}
        </Form.Group>
          <Form.Group className="mb-3" controlId="formBasicEndDate">
          <Form.Label>Fecha de finalización</Form.Label>
          <Form.Control
            type="date"
            {...register("end_date")}
            name="end_date"
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
          <Form.Check type="checkbox" label="Visible" {...register("status")} />
        </Form.Group>
        <Button variant="primary" type="submit">
          Submit
        </Button>
      </Form>
    </div>
  );
};
