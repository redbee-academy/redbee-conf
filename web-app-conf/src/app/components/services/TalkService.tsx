import axios from 'axios'

const url = process.env.REACT_APP_MS_TALKS_URL

const TalkService = {
  postulateTalk: (form: any) => axios.post(`${url}/talks`, form),
}

export default TalkService
