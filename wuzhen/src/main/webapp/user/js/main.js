(function(a){a(function(){
    a("#loading").delay(350).fadeOut("slow")
    a(".section-wrapper").addClass("fadeOutDown").removeClass("fadeInUp");
    a(".home-section").find(".section-wrapper").addClass("fadeInUp").removeClass("fadeOutDown");
    a(".navigation ul li a").on("click",function(c){c.preventDefault();
        var b=a(this).attr("href");var d=a(this).parent("li").attr("class");
        a(".section").removeClass("active");
        a(".section-wrapper ").removeClass("fadeInUp").addClass("fadeOutDown");a(".overlay").removeClass("home-overlay").addClass(d+"-overlay");
        a(".close-section").addClass("active");
        a(b).addClass("active");a(b).find(".section-wrapper").addClass("fadeInUp").removeClass("fadeOutDown");return false});a(".close-section").on("click",function(b){var c=a(".section.active").attr("id");a(this).removeClass("active");a(".section").removeClass("active");a(".section-wrapper").removeClass("fadeInUp").addClass("fadeOutDown");a(".overlay").removeClass(c+"-overlay").addClass("home-overlay");a(".home-section").addClass("active");a(".home-section").find(".section-wrapper").removeClass("fadeOutDown").addClass("fadeInUp");return false});a(".chart").easyPieChart({barColor:"#fff",trackColor:"transparent",scaleColor:"transparent",lineCap:"round",lineWidth:3,size:120,animate:{duration:3000,enabled:true}});a(".skill-slider").owlCarousel({items:3,autoplay:false,loop:true,nav:true,navText:['<i class="ti-angle-left"></i>','<i class="ti-angle-right"></i>'],dots:false,responsive:{0:{items:2,},480:{items:3,},768:{items:4,},992:{items:3,}}});a(".testimonial-slider").owlCarousel({items:1,autoplay:false,loop:true,nav:true,margin:0,navText:['<i class="ti-angle-left"></i>','<i class="ti-angle-right"></i>'],dots:false,});a(".section, .page").perfectScrollbar();a(".portfolio-hover").magnificPopup({type:"image",mainClass:"mfp-with-zoom",})})})(jQuery);
