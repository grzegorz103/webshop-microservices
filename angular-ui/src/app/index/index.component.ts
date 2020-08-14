import {Component, OnInit} from '@angular/core';
import {animate, style, transition, trigger} from "@angular/animations";

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.scss'],
  animations: [
    trigger('fade', [
      transition('void => *', [
        style({opacity: 0}),
        animate(1000, style({opacity: 1}))
      ]),
      transition('* => void', [
        animate(1000, style({opacity: 0}))
      ])
    ])

  ]
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
