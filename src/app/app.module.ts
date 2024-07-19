import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { FormComponent } from './components/form/form.component';
import { PartDetailsComponent } from './components/part-details/part-details.component';
import { FormInitializationService } from './services/form-initialization.service';

@NgModule({
  declarations: [
    AppComponent,
    FormComponent,
    PartDetailsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule
  ],
  providers: [FormInitializationService],
  bootstrap: [AppComponent]
})
export class AppModule { }