import React, {
  FunctionComponent,
  ReactElement,
  useCallback,
  useState,
} from 'react'
import { Alert, Button, Form } from 'react-bootstrap'
import { SubmitHandler, useForm } from 'react-hook-form'
import { UnparsedConference } from '../domain'
import DatePicker from 'react-datepicker'
import { DATE_PICKER_FORMAT } from '../../shared'
import moment from 'moment'

export interface ConferenceFormData
  extends Omit<UnparsedConference, 'startDate' | 'endDate' | 'volume'> {
  startDate: Date
  endDate: Date
}

export const conferenceFormDataToUnparsedFormData = (
  data: ConferenceFormData
): UnparsedConference => ({
  ...data,
  startDate: moment(data.startDate).toISOString(),
  endDate: moment(data.endDate).toISOString(),
})

export interface ConferenceFormProps {
  onSubmit: (data: ConferenceFormData) => void
  defaultValues?: ConferenceFormData
  volume?: string
  error?: string
  extraButtonsAside?: ReactElement
}

export const ConferenceForm: FunctionComponent<ConferenceFormProps> = ({
  onSubmit,
  defaultValues,
  volume,
  error,
  extraButtonsAside,
}) => {
  const [startDate, setStartDate] = useState<Date | undefined>(
    defaultValues?.startDate
  )
  const [endDate, setEndDate] = useState<Date | undefined>(
    defaultValues?.endDate
  )
  const { register, handleSubmit, formState, setValue } =
    useForm<ConferenceFormData>({
      defaultValues,
    })

  const submit: SubmitHandler<ConferenceFormData> = useCallback(
    (data) => {
      onSubmit({
        ...data,
        startDate: startDate as Date,
        endDate: endDate as Date,
      })
    },
    [endDate, onSubmit, startDate]
  )

  return (
    <Form onSubmit={handleSubmit(submit)}>
      {volume ? (
        <Form.Group className="mb-3" controlId="formBasicName">
          <Form.Label>Nombre de la proxima CONF</Form.Label>
          <Form.Control
            type="name"
            defaultValue={`redbee conf vol. ${volume}`}
            placeholder="Ingrese un nombre"
          />{' '}
        </Form.Group>
      ) : undefined}
      <div className="d-flex justify-content-between">
        <Form.Group className="mb-3" controlId="formBasicStartDate">
          <Form.Label>Fecha de inicio</Form.Label>
          <DatePicker
            required
            className="form-control"
            dateFormat={DATE_PICKER_FORMAT}
            selected={startDate}
            onChange={(date) => {
              if (date) {
                setStartDate(date as Date)
                setValue('startDate', date as Date, {
                  shouldDirty: true,
                  shouldTouch: true,
                })
              }
            }}
          />
        </Form.Group>
        <Form.Group className="mb-3" controlId="formBasicEndDate">
          <Form.Label>Fecha de finalización</Form.Label>
          <DatePicker
            required
            className="form-control"
            dateFormat={DATE_PICKER_FORMAT}
            selected={endDate}
            onChange={(date) => {
              if (date) {
                setEndDate(date as Date)
                setValue('endDate', date as Date, {
                  shouldDirty: true,
                  shouldTouch: true,
                })
              }
            }}
          />
        </Form.Group>
      </div>
      <Form.Group className="mb-3" controlId="formBasicDescription">
        <Form.Label>Descripcion</Form.Label>
        <Form.Control
          {...register('description')}
          name="description"
          as="textarea"
          rows={3}
        />
      </Form.Group>
      <Form.Group className="mb-3" controlId="formBasicCheckbox">
        <Form.Check type="checkbox" label="Visible" {...register('status')} />
      </Form.Group>
      {error ? <Alert variant="danger">{error}</Alert> : undefined}
      <div style={{ display: 'flex', justifyContent: 'space-between' }}>
        {extraButtonsAside}
        <Button
          variant="primary"
          type="submit"
          disabled={defaultValues && !formState.isDirty}
        >
          Guardar
        </Button>
      </div>
    </Form>
  )
}
