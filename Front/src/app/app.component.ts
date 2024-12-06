import { Component, Renderer2, OnDestroy, OnInit, Inject, PLATFORM_ID } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { Subscription } from 'rxjs';
import { isPlatformBrowser } from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'Proyecto';
  private routerSubscription: Subscription;

  constructor(
    private renderer: Renderer2, 
    private router: Router,
    @Inject(PLATFORM_ID) private platformId: Object
  ) { 
    this.routerSubscription = new Subscription();
  }

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.routerSubscription = this.router.events.subscribe(event => {
        if (event instanceof NavigationEnd) {
          if (event.url === '/lobby'||event.url === '/admin') { 
            this.setAltBackground();
          } else {
            this.setDefaultBackground();
          }
        }
      });
    }
  }

  ngOnDestroy(): void {
    if (this.routerSubscription) {
      this.routerSubscription.unsubscribe();
    }
  }

  setAltBackground(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.renderer.removeClass(document.body, 'default-background');
      this.renderer.addClass(document.body, 'alt-background');
    }
  }

  setDefaultBackground(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.renderer.removeClass(document.body, 'alt-background');
      this.renderer.addClass(document.body, 'default-background');
    }
  }
}
