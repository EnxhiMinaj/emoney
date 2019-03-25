import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FindJobComponent} from './find-job.component';
import {FindJobRoutingModule} from "./find-job-routing.module";
import {JobService} from "../services/job.service";

@NgModule({
  imports: [
    CommonModule,
    FindJobRoutingModule
  ],
  declarations: [FindJobComponent],
  providers: [JobService]
})
export class FindJobModule { }
