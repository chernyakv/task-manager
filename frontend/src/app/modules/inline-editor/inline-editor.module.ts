import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InputEditorComponent } from './input-editor/input-editor.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

@NgModule({
  declarations: [InputEditorComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,   
    FormsModule
  ],
  exports: [InputEditorComponent],
})
export class InlineEditorModule { }
