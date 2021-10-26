import { Conference } from "../domain/conference"
import axios from "axios";
import { AppConfiguration } from "..";

export const getConferenceByVisibility = (appConfiguration:AppConfiguration):Promise<Conference> => {
    return axios
        .get<Conference>(`${appConfiguration.conferencesUrl}/conference`)
        .then(response => {
            console.log("respuesta", response);
            return new Conference(response.data.name, new Date(response.data.startDate), new Date(response.data.endDate), response.data.description);
        });
}
