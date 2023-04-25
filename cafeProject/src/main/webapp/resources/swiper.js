const swiper = new Swiper('.swiper', {
  direction: 'horizontal',		
  slidesPerView: 1,			
  spaceBetween: 16,
  observer: true,
  observeParents: true,
  effect: 'fade',
  fadeEffect: {
    crossFade: true
  },
  autoplay: {
    delay: 5000,
  },
});