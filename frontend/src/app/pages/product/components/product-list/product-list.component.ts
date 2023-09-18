import { Component, OnInit } from '@angular/core';
import { ProductShort } from 'src/app/core/models/product.model';
import { ProductService } from 'src/app/core/services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.sass'],
})
export class ProductListComponent implements OnInit {
  products: ProductShort[] = [];
  allProducts: ProductShort[] = [];
  selectedCategoryIds: number[] = [];

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.listProducts();
  }

  listProducts() {
    this.productService.getProductList().subscribe(
      (data) => {
        this.allProducts = data;
        this.filterProducts();
      },
      (error) => {
        console.error('Error fetching products:', error);
      }
    );
  }

  onCategorySelected(categoryIds: number[]): void {
    this.selectedCategoryIds = categoryIds;
    this.filterProducts();
  }

  filterProducts(): void {
    this.products = this.selectedCategoryIds.length
      ? this.allProducts.filter((product) =>
          this.selectedCategoryIds.includes(product.category.id)
        )
      : this.allProducts;
  }
}
