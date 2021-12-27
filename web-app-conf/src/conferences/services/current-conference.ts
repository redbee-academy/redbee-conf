import axios from 'axios'
import { BehaviorSubject, Observable } from 'rxjs'
import { AppConfiguration } from '../../app'
import { Conference, parseConference, UnparsedConference } from '../domain'

export interface CurrentConferenceServiceOptions {
  visible?: boolean
}

export class CurrentConferenceService {
  public constructor(
    private appConfiguration: AppConfiguration,
    private options: CurrentConferenceServiceOptions
  ) {
    this.conference = this.conferenceSubject
    this.initialized = this.initializedSubject
  }

  public conference: Observable<Conference | undefined>
  public initialized: Observable<boolean>

  public async init(): Promise<void> {
    let url = `${this.appConfiguration.conferencesUrl}/conference/current`

    if (this.options.visible != null) {
      url += '?visible=' + (this.options.visible ? '1' : '0')
    }

    const conference = await axios
      .get<UnparsedConference>(url)
      .then((res) => parseConference(res.data))
      .catch((error) => {
        if (error?.response?.status === 404) {
          return undefined
        } else {
          throw error
        }
      })

    this.conferenceSubject.next(conference)
    this.initializedSubject.next(true)
  }

  private conferenceSubject = new BehaviorSubject<Conference | undefined>(
    undefined
  )
  private initializedSubject = new BehaviorSubject(false)
}
