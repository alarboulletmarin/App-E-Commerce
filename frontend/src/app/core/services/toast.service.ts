import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root',
})
export class ToastService {
  constructor(private toastr: ToastrService) {}

  showToast(message: string): void {
    this.toastr.warning(message, 'Attention', {
      positionClass: 'toast-top-right',
    });
  }
}
