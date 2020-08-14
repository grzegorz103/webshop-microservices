import {Component, OnInit} from '@angular/core';
import {EventService} from "../../services/event.service";
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-event-log',
  templateUrl: './event-log.component.html',
  styleUrls: ['./event-log.component.scss']
})
export class EventLogComponent implements OnInit {

  events: any[];

  constructor(private eventService: EventService) {
  }

  ngOnInit() {
    this.fetchEvents();
  }

  fetchEvents() {
    this.eventService.getAll()
      .subscribe(res => this.events = res);
  }

  exportPdf() {
    window.open(environment.apiUrl + 'events/events/report', '_blank');
  }

}
