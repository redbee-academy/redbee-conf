import { DatePicker } from "@material-ui/pickers";
import { FunctionComponent, useEffect, useState } from "react";
import { Button, Form, Spinner } from "react-bootstrap";
import { useFetch } from "../../../hooks/fetch";
import { Conference } from "./Conference";


const UpdateConf: FunctionComponent = () => {
  const [data, setData] = useState<any>()
  const [startDate, setStartDate] = useState<any>()
  const [endDate, setEndDate] = useState<any>()
  const [description, setDescription] = useState<any>()
  const [isVisible, setVisible] = useState<any>()
  const [loading, setLoading] = useState<Boolean>(true)
  const fetchData = useFetch<any>('conferences/1')

  useEffect(() =>{
     fetchData()
     .then((data) => {
       setData(data)//esto pq no se como pasa el name
       setStartDate(data.startDate)
       setEndDate(data.endDate)
       setDescription(data.description)
       setVisible(data.visibility)
       setLoading(false)
     })
     .catch((error)=>{
        setLoading(false) 
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
        visibility: data.visibility
      }

      const body = JSON.stringify(updatedConf)

      fetch('http://localhost:3001/conferences', {
        method: 'PUT',
        body: body,
        headers: {
          'Content-Type': 'application/json',
        },
      }).catch((error)=>{
        console.log(error)
     })
    }
  

  if(loading) { 
  return <div
    style={{
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
      margin: '50px',
    }}
  >
    <Spinner animation="border" variant="primary" />
  </div>
  }else {
     return <div  style={{ maxWidth: '1000px', padding: '100px' }}>
            <h2>Editar {data.name} </h2>
  
            <Form onSubmit={submitForm}>
            <Form.Group className="mb-3" >
            
                <div style={{display:"flex", justifyContent:"space-between"}}>
                  <DatePicker
                  disablePast
                  format="dd/MM/yyyy"
                  label="Fecha de inicio"
                  value={startDate}
                  onChange={(date)=> setStartDate(date)}
                />
                  <DatePicker
                  minDate={startDate}
                  format="dd/MM/yyyy"
                  label="Fecha de finalización"
                  value={endDate}
                  onChange={(date)=> setEndDate(date)}
                />
                </div>
            </Form.Group>

              <Form.Group className="mb-3" >
                  <Form.Label>Descripción</Form.Label>
                  <Form.Control as="textarea" rows={5} defaultValue={description} onChange={e => setDescription(e.target.value)}/>
              </Form.Group>

              <Form.Label>Estado {data.visibility} </Form.Label>
              
              <Form.Check label='Es visible' checked={isVisible} onChange={e => setVisible(e.target.value)} />  
              <div style= {{display:"flex", justifyContent:"space-between", paddingTop: '20px'}} >
                <Button variant="outline-danger">Cancelar</Button>{' '}
                <Button variant="outline-primary" type="submit">Guardar</Button>{' '}
              </div>

              </Form>
  </div>
  }

}

export default UpdateConf