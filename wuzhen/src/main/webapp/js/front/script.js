(function (a) {
    a(".animsition").animsition({
        loadingClass: "preloader",
        loadingInner: '<div class="spinner"><div class="double-bounce1"></div><div class="double-bounce2"></div></div>'
    });
    new WOW({mobile: false}).init();
    a(".a-nav-toggle").on("click", function () {
        if (a("html").hasClass("body-menu-opened")) {
            a("html").removeClass("body-menu-opened").addClass("body-menu-close")
        } else {
            a("html").addClass("body-menu-opened").removeClass("body-menu-close")
        }
    });
    a(".navbar-nav .dropdown").on({
        mouseenter: function () {
            a(this).find(".dropdown-menu").show()
        }, mouseleave: function () {
            a(this).find(".dropdown-menu").hide()
        }
    });
    var g = a(window).height();
    if (a(".a-affix").length) {
        a(window).scroll(function () {
            var m = a(window).scrollTop();
            if (m >= g) {
                a(".header").addClass("header-affix")
            } else {
                a(".header").removeClass("header-affix")
            }
        })
    }
    a(window).on("load", function () {
        if (a(".a-grid").length) {
            a(".a-grid").isotope({itemSelector: ".grid-item"});
            a(".a-grid-filter a").on("click", function () {
                a(this).parents(".a-grid-filter").find(".active").removeClass("active");
                a(this).parent().addClass("active");
                var m = a(this).attr("data-filter");
                a(".a-grid").isotope({filter: m})
            })
        }
        if (a(".a-grid-line").length) {
            a(".a-grid-line").isotope({itemSelector: ".grid-item", layoutMode: "fitRows"});
            a(".a-grid-filter a").on("click", function () {
                a(this).parents(".a-grid-filter").find(".active").removeClass("active");
                a(this).parent().addClass("active");
                var m = a(this).attr("data-filter");
                a(".a-grid-line").isotope({filter: m})
            })
        }
    });
    if (a(".a-progressbar").length) {
        function i() {
            a(".a-progressbar .progress-bar:in-viewport").each(function () {
                if (!a(this).hasClass("animated")) {
                    a(this).addClass("animated");
                    a(this).width(a(this).attr("aria-valuenow") + "%")
                }
            })
        }

        i();
        a(window).on("scroll", function () {
            i()
        })
    }
    if (a(".a-counter").length) {
        function b() {
            a(".a-counter:in-viewport").each(function () {
                if (!a(this).hasClass("animated")) {
                    a(this).addClass("animated");
                    var n = a(this);
                    a({count: 0}).animate({count: n.attr("data-value")}, {
                        duration: 2000,
                        easing: "swing",
                        step: function m() {
                            var o = Math.ceil(this.count);
                            n.text(o.toLocaleString("en-IN", {maximumSignificantDigits: 3}))
                        }
                    })
                }
            })
        }

        b();
        a(window).on("scroll", function () {
            b()
        })
    }
    if (a(".a-timer").length) {
        a(".a-timer").countdown("2020/10/10", function (m) {
            a(this).html(m.strftime('<div class="timer-item"><span>%D</span> Days</div><div class="divider"></div><div class="timer-item"><span>%H</span> Hours</div><div class="divider"></div><div class="timer-item"><span>%M</span> Minutes</div><div class="divider"></div><div class="timer-item"><span>%S</span> Seconds</div>'))
        })
    }
    a(".a-change-bg").on("mouseover", function () {
        var m = a(".a-change-bg").index(this);
        a(".slide-bg-list .slide-bg").removeClass("active").eq(m).addClass("active")
    });
    a(".a-minimal a").on({
        mouseenter: function () {
            a("body").addClass("dark-horizontal");
            var m = a(this).index();
            a(".promo-minimal-hover .minimal-item").eq(m).addClass("visible");
            a(".promo-minimal .minimal-item").addClass("over")
        }, mouseleave: function () {
            a("body").removeClass("dark-horizontal");
            var m = a(this).index();
            a(".promo-minimal-hover .minimal-item").eq(m).removeClass("visible");
            a(".promo-minimal .minimal-item").removeClass("over")
        }
    });
    a(".flash-item-nav a, .a-scroll").bind("click", function (n) {
        var m = a(this);
        a("html, body").stop().animate({scrollTop: a(m.attr("href")).offset().top - 100}, 1000);
        n.preventDefault()
    });
    a(".a-video-play").on("click", function () {
        if (a(this).hasClass("active")) {
            a(".video-container").fadeOut();
            a(this).removeClass("active").html('<i class="icon ion-ios-play"></i>')
        } else {
            a(".video-container").fadeIn();
            a(this).addClass("active").html('<i class="icon ion-ios-pause"></i>')
        }
    });

    function k() {
        var n = a(window).width();
        var m = a(window).height();
        a(".a-video").each(function () {
            var p = a(this).data("vimeo-width");
            var o = a(this).data("vimeo-height");
            if (m / n > o / p) {
                a(this).find("iframe").css({width: "500%", height: "100%"})
            } else {
                a(this).find("iframe").css({width: "100%", height: "500%"})
            }
        })
    }

    k();
    a(window).resize(function () {
        k()
    });
    if (a(".a-project-carousel").length) {
        var h = a(".a-project-carousel");
        h.owlCarousel({
            smartSpeed: 750,
            dots: false,
            nav: true,
            autoplay: true,
            loop: true,
            margin: 5,
            autoplayHoverPause: true,
            navText: ['<div class="arrow"><div class="arrow-top"></div><div class="arrow-bottom"></div></div>', '<div class="arrow"><div class="arrow-top"></div><div class="arrow-bottom"></div></div>'],
            items: 1
        });
        h.trigger("stop.owl.autoplay");
        a(window).bind("scroll", function (m) {
            if (a(".a-play").hasClass("animated")) {
                h.trigger("play.owl.autoplay", [7000])
            }
        });
        a(".project-carousel-3d .owl-next").on({
            mouseenter: function () {
                a(".project-carousel-3d").addClass("move-left")
            }, mouseleave: function () {
                a(".project-carousel-3d").removeClass("move-left")
            }
        });
        a(".project-carousel-3d .owl-prev").on({
            mouseenter: function () {
                a(".project-carousel-3d").addClass("move-right")
            }, mouseleave: function () {
                a(".project-carousel-3d").removeClass("move-right")
            }
        })
    }
    if (a(".a-article-promo-carousel").length) {
        a(".a-article-promo-carousel").owlCarousel({
            smartSpeed: 750,
            dots: true,
            nav: true,
            autoplay: true,
            loop: true,
            autoplayHoverPause: true,
            navText: ['<div class="arrow"><div class="arrow-top"></div><div class="arrow-bottom"></div></div>', '<div class="arrow"><div class="arrow-top"></div><div class="arrow-bottom"></div></div>'],
            items: 1
        })
    }
    if (a(".a-reviews-carousel").length) {
        a(".a-reviews-carousel").owlCarousel({smartSpeed: 750, dots: true, margin: 30, nav: false, items: 1})
    }
    if (a(".a-photo-carousel").length) {
        a(".a-photo-carousel").owlCarousel({
            items: 3,
            smartSpeed: 750,
            margin: 8,
            autoplayHoverPause: true,
            dots: true,
            nav: true,
            navText: ['<div class="arrow"><div class="arrow-top"></div><div class="arrow-bottom"></div></div>', '<div class="arrow"><div class="arrow-top"></div><div class="arrow-bottom"></div></div>'],
            dotData: false,
            responsive: {0: {nav: false, items: 1}, 900: {nav: true, items: 3}}
        })
    }

    function j() {
        var n = a(window).width();
        var m = a(window).height();
        if (n < 768) {
            a(".video-text-container").addClass("owl-carousel owl-theme");
            a(".video-text-container").owlCarousel({smartSpeed: 750, dots: true, nav: false, items: 1});
            a(".video-text-container").on("translated.owl.carousel", function (o) {
                a(this).find(".video-text-item.is-active").removeClass("is-active");
                a(this).find(".owl-item.active .video-text-item").addClass("is-active")
            })
        } else {
            a(".owl-carousel .video-text-item.is-active").removeClass("is-active");
            a(".video-text-container").removeClass("owl-carousel owl-theme").trigger("destroy.owl.carousel")
        }
        a(".video-item").each(function () {
            var q = a(this).data("vimeo-width");
            var o = a(this).data("vimeo-height");
            var p = a(this).data("portrait-index");
            if (m / n > o / q) {
                a(".video-item[data-portrait-index=" + p + "] .iframe-css").html('<style>.video-item[data-portrait-index="' + p + '"] iframe {width: 500%; height:100%;}</style>')
            } else {
                a(".video-item[data-portrait-index=" + p + "] .iframe-css").html('<style>.video-item[data-portrait-index="' + p + '"] iframe {width: 102%; height:500%;}</style>')
            }
        })
    }

    j();
    a(window).resize(function () {
        j()
    });
    a(".circular-name, .circle-dot").on({
        mouseenter: function () {
            var m = a(this).data("portrait-index");
            a(".circular-name").removeClass("is-init");
            a(".circular-name[data-portrait-index=" + m + "]").addClass("is-init")
        }, mouseleave: function () {
            a(".circular-name").removeClass("is-init");
            if (!a(".circular-name").hasClass("is-active")) {
                a(".circular-name").addClass("is-init")
            }
        }
    });
    var d = 2644;
    var f = [9999, 2108, 1580, 1050, 530, 0];
    var e = function (m, n) {
        if (n == "forward") {
            for (var o = 0; o <= f.length - 1; o++) {
                if (m >= f[o]) {
                    return o
                }
            }
        } else {
            for (var o = f.length - 1; o > 0; o--) {
                if (m <= f[o]) {
                    return o
                }
            }
        }
    };
    var c = function c(n, o, p) {
        var m = n <= o ? "backward" : "forward";
        a({n: n}).animate({n: o}, {
            easing: "linear", duration: 1000, step: function (q) {
                a("." + p).css({"stroke-dashoffset": q | 0});
                var r = e(q, m);
                if (p == "st1") {
                    setTimeout(function () {
                        a(".circle-dot[data-portrait-index=" + r + "] .circle-outside").css({opacity: m == "forward" ? 1 : 0.2}, {duration: 500})
                    }, 300)
                }
            }
        })
    };
    var l = function () {
        a(this).removeClass("is-active").css({opacity: "1", "z-index": "100"}).find("iframe").remove()
    };
    a(".circular-name, .circle-dot").on("click", function () {
        if (a(this).hasClass("is-active")) {
            return
        }
        var m = a(this).data("portrait-index");
        var p = a(".video-item[data-portrait-index=" + m + "]");
        var o = p.attr("data-vimeo");
        var n = p.attr("data-vimeo-start");
        a(".circular-name").removeClass("is-active");
        a(".video-item.is-active").css("z-index", "500").animate({opacity: 0}, 4000, l);
        p.addClass("is-active").append('<iframe src="https://player.vimeo.com/video/' + o + '?title=0&byline=0&portrait=0&autoplay=1&autopause=0&muted=1&background=1" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>');
        j();
        a(".circle-dot.is-active, .circular-name.is-active, .video-text-item.is-active").removeClass("is-active");
        a(".circle-dot[data-portrait-index=" + m + "], .circular-name[data-portrait-index=" + m + "], .video-text-item[data-portrait-index=" + m + "]").addClass("is-active");
        a(".circle-dot[data-portrait-index=" + m + "] .circle-outside").delay(1000).animate({opacity: 1});
        c(d, f[m], "st1");
        d = f[m]
    });
    a(window).on("load", function () {
        a(".video-item:first-child").addClass("is-active");
        setTimeout(function () {
            a('.video-text-item[data-portrait-index="0"]').addClass("is-active");
            c(2644, 0, "st0")
        }, 300)
    })
}($));