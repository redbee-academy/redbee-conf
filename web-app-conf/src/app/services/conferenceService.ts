import { Conference } from "../domain/conference"
import axios from "axios";

export const getConferenceByVisibility = ():Promise<Conference> => {
    return axios
        .get<Conference>("http://localhost:3001/conference")
        .then(response => {
            console.log("respuesta", response);
            return new Conference(response.data.name, new Date(response.data.startDate), new Date(response.data.endDate), response.data.description);
        });
   // return Promise.resolve(new Conference("redbeeConf-Vol5", new Date(2022, 4, 13, 17, 23, 42, 11), new Date(2022, 4, 24, 17, 0, 0, 0), "Sé parte de una experiencia única dentro del mundo de TI, con más de +50 Speakers, +30 workshops en diferentes áreas, +100 charlas en vivo."))

}