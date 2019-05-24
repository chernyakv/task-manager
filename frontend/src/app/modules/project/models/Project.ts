export class Project {  
  id: string;
  code: string;  
  name: string;
  summary: string; 
  manager: string;

  static cloneBase(project: Project): Project {
    const clonedProject: Project = new Project();
    clonedProject.id = project.id;
    clonedProject.code = project.code;
    clonedProject.name = project.name;
    clonedProject.summary = project.summary;
    clonedProject.manager = project.manager;    
    return clonedProject;
  }
}