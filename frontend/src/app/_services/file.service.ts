import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  constructor(private http: HttpClient) { }

  saveFile(file: File, taskId: string, projectId: string): Observable<any> {
    const formData: FormData = new FormData();

    formData.append('file', file);
    
    return this.http.post<any>(`${environment.apiUrl}/api/v1/files/file?taskId=${taskId}&projectId=${projectId}`, formData);
  }


  getFiles(taskId: string, projectId: string): Observable<String[]> {    
    return this.http.get<String[]>(`${environment.apiUrl}/api/v1/files?taskId=${taskId}&projectId=${projectId}`);
  }
}
