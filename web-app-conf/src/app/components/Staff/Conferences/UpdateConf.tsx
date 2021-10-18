import { DatePicker, MuiPickersUtilsProvider } from "@material-ui/pickers";
import { FunctionComponent, useEffect, useState } from "react";
import { Button, Form, Spinner } from "react-bootstrap";
import { useFetch } from "../../../hooks/fetch";
import DateFnsUtils from '@date-io/date-fns';


const UpdateConf: FunctionComponent = () => {
  const [data, setData] = useState<any>()
  const [date, setDate] = useState<any>()
  const [loading, setLoading] = useState<Boolean>(true)
  const fetchData = useFetch<any>('conferences/1')

  useEffect(() =>{
     fetchData()
     .then((data) => {
       setData(data)
       setLoading(false)
       console.log(data)
     })
     .catch((error)=>{
        setLoading(false) 
        console.log(error)
     })
  }, [fetchData])


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
  <Form>
  <Form.Group className="mb-3" >
    <MuiPickersUtilsProvider utils={DateFnsUtils}>
      <div style={{display:"flex", justifyContent:"space-between"}}>
          <DatePicker
        disablePast
        format="dd/MM/yyyy"
        label="Fecha de inicio"
        value={data.startDate}
        onChange={(date)=> setDate(date)}
      />
        <DatePicker
        disablePast
        format="dd/MM/yyyy"
        label="Fecha de finalización"
        value={data.startDate}
        onChange={(date)=> setDate(date)}
      />
      </div>
      
    </MuiPickersUtilsProvider>
  </Form.Group>
  <Form.Group className="mb-3" defaultValue={data.endDate}>
        <Form.Label>Descripción</Form.Label>
        <Form.Control as="textarea" rows={3} defaultValue={data.description}/>
    </Form.Group>
    <Form.Label>Estado</Form.Label>
    <Form.Select  defaultValue={data.visibility}>
        <option value='0'>No visible</option>
        <option value='1' >Visible</option>
    </Form.Select>
    <div style= {{display:"flex", justifyContent:"space-between", paddingTop: '20px'}} >
      <Button variant="outline-danger">Cancelar</Button>{' '}
      <Button variant="outline-primary">Guardar</Button>{' '}
    </div>
    </Form>
  </div>
  }

}

export default UpdateConf