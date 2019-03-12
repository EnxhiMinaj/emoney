import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WalletComponent } from './wallet.component';
import {WalletRoutingModule} from "./wallet-routing.module";

@NgModule({
  imports: [
    CommonModule,
    WalletRoutingModule
  ],
  declarations: [WalletComponent]
})
export class WalletModule { }
