import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { UploadEvent, UploadFile, FileSystemFileEntry, FileSystemDirectoryEntry } from 'ngx-file-drop';
import { FileService } from 'src/app/services/file.service';
import { BsModalRef } from 'ngx-bootstrap';


@Component({
  selector: 'app-add-file-modal',
  templateUrl: './add-file-modal.component.html',
  styleUrls: ['./add-file-modal.component.less']
})
export class AddFileModalComponent implements OnInit {

  @Input() taskId: string;
  @Input() projectId: string;
  @Output() uploadFiles: EventEmitter<File[]> = new EventEmitter<File[]>();

  public uploadedFiles: UploadFile[] = [];
  public files: File[] = [];

  constructor(private fileService: FileService,
    private modalRef: BsModalRef) { }

  ngOnInit() {  
    console.log(this.projectId);
  }

  public dropped(event: UploadEvent) {
    this.uploadedFiles = event.files;
 
    for (const droppedFile of event.files) {
      if (droppedFile.fileEntry.isFile) {
        const fileEntry = droppedFile.fileEntry as FileSystemFileEntry;
        fileEntry.file((file: File) => {
          this.files.push(file)                
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
  _removeFile(index: number) {
    this.files.splice(index, 1);
  }

  _addFiles(): void {
    this.uploadFiles.emit(this.files);
    this._closeModal();
  }

  _closeModal() : void {
    this.modalRef.hide();
  }
}
