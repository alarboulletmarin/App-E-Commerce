import { Component, OnInit } from '@angular/core';
import { ProductService } from '../core/services/product.service';
import { ProductShort } from '../core/models/product.model';
import { TranslateService } from '@ngx-translate/core';
import { APP_CONSTANTS } from '../app.constant';
import { IconDefinition } from '@fortawesome/free-solid-svg-icons';
import { AuthService } from '../core/services/auth.service';

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
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.authService.currentToken.subscribe((token) => {
      this.isConnected = !!token;
      if (token) {
        this.userRole = this.authService.hasRole('ROLE_ADMIN')
          ? 'admin'
          : 'user';
      }
    });

    this.productService.getProductList().subscribe((products) => {
      this.allProducts = products;
    });
    const savedLanguage = localStorage.getItem('selectedLanguage');
    if (savedLanguage) {
      this.translateService.use(savedLanguage);
    } else {
      this.translateService.use(this.languages[0]);
    }

    const savedTheme = localStorage.getItem('theme');
    const body = document.body;

    if (savedTheme) {
      body.classList.add(savedTheme);
      if (savedTheme === 'dark-theme') {
        this.faIconTheme = this.faIcons.light_theme;
      } else {
        this.faIconTheme = this.faIcons.dark_theme;
      }
    } else {
      body.classList.add('dark-theme');
      this.faIconTheme = this.faIcons.light_theme;
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
    this.translateService.use(lang);
    localStorage.setItem('selectedLanguage', lang);
    this.showLanguages = false;
  }

  public toggleTheme() {
    const body = document.body;
    if (body.classList.contains('dark-theme')) {
      body.classList.remove('dark-theme');
      body.classList.add('light-theme');
      this.faIconTheme = this.faIcons.dark_theme;
      localStorage.setItem('theme', 'light-theme');
    } else {
      body.classList.remove('light-theme');
      body.classList.add('dark-theme');
      this.faIconTheme = this.faIcons.light_theme;
      localStorage.setItem('theme', 'dark-theme');
    }
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
