import { ProjectPersistenceService } from "./state/project-persistence-service";
import { InputValidator } from "./util/input-validator";
import { ProjectList } from "./components/project-list";
import { ProjectInput } from "./components/project-input";

const projectPersistenceService = new ProjectPersistenceService();
new ProjectInput(new InputValidator(), projectPersistenceService);
new ProjectList("active", projectPersistenceService);
new ProjectList("finished", projectPersistenceService);
