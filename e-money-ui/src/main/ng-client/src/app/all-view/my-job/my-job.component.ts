import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {CREATE_JOB_URL, ICREDIT_URL} from "../../core/utility/navigation-url";
import {ResponseModel} from "../../core/lib/model/response.model";
import {JobService} from "../app-services/job.service";
import {JobModel} from "../models/job.model";
import {JobTransactionModel} from "../models/job-transaction.model";
import {MatDialog, MatSnackBar} from "@angular/material";
import {PictureDialogComponent} from "../../core/lib/components/picture-dialog/picture-dialog.component";
import {ApiConstant} from "../../core/utility/api.constant";
import {ConfirmDialogComponent} from "../../core/lib/components/confirm-dialog/confirm-dialog.component";

@Component({
  selector: 'app-my-job',
  templateUrl: './my-job.component.html',
  styleUrls: ['./my-job.component.scss']
})
export class MyJobComponent implements OnInit {
  myJobsList: JobModel[];

  constructor(private _router: Router,
              private _jobService: JobService,
              private _snackBar: MatSnackBar,
              private _dialog: MatDialog
  ) {
    this.myJobsList = [];
  }

  ngOnInit() {
    this._jobService.getMyJobs().then((res: ResponseModel) => {
      if (res.responseStatus) {
        this.myJobsList = res.result;
      } else {
        this.myJobsList = [];
      }
    });
  }

  createJob(){
    let finalUrl = "/"+ICREDIT_URL+  "/" + CREATE_JOB_URL;
    this._router.navigateByUrl(finalUrl);
  }
  getAllApplicantsByJob(job: JobModel) {
    this._jobService.getAllApplicant(job.id).then((res: ResponseModel) => {
      job.approvedStatus = true;
      job.pendingStatus = true;
      if (res.responseStatus) {
        job.appliedJobsList = res.result;
        job.appliedJobsList.forEach(function(applicant) {
          if (applicant.status === "APPROVED") {
            job.approvedStatus = false;
          } else if (applicant.status === "APPLIED") {
            job.pendingStatus = false;
          }
        });
      }
    });
  }

  acceptApplicant(applicant: JobTransactionModel, job: JobModel) {
    this._jobService.acceptApplicant(applicant.jobId, applicant.applicantId).then((res: ResponseModel) => {
      if (res.responseStatus) {
        applicant.status = 'APPROVED';
        job.approvedStatus = true;
        job.pendingStatus = true;
        job.appliedJobsList.forEach(function (applicant) {
          if (applicant.status === "APPROVED") {
            job.approvedStatus = false;
          } else if (applicant.status === "APPLIED") {
            job.pendingStatus = false;
          }
        });
      }
      this._snackBar.open(res.message, "OK", {
        duration: 6000,
        verticalPosition: 'top'
      });
    });
  }

  declineApplicant(applicant: JobTransactionModel, job: JobModel) {
    this._jobService.declineApplicant(applicant.jobId, applicant.applicantId).then((res: ResponseModel) => {
      if (res.responseStatus) {
        applicant.status = 'REJECTED';
        job.approvedStatus = true;
        job.pendingStatus = true;
        job.appliedJobsList.forEach(function (applicant) {
          if (applicant.status === "APPROVED") {
            job.approvedStatus = false;
          } else if (applicant.status === "APPLIED") {
            job.pendingStatus = false;
          }
        });
      }
      this._snackBar.open(res.message, "OK", {
        duration: 6000,
        verticalPosition: 'top'
      });
    });
  }

  getQRCode(job: JobModel) {
    this.openDialog(job);
  }

  cancelJob(job: JobModel) {
    const dialogRef = this._dialog.open(ConfirmDialogComponent, {
      width: '350px',
      data: {title: "Confirm", content: "Do you really want to cancel this job?"}
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        this.confirmCancelJob(job);
      }

    });
  }

  confirmCancelJob(job: JobModel) {
    this._jobService.cancelJob(job.id).then((res: ResponseModel) => {
      if (res.responseStatus) {
        this.ngOnInit();
      }
      this._snackBar.open(res.message, "OK", {
        duration: 6000,
        verticalPosition: 'top'
      });
    });
  }

  openDialog(job): void {
    let finalApi = ApiConstant.IMAGE_DISPLAY + 'JOB/' + `${job.qrFileName}`;
    const dialogRef = this._dialog.open(PictureDialogComponent, {
      width: '350px',
      maxWidth: '85vw',
      data: {title: "QR CODE: " + job.jobTitle, content: finalApi}
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      return;
    });
  }


}
