import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root',
})
export class ToastService {
  constructor(private toastr: ToastrService) {}

  /**
   * Displays a warning toast message with the given message and position.
   * @param message The message to display in the toast.
   */
  showToast(message: string): void {
    this.toastr.warning(message, 'Attention', {
      positionClass: 'toast-top-right',
    });
  }
}
