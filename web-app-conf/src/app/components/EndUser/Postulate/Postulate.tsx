import React, { useState } from "react";
import { Button, Container, Form, Alert } from "react-bootstrap";
import { useHistory } from "react-router-dom";

import { ReactComponent as Logo } from "../../../../assets/images/logo-redbee-conf.svg";
import TalkService from "../../services/TalkService";

const redbeeDomains = ["redb.ee", "redbee.io"];

interface Result {
    isSuccess: boolean
    message: string
}

const Postulate = () => {
    const history: any = useHistory();
    const userData = history.location.state;
    const isABee = redbeeDomains.some((domain) =>
        userData.email.endsWith(domain)
    );
    const [postulateForm, setPostulateForm] = useState({
        speaker_name: userData.name,
        speaker_email: userData.email,
        reference: "",
        talk_name: "",
        redbee_employee: isABee,
        talk_topic: "",
        talk_description: "",
    });
    const [result, setResult] = useState<Result>()

    const setPostulateField = (field: string) => (e: any) =>
        setPostulateForm((prevState) => ({
            ...prevState,
            [field]: e.target.value,
        }));

    const handleSubmit = () => {
        TalkService.postulateTalk(postulateForm)
            .then(_ => setResult({
                isSuccess: true,
                message: "Tu charla se postuló con exito!"
            }))
            .catch(_ => setResult({
                isSuccess: false,
                message: "Ocurrió un error postulando tu charla. Por favor, intentá nuevamente."
            }))
    }

    return (
        <div>
            <div style={{ padding: "40px 0px 20px 40px" }}>
                <div style={{ paddingBottom: "20px" }}>
                    <Logo />
                </div>
                <p style={{ color: "#515357", fontSize: "56px", lineHeight: "56px" }}>
                    Sumate a la <br /> redbee Conference
                </p>
            </div>
            <div
                style={{
                    backgroundColor: "#F9F9F9",
                    padding: "20px 40px 20px",
                    height: "100%",
                }}
            >
                <Form>
                    <Form.Group className="mb-3" controlId="formBasicName">
                        <Form.Label>Nombre y apellido</Form.Label>
                        <Form.Control
                            type="name"
                            placeholder="Ingrese un nombre"
                            readOnly
                            value={postulateForm.speaker_name}
                        />
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="formBasicName">
                        <Form.Label>Email</Form.Label>
                        <Form.Control
                            type="name"
                            placeholder="Ingrese un mail"
                            readOnly
                            value={postulateForm.speaker_email}
                        />
                    </Form.Group>
                    {!isABee && (
                        <Form.Group className="mb-3" controlId="formBasicName">
                            <Form.Label>Linkeding url</Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="Ingrese una referencia"
                                value={postulateForm.reference}
                                onChange={setPostulateField("reference")}
                            />
                        </Form.Group>
                    )}

                    <Form.Group className="mb-3" controlId="formBasicName">
                        <Form.Label>Nombre de la charla</Form.Label>
                        <Form.Control
                            type="name"
                            placeholder="Ingrese un nombre"
                            value={postulateForm.talk_name}
                            onChange={setPostulateField("talk_name")}
                        />
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="formBasicName">
                        <Form.Label>Topico</Form.Label>
                        <Form.Control
                            type="name"
                            placeholder="Ingrese un topico"
                            value={postulateForm.talk_topic}
                            onChange={setPostulateField("talk_topic")}
                        />
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="formBasicDescription">
                        <Form.Label>Descripcion</Form.Label>
                        <Form.Control
                            as="textarea"
                            rows={3}
                            value={postulateForm.talk_description}
                            onChange={setPostulateField("talk_description")}
                        />
                    </Form.Group>

                    <Button variant="primary" onClick={handleSubmit}>
                        Submit
                    </Button>
                </Form>
                {result && (
                    <Alert className="mt-3" variant={result.isSuccess ? 'success' : 'danger'}>
                        {result.message} {' '}
                        <Alert.Link href="/">Volver al home.</Alert.Link>
                    </Alert>
                )}
            </div>
        </div>
    );
};
export default Postulate;
