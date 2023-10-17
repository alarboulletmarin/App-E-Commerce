import { Injectable } from '@angular/core';
import { IconDefinition } from '@fortawesome/free-solid-svg-icons';
import { TranslateService } from '@ngx-translate/core';
import { APP_CONSTANTS } from 'src/app/app.constant';

@Injectable({
  providedIn: 'root',
})
export class NavbarService {
  private faIcons = APP_CONSTANTS.faIcon;
  public faIconTheme: IconDefinition = this.faIcons.dark_theme;

  constructor(private translateService: TranslateService) {}

  /**
   * Sets the language to be used by the application and saves it in local storage.
   * @param language The language code to be used (e.g. 'en', 'fr', 'es')
   * @returns void
   */
  public useLanguage(language: string): void {
    this.translateService.use(language);
    localStorage.setItem('selectedLanguage', language);
  }

  /**
   * Returns the current language selected by the user, or 'fr' if no language is selected.
   * @returns {string} The current language selected by the user.
   */
  public getCurrentLanguage(): string {
    return localStorage.getItem('selectedLanguage') || 'fr';
  }

  /**
   * Toggles the theme of the application between light and dark mode.
   * @returns The FontAwesome icon definition for the new theme.
   */
  public toggleTheme(): IconDefinition {
    const body = document.body;
    if (body.classList.contains('dark-theme')) {
      body.classList.remove('dark-theme');
      body.classList.add('light-theme');
      localStorage.setItem('theme', 'light-theme');
      return this.faIcons.dark_theme;
    } else {
      body.classList.remove('light-theme');
      body.classList.add('dark-theme');
      localStorage.setItem('theme', 'dark-theme');
      return this.faIcons.light_theme;
    }
  }

  /**
   * Returns the current theme stored in local storage, or 'dark-theme' if no theme is found.
   * @returns {string} The current theme.
   */
  public getCurrentTheme(): string {
    return localStorage.getItem('theme') || 'dark-theme';
  }
}
