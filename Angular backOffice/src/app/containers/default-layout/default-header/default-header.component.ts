import { Component, Input } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ClassToggleService, HeaderComponent } from '@coreui/angular';

@Component({
  selector: 'app-default-header',
  templateUrl: './default-header.component.html',
})
export class DefaultHeaderComponent extends HeaderComponent {

  @Input() sidebarId: string = "sidebar";

  public newMessages = new Array(4)
  public newTasks = new Array(5)
  public newNotifications = new Array(5)

  constructor(private classToggler: ClassToggleService) {
    super();
  }
  navigateToOtherProject(): void {
    // Replace 'other-project-url' with the actual URL of the other project
    window.location.href = 'http://localhost:55681/chat';
  }
}
