export class Conference{
    name: string;
    startDate: Date;
    endDate: Date;
    description?: string;

    constructor(name:string, startDate:Date, endDate:Date, description?:string){
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }
}