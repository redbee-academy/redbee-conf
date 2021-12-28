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
    const conference = await axios
      .get<UnparsedConference[]>(
        `${this.appConfiguration.conferencesUrl}/conference`,
        {
          params: {
            current: '1',
            visible:
              this.options.visible == null
                ? undefined
                : this.options.visible
                ? '1'
                : '0',
          },
        }
      )
      .then((res) => res.data.map(parseConference)?.[0])

    this.conferenceSubject.next(conference)
    this.initializedSubject.next(true)
  }

  private conferenceSubject = new BehaviorSubject<Conference | undefined>(
    undefined
  )
  private initializedSubject = new BehaviorSubject(false)
}
