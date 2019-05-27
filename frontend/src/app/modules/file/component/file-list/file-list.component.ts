import { Component, OnInit, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { FileService } from 'src/app/services/file.service';
import { TaskDetailComponent } from 'src/app/modules/task/components/task-detail/task-detail.component';
import { HttpResponse } from '@angular/common/http';
import {saveAs} from 'file-saver';

@Component({
  selector: 'app-file-list',
  templateUrl: './file-list.component.html',
  styleUrls: ['./file-list.component.less']
})
export class FileListComponent implements OnInit {

  @Input() taskId: string;
  @Input() projectId: string;

  public files: String[];


  constructor(private fileService: FileService) { }

  ngOnInit() {    
    this._updateFileList();
  }

  _onClick(file:string){
    this.fileService.download(file, this.taskId, this.projectId).subscribe((data: HttpResponse<Blob>) => { 
      const fileName = decodeURIComponent(file);
      saveAs(data.body, fileName);
    })
  }

  onDelete(file:string) {
    this.fileService.delete(file, this.taskId, this.projectId).subscribe((data) => { 
      this._updateFileList();
    })
  }  

  _updateFileList() {
    this.fileService.getFiles(this.taskId,this.projectId).subscribe(data=>{
      this.files = data;
    })
  }

  
}
