import { Component, OnInit } from '@angular/core';
import { ProductService } from '../core/services/product.service';
import { ProductShort } from '../core/models/product.model';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.sass'],
})
export class NavbarComponent implements OnInit {
  isConnected: any;
  searchTerm: string = '';
  allProducts: ProductShort[] = [];
  searchResults: ProductShort[] = [];

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.productService.getProductList().subscribe((products) => {
      this.allProducts = products;
    });
  }

  onSearchChange(): void {
    if (this.searchTerm.length > 0) {
      this.searchResults = this.allProducts.filter((product) =>
        product.name.toLowerCase().includes(this.searchTerm.toLowerCase())
      );
    } else {
      this.searchResults = [];
    }
  }
}
