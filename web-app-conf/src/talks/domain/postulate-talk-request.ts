import { Talk } from './talk'

export type PostulateTalkRequest = Omit<Talk, 'id' | 'creation_date'>
