import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.scss']
})
export class IndexComponent implements OnInit {

  carouselInfo = [
    {url: '/assets/2.jpg', title: 'Webshop online', info: 'Best webshop in the web'},
    {url: '/assets/1.jpg', title: 'Webshop online', info: 'Built in microservices architecture!'},
  ];

  constructor() {
  }

  ngOnInit() {
  }

}
