import { Component, OnInit, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { FileService } from 'src/app/_services/file.service';
import { TaskDetailComponent } from 'src/app/modules/task/components/task-detail/task-detail.component';

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
    this.fileService.getFiles(this.taskId,this.projectId).subscribe(data=>{
      this.files = data;
      console.log(this.files);
    })
  }

}
