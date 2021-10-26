import {Conference} from "../domain/conference"
import axios from "axios";
import {AppConfiguration} from "..";

export const getConferenceByVisibility = (appConfiguration: AppConfiguration): Promise<Conference[]> => {
    return axios
        .get<Conference[]>(`${appConfiguration.conferencesUrl}/conference?visible=true`)
        .then(response => {
            return response.data.map(conf => ({
                ...conf,
                ...{
                    startDate: new Date(conf.startDate),
                    endDate: new Date(conf.endDate)
                }
            }));
        });
}
