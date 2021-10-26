import { KeyboardDatePicker } from "@material-ui/pickers";
import { FunctionComponent, useEffect, useState } from "react";
import { Button, Form, Spinner } from "react-bootstrap";
import { useFetch } from "../../../hooks/fetchMsConf";
import { Conference } from "./Conference";
import './conference.css'

interface UpdateConfProps {
  confId: string
}

const UpdateConf: FunctionComponent<UpdateConfProps> = ({
  confId,
}) => {
  const [data, setData] = useState<any>()
  const [startDate, setStartDate] = useState<any>()
  const [endDate, setEndDate] = useState<any>()
  const [description, setDescription] = useState<any>()
  const [isVisible, setVisible] = useState<any>()
  const [isLoading, setIsLoading] = useState<Boolean>(true)
  const fetchData = useFetch<any, any>(`conference/${confId}`)
  const updateData = useFetch<any, any>(`conference/${confId}`, 'PUT')

  useEffect(() =>{
     fetchData()
     .then((data) => {
       setData(data)//esto pq no se como pasa el name
       setStartDate(data.startDate)
       setEndDate(data.endDate)
       setDescription(data.description)
       setVisible(data.status)
       setIsLoading(false)
     })
     .catch((error)=>{
        setIsLoading(false)
        console.log(error)
     })
  }, [fetchData])

  const submitForm =
  (e: React.SyntheticEvent): void  => {
      e.preventDefault()

      const updatedConf: Conference = {
        id: data.id,
        name: data.name,
        startDate: startDate,
        endDate: endDate,
        description: description,
        status: isVisible
      }

      updateData(updatedConf).catch((error)=>{
        console.log(error)
     })
    }
  

  if(isLoading) {
  return <div className="d-flex justify-content-center align-items-center m-4">
    <Spinner animation="border" variant="primary" />
  </div>
  }
  else {
     return <div className="p-5 container-xl">
            <h2>Editar {data.name} </h2>
  
            <Form className="mt-3 col-12" onSubmit={submitForm}>
            <Form.Group className="mb-3" >
            
                <div className="d-flex justify-content-between">
                  <KeyboardDatePicker
                  disablePast
                  format="dd/MM/yyyy"
                  label="Fecha de inicio"
                  invalidDateMessage="Formato de fecha incorrecto"
                  minDateMessage="No debe ser menor a la fecha de hoy"
                  value={startDate}
                  onChange={(date)=> setStartDate(date)}
                />
                <KeyboardDatePicker
                  value={endDate}
                  minDate={startDate}
                  format="dd/MM/yyyy"
                  label="Fecha de finalización"
                  invalidDateMessage="Formato de fecha incorrecto"
                  minDateMessage="No debe ser menor a la fecha de inicio de la Conf."
                  onChange={(date)=> setEndDate(date)}
                />
                </div>
            </Form.Group>

              <Form.Group className="mb-3" >
                  <Form.Label>Descripción</Form.Label>
                  <Form.Control as="textarea" rows={5} value={description} onChange={e => setDescription(e.target.value)}/>
              </Form.Group>

              <Form.Label>Estado</Form.Label>
              <Form.Check label='Es visible' checked={isVisible} onClick={()=> setVisible(!isVisible)} />
              <div className="d-flex justify-content-between pt-4" >
                <Button variant="outline-secondary">Cancelar</Button>{' '}
                <Button variant="primary" type="submit">Guardar</Button>{' '}
              </div>
              </Form>
  </div>
  }
  //TODO: - agregar mensaje de error o de exito
  //      - arreglar checkbox
  //      -? deshabilitar boton guardar si hay errores
}

export default UpdateConf