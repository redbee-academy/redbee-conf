import { Conference } from "../domain/conference"
import axios from "axios";

export const getConferenceByVisibility = ():Promise<Conference> => {
    return axios
        .get<Conference>("http://localhost:3001/conference")
        .then(response => {
            console.log("respuesta", response);
            return new Conference(response.data.name, new Date(response.data.startDate), new Date(response.data.endDate), response.data.description);
        });
}