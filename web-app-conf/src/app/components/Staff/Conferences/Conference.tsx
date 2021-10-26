export interface Conference {
    id: string,
    name: string,
    startDate: Date,
    endDate: Date,
    description: string,
    status: boolean,
    volume?: number
}