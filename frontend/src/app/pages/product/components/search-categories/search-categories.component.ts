import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Category } from 'src/app/core/models/category.model';
import { CategoryService } from 'src/app/core/services/category.service';

@Component({
  selector: 'app-search-categories',
  templateUrl: './search-categories.component.html',
  styleUrls: ['./search-categories.component.sass'],
})
export class SearchCategoriesComponent implements OnInit {
  @Output() categorySelected: EventEmitter<number[]> = new EventEmitter<
    number[]
  >();

  public selectedCategoryIds: number[] = [];

  public categories: Category[] = [];

  constructor(private categoryService: CategoryService) {}

  ngOnInit(): void {
    this.categoryService.getCategoryList().subscribe((categories) => {
      this.categories = categories;
    });
  }

  onSelectCategory(event: any, categoryId: number): void {
    if (event.target.checked) {
      this.selectedCategoryIds.push(categoryId);
    } else {
      const index = this.selectedCategoryIds.indexOf(categoryId);
      if (index !== -1) {
        this.selectedCategoryIds.splice(index, 1);
      }
    }
    this.categorySelected.emit(this.selectedCategoryIds);
  }
}
