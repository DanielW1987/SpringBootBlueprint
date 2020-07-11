export interface Project {
    readonly id: string;
    readonly title: string;
    readonly description: string;
    readonly numOfPeople: number;
    readonly status: ProjectState;
}

export enum ProjectState {
    ACTIVE, FINISHED
}