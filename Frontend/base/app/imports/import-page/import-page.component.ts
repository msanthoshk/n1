import { Component, Input, OnInit, inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BaseAppConstants } from '@baseapp/app-constants.base';
import { AppUtilBaseService } from '@baseapp/app-util.base.service';
import { TranslateService } from '@ngx-translate/core';
import { MessageService } from 'primeng/api';
import { Observable, Subject, Subscription, from, of } from 'rxjs';
import { ImportsService } from '../imports.service';
import { UploaderService } from '@baseapp/upload-attachment.service';
import { AppConstants } from '@app/app-constants';
import { AppGlobalService } from '@baseapp/app-global.service';
interface ImportObject {
  tableName : string,
  importName : string,
  possibleInputFiles : any, 
  allowDatechangeonImport : string, 
  allowDateTimechangeonImport : string, 
  allowNumberchangeonImport : string, 
  dateformats : any,
  numberformats : any,
  roles : any
  
};
@Component({
  selector: 'app-import-page',
  templateUrl: './import-page.component.html',
  styleUrls: ['./import-page.component.scss']
})
export class ImportPageComponent implements OnInit {

  @Input() ImportConfig:any;
  @Input() fromListPage:any;
  @Input() fromTableName:any;
  id: any;
  
  public importsService = inject(ImportsService)
  public uploaderService = inject(UploaderService)
  public appUtilBaseService = inject(AppUtilBaseService)
  public translateService = inject(TranslateService)
  public messageService = inject(MessageService)
  public router = inject(Router)
  public appGlobalService = inject(AppGlobalService)

  importGroup: FormGroup = new FormGroup({
    tableName: new FormControl('', []),
    templateName: new FormControl('', [Validators.required]),
    FileType: new FormControl('', []),
    attachment: new FormControl(''),
    GoogleSheetLink: new FormControl('', []),
    DateFormat: new FormControl('', []),
    DateTimeFormat: new FormControl('', []),
    NumberFormat: new FormControl('', [])
  });
  actionBarConfig: any = [];
  conditionalActions: any = {
    disableActions: [],
    hideActions: []
  }
  subscription!: Subscription;
   dateformat:any = [];
   datetimeformat:any= [];
   numberformat:any = [];
   displayUpload:any;
   inValidFields:any = {};
   updatedConfig:any[] =[];
   filterConfig:any[] =[]; 
   subscriptions: Subscription[] = [];
  //  isPageLoading:boolean = false;

   isMobile: boolean = BaseAppConstants.isMobile;
   isSearchFocused:boolean = false;
   showBreadcrumb = BaseAppConstants.showBreadcrumb;
   pageViewTitle: string = 'Add Imports';
   selectedtablename:any;
   selectedValues:any = {};
   templateDownloadLink: string ="";
   enableDownloadLink: boolean =false;
   allImportConfig: any;
   currentUserRoles:any; 
   fieldConfig:any={
    tableName:{label:"TABLE_NAME"},
    templateName:{label:"TEMPLATE_NAME"},
    FileType:{label:"FILE_TYPE"},
    attachment:{label:"ATTACHMENT"},
    GoogleSheetLink:{label:"GOOGLE_SHEET_LINK"},
    DateFormat:{label:"DATE_FORMAT"},
    DateTimeFormat:{label:"DATE_TIME_FORMAT"},
    NumberFormat:{label:"NUMBER_FORMAT"}
    }

    selectedImportType(event: any){
      this.importGroup.reset();
        this.importGroup.controls["tableName"].setValue(this.selectedtablename);
      this.importGroup.controls["attachment"].addValidators([Validators.required]);
      this.selectedValues.selectedImportFileType = event.value.fileTypes;
      this.importGroup.controls["templateName"].setValue(event.value.name);
      this.selectedFileType( this.selectedValues.selectedImportFileType[0])

       this.selectedValues.allowDatechangeonImport= event.value.modifyDateFmt;
      if( this.selectedValues.allowDatechangeonImport == true){
        this.dateformat = [];
        this.dateformat.push(event.value.dateFormats)
        this.importGroup.controls["DateFormat"].addValidators([Validators.required]);
      }else{
        this.importGroup.controls["DateFormat"].clearValidators();
      }
      this.importGroup.controls["DateFormat"].updateValueAndValidity();

       this.selectedValues.allowDateTimechangeonImport= event.value.modifDateTimeFmt;
      if( this.selectedValues.allowDateTimechangeonImport == true){
        this.datetimeformat = [];
        this.datetimeformat.push(event.value.timeformats)
        this.importGroup.controls["DateTimeFormat"].addValidators([Validators.required]);
      }else{
        this.importGroup.controls["DateTimeFormat"].clearValidators();
      }
      this.importGroup.controls["DateTimeFormat"].updateValueAndValidity();

       this.selectedValues.allowNumberchangeonImport= event.value.modifyNumFmt;
      if( this.selectedValues.allowNumberchangeonImport == true){
        this.numberformat = [];
        this.numberformat.push(event.value.numberFormats)
        this.importGroup.controls["NumberFormat"].addValidators([Validators.required]);
      }else{
        this.importGroup.controls["NumberFormat"].clearValidators();
      }
      this.importGroup.controls["NumberFormat"].updateValueAndValidity();
      

    }
    //selecting the File type radio buttons
    selectedFileType(filetype:any){
      this.importGroup.controls["attachment"].setValue(null);
      this.selectedValues.selectedType = filetype;
      this.importGroup.controls["FileType"].setValue(this.selectedValues.selectedType)
      this.getTemplateLink();
    }

    //Download Link for Template
    getTemplateLink(){
      this.enableDownloadLink =false;
      const params = {
        tableName : this.importGroup.controls["tableName"].value,
        templateName : this.importGroup.controls["templateName"].value,
        FileType:  this.selectedValues.selectedType
      }
      const dataSubscription = this.importsService.getTemplateLink (params).subscribe((res:any) => {
        console.log(res)
        this.templateDownloadLink = AppConstants.attachmentBaseURL + res.attachmentId;
        this.enableDownloadLink =true;
    });

    }
 
    formErrors:any = {};
    showMessage(config:any){
      this.messageService.clear();
      this.messageService.add(config);
  }
    checkValidation() {
    const finalArr: string[] = [];
    this.formErrors = {};
    this.inValidFields = {};
      if (!this.appUtilBaseService.validateNestedForms(this.importGroup, this.formErrors, finalArr, this.inValidFields,this.fieldConfig)) {
        if (finalArr.length) {
          this.showMessage({ severity: 'error', summary: 'Error', detail: this.appUtilBaseService.createNotificationList(finalArr), sticky: true });
        }
        return false;
      }
      else {
        return true;
      }
    }
  
    //selecting table name
    selectedTable(event: any) {
      this.enableDownloadLink = false;
      this.selectedValues = {}
      this.selectedtablename = event.value.tableName;
      this.ImportConfig=event.value.imports;
      const key={ value: this.ImportConfig[0]}
      this.selectedImportType(key);
      console.log(this.importGroup)
    }
    selectedImport(event:any){
      this.selectedImportType(event);
      
    }
    ImportAuth(roles:any, config:any){
      this.filterConfig = []
      for (let i of config){
        const found = (roles.some((r: any)=> i.roles.includes(r)) || i.roles.includes("All") || false);
        if (found){
          this.filterConfig.push(i);
        }
      }
      return this.filterConfig;
    }

    checkAuth(){
    this.updatedConfig = [];
    this.updatedConfig = this.ImportAuth(this.currentUserRoles,this.ImportConfig);
    }
    checkedAllImportConfig: any = [];
    checkallAuth(){
         for (let i of this.currentImpConfig){
        i.imports = this.ImportAuth(this.currentUserRoles,i.imports)
        if (i.imports.length){
          this.checkedAllImportConfig.push(i);
        }
      }
      this.currentImpConfig =this.checkedAllImportConfig;
    }
 
    onSelectattachment(event:any){
      this.importGroup.controls.attachment.setValue(event.currentFiles[0]);
      this.appUtilBaseService.setImagePreview(event.files).subscribe((res:any) =>{
          this.importGroup.controls.attachment.setValue(res.slice(0));
      });
  }
  currentImpConfig: any[] = [];
  ngOnInit(): void {
    this.currentUserRoles = this.appGlobalService.getCurrentUserData().userRoles;
    const importSubscription = this.importsService.getImportConfig().subscribe((data: any) => {
      this.allImportConfig = data;
      console.log(  this.allImportConfig);
    });
    for(const prop in this.allImportConfig) {
      this.currentImpConfig.push({tableName:prop,imports:this.allImportConfig[prop]})
      console.log(this.currentImpConfig);
    }
    console.log(this.currentImpConfig);

    this.subscriptions.push(importSubscription);
    if(this.fromListPage == true){
      this.selectedtablename = this.fromTableName; //from json
      this.ImportConfig = this.findImpConfig(this.currentImpConfig,this.selectedtablename);
      console.log(this.ImportConfig)
      this.checkAuth();
      const key={ value: this.ImportConfig[0]}
      this.selectedImportType(key);

    }
    else{
      this.importGroup.controls["tableName"].addValidators([Validators.required]);
      this.checkallAuth()
      
    }
  }
  findImpConfig(list:any,tablename:any){
    // const filteredImp = list[tablename];
    const filteredImp= list.find((x: { tableName: string; }) =>x.tableName == tablename).imports;
    return filteredImp;
  }


 
    //on click of start import
   initiateImport(isToastNotNeeded ?: boolean) {
    if (this.checkValidation()) {

      const data = {
        tableName :  this.selectedtablename,
        templateName : this.importGroup.controls["templateName"].value,
        FileType: this.importGroup.controls["FileType"].value,
        rappitImport: this.importGroup.controls["attachment"].value,
      };

      const method = this.id ? 'update' : 'create';
    const requestedObj = data;
        this.messageService.clear();
        const attachmentFields = ['rappitImport'];
        const splittedData = this.appUtilBaseService.splitFileAndData(data, attachmentFields);
  
        if (Object.keys(splittedData.files).length > 0) {
        
        const saveSubscription = this.uploadAttachmentsandSaveData(requestedObj, splittedData).subscribe((res: any) => {
            this.onAfterSave(res, data, method, isToastNotNeeded);
          }, (err: any) => { 
          })
          this.subscriptions.push(saveSubscription);
        }
      }
  
    }
       
         onAfterSave(res: any, data: any, method: string, isToastNotNeeded?: boolean) {
     
      if (!isToastNotNeeded) {
        this.showMessage({ severity: 'success', summary: '', detail: this.translateService.instant('RECORD_SAVED_SUCCESSFULLY') });
      }
    }
  
  uploadAttachmentsandSaveData(data: any, splittedData: any): Observable<any> {
      const subject$ = new Subject();
  
      const completeReq = (resData: any,) => {
        resData ? subject$.next(resData) : subject$.error(resData);
        subject$.complete();
      }
      if (!this.id) {
         const saveSubscription =  this.importsService.create({}).subscribe(
           (          createdData: any) => {
            const data = { ...splittedData.data, ...createdData };
            splittedData.data = data;
            this.id = data.sid;
            if (splittedData.files) {
              this.updateData(splittedData).subscribe(
                updatedData => completeReq(updatedData),
                err => completeReq(null)
              );
            }
          },
           (          err: any) => completeReq(null))
          this.subscriptions.push(saveSubscription);
      } 

       const saveSubscription =  this.updateData(splittedData).subscribe(
          updatedOrg => completeReq(updatedOrg),
          err => completeReq(null)
        );
        this.subscriptions.push(saveSubscription);
  
      return subject$.asObservable()
    }
  
  updateData(splittedData: any) {
  const subject$ = new Subject();
  const updateSubscription =  this.saveFiles(splittedData).subscribe((dataToUpdate: any) => {
    dataToUpdate.data.importId = this.id;
    this.importsService.update(dataToUpdate.data).subscribe(
      (res: unknown) => {
       subject$.next(res);
       subject$.complete();
     },
      (err: any) => {
       subject$.error(null);
       subject$.complete();
     }
   )
  });
  this.subscriptions.push(updateSubscription);
  return subject$.asObservable()
  }
  
  saveFiles(splittedData: any) {
      if (splittedData.files) {
        const detailsform = null;
        return new Observable(observer => {
          this.uploaderService.saveAddedFiles(splittedData, this.id, detailsform).subscribe((res: any) => {
            let fData: any = {};
            for (const key in res.dataToResend) {
              if (res.dataToResend[key] instanceof Array) {
                const tempArr = res.dataToResend[key].flat();
                fData[key] = (tempArr.filter((n: any, i: any) => tempArr.indexOf(n) === i)).filter(Boolean);
              } else {
                fData[key] = [res.dataToResend[key]];
              }
              fData[key] = this.appUtilBaseService.removeImagePreviewProperties(fData[key]);
            }
            
            const finalData = { data: { ...splittedData.data, ...fData } };
            if (res.error) {
              const errorArr: any = [];
              Object.keys(res.error).forEach((key) => {
                errorArr.push("Failed to upload " + key);
              })
              if (errorArr.length > 0)
              this.showMessage({ severity: 'error', summary: 'Error', detail: this.appUtilBaseService.createNotificationList(errorArr) });
            }
            observer.next(finalData);
            observer.complete();
          }, (err: any) => {
            observer.error(err);
          });
        });
      } else {
        return of(splittedData)
      }
    }

}
