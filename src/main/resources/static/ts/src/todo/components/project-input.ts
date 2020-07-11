import { InputValidator } from "../util/input-validator";
import { ProjectPersistenceService } from "../state/project-persistence-service";

export class ProjectInput {

    private templateElement: HTMLTemplateElement;
    private hostElement: HTMLDivElement;
    private formElement: HTMLFormElement;
    private titleInputElement: HTMLInputElement;
    private descriptionInputElement: HTMLInputElement;
    private peopleInputElement: HTMLInputElement;

    constructor(private inputValidator: InputValidator, private projectPersistenceService: ProjectPersistenceService) {
        this.templateElement = document.getElementById("project-input")! as HTMLTemplateElement;
        this.hostElement = document.getElementById("app")! as HTMLDivElement;

        const importedNode = document.importNode(this.templateElement.content, true);
        this.formElement = importedNode.firstElementChild as HTMLFormElement;
        this.formElement.id = "user-input";

        this.titleInputElement = this.formElement.querySelector("#title") as HTMLInputElement;
        this.descriptionInputElement = this.formElement.querySelector("#description") as HTMLInputElement;
        this.peopleInputElement = this.formElement.querySelector("#people") as HTMLInputElement;

        this.configure();
        this.attach();
    }

    private configure() {
        this.formElement.addEventListener("submit", this.submitHandler.bind(this));
    }

    private attach() {
        this.hostElement.insertAdjacentElement("afterbegin", this.formElement);
    }

    private submitHandler(event: Event) {
        event.preventDefault();

        if (
            this.inputValidator.validate({ value: this.titleInputElement.value, required: true}) &&
            this.inputValidator.validate({ value: this.descriptionInputElement.value, required: true, minLength: 5 }) &&
            this.inputValidator.validate({ value: parseInt(this.peopleInputElement.value), required: true, min: 1, max: 5 })
        ) {
            console.log("Values are valid!");
            const title = this.titleInputElement.value;
            const description = this.descriptionInputElement.value;
            const people = this.peopleInputElement.value;
            this.projectPersistenceService.addProject(title, description, parseInt(people));
            this.clearInput();
        }
        else {
            alert("Values are not valid");
        }
    }

    private clearInput() {
        this.titleInputElement.value = "";
        this.descriptionInputElement.value = "";
        this.peopleInputElement.value = "";
    }
}
