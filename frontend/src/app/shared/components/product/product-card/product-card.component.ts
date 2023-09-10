import { Component, Input, OnInit } from '@angular/core';
import { ProductShort } from 'src/app/core/models/product.model';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.sass'],
})
export class ProductCardComponent implements OnInit {
  @Input() product!: ProductShort;
  ngOnInit(): void {}
}
