import {Component, OnInit} from '@angular/core';
import {AddressService} from "../../services/address.service";

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.scss']
})
export class AddressComponent implements OnInit {

  address: any;

  constructor(private addressService: AddressService) {
  }

  ngOnInit() {
    this.getAddress();
  }

  getAddress() {
    this.addressService.getUserAddress().subscribe(res =>this.address = res);
  }

  update() {
    this.addressService.update(this.address)
      .subscribe(res => alert('Updated'));
  }

}
