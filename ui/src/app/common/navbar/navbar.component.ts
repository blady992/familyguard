import {Component, OnInit} from '@angular/core';

// declare var $: any;

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  ngOnInit() {
  //   $('.title-bar').on('sticky.zf.stuckto:top', function () {
  //     $(this).addClass('shrink');
  //   }).on('sticky.zf.unstuckfrom:top', function () {
  //     $(this).removeClass('shrink');
  //   });
  }

}
