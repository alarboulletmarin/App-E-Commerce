import { Component, OnInit } from '@angular/core';
import { ProductService } from '../core/services/product.service';
import { ProductShort } from '../core/models/product.model';
import { TranslateService } from '@ngx-translate/core';
import { APP_CONSTANTS } from '../app.constant';
import { IconDefinition } from '@fortawesome/free-solid-svg-icons';
import { AuthService } from '../core/services/auth.service';
import { NavbarService } from '../core/services/navbar.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.sass'],
})
export class NavbarComponent implements OnInit {
  public faIcons = APP_CONSTANTS.faIcon;
  public isConnected: boolean = false;
  public searchTerm: string = '';
  public allProducts: ProductShort[] = [];
  public searchResults: ProductShort[] = [];
  public showLanguages: boolean = false;
  public languages: string[] = ['fr', 'en'];
  public faIconTheme: IconDefinition = this.faIcons.dark_theme;
  public userRole: string = '';

  constructor(
    private productService: ProductService,
    private translateService: TranslateService,
    private authService: AuthService,
    private navbarService: NavbarService
  ) {}

  ngOnInit(): void {
    // Check if the user is connected and get his role
    // to display the correct navbar
    this.authService.currentToken.subscribe((token) => {
      this.isConnected = !!token;
      if (token) {
        this.userRole = this.authService.hasRole('ROLE_ADMIN')
          ? 'admin'
          : 'user';
      }
    });

    // Get the list of products to be used for the search bar
    this.productService.getProductList().subscribe((products) => {
      this.allProducts = products;
    });

    // Set the language to be used by the application
    const savedLanguage = this.navbarService.getCurrentLanguage();
    this.translateService.use(savedLanguage);

    // Set the theme to be used by the application
    const body = document.body;
    const savedTheme = this.navbarService.getCurrentTheme();
    body.classList.add(savedTheme);
    if (savedTheme === 'dark-theme') {
      this.faIconTheme = this.faIcons.light_theme;
    } else {
      this.faIconTheme = this.faIcons.dark_theme;
    }
  }

  public onSearchChange(): void {
    if (this.searchTerm.length > 0) {
      this.searchResults = this.allProducts.filter((product) =>
        product.name.toLowerCase().includes(this.searchTerm.toLowerCase())
      );
    } else {
      this.searchResults = [];
    }
  }

  public useLanguage(lang: string): void {
    this.navbarService.useLanguage(lang);
    this.showLanguages = false;
  }

  public toggleTheme() {
    this.faIconTheme = this.navbarService.toggleTheme();
  }

  public logout(): void {
    this.authService.logout();
    this.isConnected = false;
  }

  public isAdmin(): boolean {
    return this.userRole === 'admin';
  }

  public isUser(): boolean {
    return this.userRole === 'user';
  }
}
