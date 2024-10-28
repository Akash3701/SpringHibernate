document.addEventListener('DOMContentLoaded', () => {
    const container = document.querySelector('.image-container');
    const scrollableImages = document.querySelector('.image-slider');
  
    container.addEventListener('mousemove', (event) => {
      const containerWidth = container.offsetWidth;
      const scrollWidth = scrollableImages.scrollWidth;
      const mouseX = event.clientX - container.offsetLeft;
      const maxScroll = scrollWidth - containerWidth;
      const scrollValue = (mouseX / containerWidth) * maxScroll;
  
      scrollableImages.style.transform = `translateX(-${scrollValue}px)`;
    });
  })
  
  document.addEventListener('DOMContentLoaded', () => {
      const carousel = document.querySelector('.carousel-content');
      const carouselItems = document.querySelectorAll('.carousel-content img, .carousel-content video');
      const prevButton = document.querySelector('.carousel-control-left');
      const nextButton = document.querySelector('.carousel-control-right');
  
      let index = 0;
  
      // Function to update carousel position
      function updateCarousel() {
          carousel.style.transform = `translateX(-${index * 100}%)`;
      }
  
      // Function to move to the next item
      function nextItem() {
          index = (index + 1) % carouselItems.length;
          updateCarousel();
      }
  
      // Function to move to the previous item
      function prevItem() {
          index = (index - 1 + carouselItems.length) % carouselItems.length;
          updateCarousel();
      }
  
      // Set up event listeners for buttons
      nextButton.addEventListener('click', nextItem);
      prevButton.addEventListener('click', prevItem);
  
      // Auto-slide function
      setInterval(nextItem, 16000); 
  });
  
  
  
  document.addEventListener('DOMContentLoaded', () => {
    const topPicksSlider = document.querySelector('.top-picks-slider');
    const newArrivalsSlider = document.querySelector('.new-arrivals-slider');
  
    // Function to move the slider
    function moveSlider(sliderClass, direction) {
      const slider = document.querySelector(`.${sliderClass}-slider`);
      const slideWidth = slider.querySelector('.slide').offsetWidth; // Get the width of one slide
      const slides = slider.querySelectorAll('.slide').length;
      let currentPosition = parseInt(getComputedStyle(slider).transform.split(',')[4]) || 0;
  
      let newPosition = currentPosition + (direction * slideWidth);
      if (newPosition > 0) {
        newPosition = -((slides - 1) * slideWidth); // Loop to the end
      } else if (newPosition < -((slides - 1) * slideWidth)) {
        newPosition = 0; // Loop to the start
      }
  
      slider.style.transform = `translateX(${newPosition}px)`;
    }
  
    // Event listeners for buttons
    document.querySelector('.top-picks .slider-control-left').addEventListener('click', () => moveSlider('top-picks', -1));
    document.querySelector('.top-picks .slider-control-right').addEventListener('click', () => moveSlider('top-picks', 1));
  
    document.querySelector('.new-arrivals .slider-control-left').addEventListener('click', () => moveSlider('new-arrivals', -1));
    document.querySelector('.new-arrivals .slider-control-right').addEventListener('click', () => moveSlider('new-arrivals', 1));
  
    // Auto-slide function for continuous loop
    setInterval(() => moveSlider('top-picks', 1), 5000); // Adjust time as needed
    setInterval(() => moveSlider('new-arrivals', 1), 5000); // Adjust time as needed
  });
  


// JavaScript to handle the modal functionality
let modal = document.querySelector(".modal");
let openModalBtn = document.querySelector(".open-modal-btn");
let closeModalBtn = document.querySelector(".close");

// Ensure no duplicate declarations
if (modal && openModalBtn && closeModalBtn) {
    openModalBtn.addEventListener("click", () => {
        modal.style.display = "block";
    });

    closeModalBtn.addEventListener("click", () => {
        modal.style.display = "none";
    });

    // Close the modal if the user clicks outside of the modal content
    window.addEventListener("click", (event) => {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    });
}

// Ensure no duplicate declarations for form functionality
let signup = document.querySelector(".modal-signup");
let login = document.querySelector(".modal-login");
let slider = document.querySelector(".modal-slider");
let formSection = document.querySelector(".modal-form-section");

if (signup && login && slider && formSection) {
    signup.addEventListener("click", () => {
        slider.classList.add("moveslider");
        formSection.classList.add("modal-form-section-move");
    });

    login.addEventListener("click", () => {
        slider.classList.remove("moveslider");
        formSection.classList.remove("modal-form-section-move");
    });
}


document.addEventListener("DOMContentLoaded", function () {
	  const brandsSlider = document.querySelector('.brands-on-board .slider');
	  const slideWidth = 300; // Width of each slide
	  let position = 0;

	  function startInfiniteScroll() {
	    // Move the slider to the left continuously
	    position -= 1; // Adjust speed by changing the decrement value

	    if (position <= -slideWidth * 5) { // Check when to reset (number of original slides * width)
	      position = 0; // Reset to the original position
	    }

	    brandsSlider.style.transform = `translateX(${position}px)`;
	    requestAnimationFrame(startInfiniteScroll);
	  }

	  startInfiniteScroll(); // Start the continuous loop
	});