import { ProjectPersistenceService } from "../state/project-persistence-service";
import { Project, ProjectState } from "../model/project";

export class ProjectList {

    private templateElement: HTMLTemplateElement;
    private hostElement: HTMLDivElement;
    private sectionElement: HTMLElement;
    private projects: Project[] = [];

    constructor(private type: 'active' | 'finished', private projectPersistenceService: ProjectPersistenceService) {
        this.templateElement = document.getElementById("project-list")! as HTMLTemplateElement;
        this.hostElement = document.getElementById("app")! as HTMLDivElement;

        const importedNode = document.importNode(this.templateElement.content, true);
        this.sectionElement = importedNode.firstElementChild as HTMLElement;
        this.sectionElement.id = `${this.type}-projects`;

        projectPersistenceService.addListener((projects: Project[]) => {
            this.projects = projects.filter(project => {
                if (this.type === 'active') {
                    return project.status === ProjectState.ACTIVE;
                } else {
                    return project.status === ProjectState.FINISHED;
                }
            });
            this.renderProjects();
        });

        this.attach();
        this.renderContent();
    }

    private renderProjects() {
        const listElement = document.getElementById(`${this.type}-projects-list`) as HTMLUListElement;
        listElement.innerHTML = ""; // clear list before append all projects to avoid duplication
        this.projects.forEach(project => {
            const listItem = document.createElement("li");
            listItem.textContent = project.title;
            listElement.appendChild(listItem);
        });
    }

    private renderContent() {
        this.sectionElement.querySelector("ul")!.id = `${this.type}-projects-list`;
        this.sectionElement.querySelector("h2")!.textContent = `${this.type.toUpperCase()} PROJECTS`;
    }

    private attach() {
        this.hostElement.insertAdjacentElement("beforeend", this.sectionElement);
    }
}
