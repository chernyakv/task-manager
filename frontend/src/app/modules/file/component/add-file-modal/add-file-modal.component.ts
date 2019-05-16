import { Component, OnInit, Input } from '@angular/core';
import { UploadEvent, UploadFile, FileSystemFileEntry, FileSystemDirectoryEntry } from 'ngx-file-drop';
import { FileService } from 'src/app/_services/file.service';


@Component({
  selector: 'app-add-file-modal',
  templateUrl: './add-file-modal.component.html',
  styleUrls: ['./add-file-modal.component.less']
})
export class AddFileModalComponent implements OnInit {

  @Input() taskId: string;
  @Input() projectId: string;

  public uploadedFiles: UploadFile[] = [];
  public files: File[] = [];

  constructor(private fileService: FileService) { }

  ngOnInit() {  
    console.log(this.projectId);
  }

  public dropped(event: UploadEvent) {
    this.uploadedFiles = event.files;
 
    for (const droppedFile of event.files) {
      if (droppedFile.fileEntry.isFile) {
        const fileEntry = droppedFile.fileEntry as FileSystemFileEntry;
        fileEntry.file((file: File) => {
          console.log(file);
          this.fileService.saveFile(file, this.taskId, this.projectId).subscribe(()=>{

          })       
        });
      }
    }
  }

  public fileOver(event){
    console.log(event);
  }
 
  public fileLeave(event){
    console.log(event);
  }


}
