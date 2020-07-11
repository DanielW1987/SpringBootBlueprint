import { Project, ProjectState } from "../model/project";

export class ProjectPersistenceService {
    private listeners: ProjectListener[] = []
    private projects: Project[] = [];

    addProject(title: string, description: string, numOfPeople: number) {
        this.projects.push({
            id: Math.random().toString(),
            title: title,
            description: description,
            numOfPeople: numOfPeople,
            status: ProjectState.ACTIVE
        });

        this.listeners.forEach(listnerFn => {
            listnerFn(this.projects.slice());
        });
    }

    queryProjects(): Project[] {
        return this.projects;
    }

    addListener(listenerFn: ProjectListener) {
        this.listeners.push(listenerFn);
    }
}

type ProjectListener = (items: Project[]) => void;
