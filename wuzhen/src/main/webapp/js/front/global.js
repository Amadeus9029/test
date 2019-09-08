$(function () {
    var l = [], q, o, p, c, b,
        a = navigator.userAgent.match(/Android/i) || navigator.userAgent.match(/webOS/i) || navigator.userAgent.match(/iPhone/i) || navigator.userAgent.match(/iPad/i) || navigator.userAgent.match(/iPod/i);

    function g() {
        q = $(window).width();
        o = $(window).height();
        c = ($("footer").length) ? $("footer").offset().top : 0;
        if ($(".portfolio-detail-related-entry").length) {
            c = $(".portfolio-detail-related-entry").offset().top
        }
        if ($(".is-mobile").is(":visible")) {
            b = true
        } else {
            b = false
        }
        $(".page-height").css({height: o, "min-height": (o < 480) ? 480 : o});
        if (o <= 600) {
            $("body").addClass("min-height")
        } else {
            $("body").removeClass("min-height")
        }
        $(".rotate").each(function () {
            $(this).css({width: $(this).parent().height()})
        })
    }

    setTimeout(function () {
        $("#loader-wrapper").fadeOut()
    }, 2000);
    $(".input").each(function () {
        if ($(this).val() !== "") {
            $(this).parent().addClass("focus")
        }
    });
    if (a) {
        $("body").addClass("mobile")
    }
    g();
    $(window).load(function () {
        setTimeout(function () {
            d()
        }, 1000);
        e();
        $(".fixed-menu .nav-fix-a").each(function () {
            var r = $(".fixed-menu .nav-fix-a").index(this);
            if ($(this).attr("data-link") === window.location.hash) {
                $(".fixed-menu .nav-fix-a").eq(r).click()
            }
        });
        if ($(".baner-bg").length) {
            $("header").addClass("bg-header")
        }
        $("body").addClass("loaded");
        setTimeout(function () {
            setTimeout(function () {
                g();
                j()
            }, 0)
        }, 400);
        if ($(".lightbox").length > 0) {
            $(function () {
                var r = $(".lightbox").simpleLightbox({disableScroll: false})
            })
        }
    });

    function h() {
        g()
    }

    if (!a) {
        $(window).resize(function () {
            h()
        })
    } else {
        window.addEventListener("orientationchange", function () {
            h()
        }, false)
    }
    $(window).scroll(function () {
        j()
    });

    function j() {
        p = $(window).scrollTop();
        if (p > 30) {
            $("header").addClass("scrolled");
            $(".popup-container.right").addClass("scrolled")
        } else {
            $("header").removeClass("scrolled");
            $(".popup-container.right").removeClass("scrolled")
        }
    }

    function d() {
        var r = 0;
        $(".swiper-container").each(function () {
            var s = $(this);
            var t = "swiper-unique-id-" + r;
            s.addClass("swiper-" + t + " initialized").attr("id", t);
            s.find(".swiper-pagination").addClass("swiper-pagination-" + t);
            if (s.find(".swiper-button").length >= 1) {
                s.find(".swiper-button-prev").addClass("swiper-button-prev-" + t);
                s.find(".swiper-button-next").addClass("swiper-button-next-" + t)
            } else {
                if (s.parent().find(".swiper-button").length >= 1) {
                    s.parent().find(".swiper-button-prev").addClass("swiper-button-prev-" + t);
                    s.parent().find(".swiper-button-next").addClass("swiper-button-next-" + t)
                }
            }
            if (s.find(".swiper-slide").length <= 1) {
                $('.slider-click[data-pagination-rel="' + s.data("pagination-rel") + '"]').addClass("disabled")
            }
            var v = (s.data("slides-per-view")) ? s.data("slides-per-view") : 1,
                u = (s.data("loop")) ? parseInt(s.data("loop"), 10) : 0;
            if (v != "auto") {
                v = parseInt(v, 10)
            }
            l["swiper-" + t] = new Swiper(".swiper-" + t, {
                pagination: ".swiper-pagination-" + t,
                paginationClickable: true,
                nextButton: ".swiper-button-next-" + t,
                prevButton: ".swiper-button-prev-" + t,
                slidesPerView: v,
                autoHeight: (s.data("auto-height")) ? parseInt(s.data("auto-height"), 10) : 0,
                loop: u,
                autoplay: (s.data("autoplay")) ? parseInt(s.data("autoplay"), 10) : 0,
                centeredSlides: (s.data("center")) ? parseInt(s.data("center"), 10) : 0,
                breakpoints: (s.data("breakpoints")) ? {
                    767: {slidesPerView: parseInt(s.attr("data-xs-slides"), 10)},
                    991: {slidesPerView: parseInt(s.attr("data-sm-slides"), 10)},
                    1199: {slidesPerView: parseInt(s.attr("data-md-slides"), 10)}
                } : {},
                initialSlide: (s.data("ini")) ? parseInt(s.data("ini"), 10) : 0,
                watchSlidesProgress: true,
                speed: (s.data("speed")) ? parseInt(s.data("speed"), 10) : 500,
                parallax: (s.data("parallax")) ? parseInt(s.data("parallax"), 10) : 0,
                slideToClickedSlide: true,
                keyboardControl: true,
                mousewheelControl: (s.data("mousewheel")) ? parseInt(s.data("mousewheel"), 10) : 0,
                mousewheelReleaseOnEdges: true,
                spaceBetween: (s.data("space")) ? parseInt(s.data("space"), 10) : 0,
                direction: (s.data("direction")) ? s.data("direction") : "horizontal",
                onProgress: function (x, w) {
                    n(s, x, x.activeIndex)
                },
                onSlideChangeStart: function (x) {
                    var w = (u === 1) ? x.activeLoopIndex : x.activeIndex;
                    n(s, x, w)
                },
                onTransitionEnd: function (x) {
                    var w = (u === 1) ? x.activeLoopIndex : x.activeIndex;
                    if ($('.slider-click[data-pagination-rel="' + s.data("pagination-rel") + '"]').length) {
                        var y = $('.slider-click.left[data-pagination-rel="' + s.data("pagination-rel") + '"]'),
                            z = $('.slider-click.right[data-pagination-rel="' + s.data("pagination-rel") + '"]');
                        if (u != 1) {
                            if (w < 1) {
                                y.addClass("disabled")
                            } else {
                                y.removeClass("disabled").find(".left").text(w)
                            }
                            if (w + 2 > x.slides.length) {
                                z.addClass("disabled")
                            } else {
                                z.removeClass("disabled").find(".left").text(w + 2)
                            }
                            y.find(".preview-entry.active").removeClass("active");
                            y.find('.preview-entry[data-rel="' + (w - 1) + '"]').addClass("active");
                            z.find(".preview-entry.active").removeClass("active");
                            z.find('.preview-entry[data-rel="' + (w + 1) + '"]').addClass("active")
                        }
                    }
                }
            });
            l["swiper-" + t].update();
            r++
        });
        $(".swiper-container.swiper-control-top-js").each(function () {
            l["swiper-" + $(this).attr("id")].params.control = l["swiper-" + $(this).closest(".swipers-couple-wrapper").find(".swiper-control-bottom-js").attr("id")]
        });
        $(".swiper-container.swiper-control-bottom-js").each(function () {
            l["swiper-" + $(this).attr("id")].params.control = l["swiper-" + $(this).closest(".swipers-couple-wrapper").find(".swiper-control-top-js").attr("id")]
        })
    }

    function n(r, u, t) {
        if ($('.homepage-1-backgrounds[data-pagination-rel="' + r.data("pagination-rel") + '"]').length) {
            $(".homepage-1-backgrounds .entry.active").removeClass("active");
            $(".homepage-1-backgrounds .entry[data-rel=" + t + "]").addClass("active")
        }
        if ($('.slider-click-label[data-pagination-rel="' + r.data("pagination-rel") + '"]').length) {
            $('.slider-click-label[data-pagination-rel="' + r.data("pagination-rel") + '"]').removeClass("active prev next");
            $('.slider-click-label[data-pagination-rel="' + r.data("pagination-rel") + '"][data-slide-to="' + t + '"]').addClass("active")
        }
        if ($('.pagination-slider-wrapper[data-pagination-rel="' + r.data("pagination-rel") + '"]').length) {
            var s = $('.pagination-slider-wrapper[data-pagination-rel="' + r.data("pagination-rel") + '"]');
            s.css({top: (-1) * parseInt(s.find(".active").attr("data-slide-to"), 10) * s.parent().height()})
        }
    }

    var k = 1;
    $(".all-slides").text($(".left-right .swiper-container.my-bg-swiper .swiper-slide").length);
    $(".prev-slide").text(k);
    $(".next-slide").text(k + 1);
    $(".slider-click.left").on("click", function () {
        if ($(this)[0].hasAttribute("data-pagination-rel")) {
            l["swiper-" + $('.swiper-container[data-pagination-rel="' + $(this).data("pagination-rel") + '"]').attr("id")].slidePrev();
            $(this).find(".preview-entry").removeClass("active")
        }
    });
    $(".slider-click.right").on("click", function () {
        if ($(this)[0].hasAttribute("data-pagination-rel")) {
            l["swiper-" + $('.swiper-container[data-pagination-rel="' + $(this).data("pagination-rel") + '"]').attr("id")].slideNext();
            $(this).find(".preview-entry").removeClass("active")
        }
    });
    $(".slider-click-label").on("click", function () {
        l["swiper-" + $('.swiper-container[data-pagination-rel="' + $(this).data("pagination-rel") + '"]').attr("id")].slideTo($(this).data("slide-to"))
    });
    $(".open-overlay").on("click", function () {
        var r = $(this);
        $('.overlay[data-rel="' + $(this).data("rel") + '"]').addClass("active");
        if ($(this).hasClass("open-video")) {
            setTimeout(function () {
                $('.overlay[data-rel="' + r.data("rel") + '"] iframe').attr("src", r.data("src"))
            }, 500)
        }
        if (a) {
            setTimeout(function () {
                $("html").addClass("overflow-hidden")
            }, 500)
        }
    });
    $(".overlay .button-close").on("click", function () {
        $(this).closest(".video-popup").find("iframe").attr("src", "");
        if ($(".overlay.active").length === 1) {
            $("html").removeClass("overflow-hidden")
        }
        $(this).closest(".overlay").removeClass("active")
    });
    $(".input").on("focus", function () {
        $(this).parent().addClass("focus")
    });
    $(".input").on("blur", function () {
        if ($(this).val() === "") {
            $(this).parent().removeClass("focus")
        }
    });
    var i = 0;
    var f = 0;
    $(".nav-a").on("click", function () {
        f = $(".nav-a").index(this);
        $(".nav-a").removeClass("active");
        $(this).addClass("active");
        $(".my-multi-swiper").removeClass("active");
        $(".my-multi-swiper").eq(f).addClass("active");
        return false
    });
    $(".drop-menu").on("click", function () {
        if ($(this).hasClass("active")) {
            $(this).removeClass("active");
            $(".drop-list").removeClass("active");
            $("#portfolioCaret").addClass("fa-angle-down");
            $("#portfolioCaret").addClass("fa-angle-up")
        } else {
            $(this).addClass("active");
            $(".drop-list").addClass("active");
            $("#portfolioCaret").addClass("fa-angle-up");
            $("#portfolioCaret").removeClass("fa-angle-down")
        }
        return false
    });
    $(".nav-a").on("click", function () {
        if ($(".drop-menu").hasClass("active")) {
            $(".drop-menu").html($(this).html() + ' <i id="portfolioCaret" class="fa fa-angle-up" aria-hidden="true"></i></a>')
        }
    });
    $(".hamburger-icon").on("click", function () {
        $(".overlay-wrapper").addClass("active")
    });
    $(".btn-close").on("click", function () {
        $(".overlay-wrapper").removeClass("active")
    });
    $(".dropdown-plus span").on("click", function () {
        $(this).toggleClass("clicked").parent().find("ul").slideToggle(300)
    });
    if ($(".select-box").length > 0) {
        $(".select-box").SumoSelect()
    }
    /*if ($("#slider-range").length > 0) {
        $(function () {
            $("#slider-range").slider({
                range: true, min: 10, max: 500, values: [10, 300], slide: function (r, s) {
                    $("#amount").val("$" + s.values[0] + " - $" + s.values[1])
                }
            });
            $("#amount").val("$" + $("#slider-range").slider("values", 0) + " - $" + $("#slider-range").slider("values", 1))
        })
    }*/
    $(".shop .view-btn").click(function () {
        $(".view-btn").removeClass("active");
        $(this).addClass("active");
        if ($(".view-btn-2").hasClass("active")) {
            $(".prod-item-wrapper").addClass("view-2")
        } else {
            $(".prod-item-wrapper").removeClass("view-2")
        }
    });
    $(".category-shop").on("click", function () {
        var r = $(this).attr("data-text");
        $(".category-toggle span").text(r);
        $(".category-shop").removeClass("active");
        $(this).addClass("active");
        return false
    });
    $(".category-toggle").on("click", function () {
        $(".category").slideToggle(300);
        if ($(this).hasClass("active")) {
            $(this).removeClass("active")
        } else {
            $(this).addClass("active")
        }
    });
    $(".hamburger-icon-2").on("click", function () {
        if ($(this).hasClass("active")) {
            $(this).removeClass("active")
        } else {
            $(this).addClass("active")
        }
        if ($(".header-style-2").hasClass("active")) {
            $(".header-style-2").removeClass("active bg-white")
        } else {
            $(".header-style-2").addClass("active bg-white")
        }
    });
    $(".open-popup").on("click", function () {
        $(".popup-content").removeClass("active");
        $('.popup-wrapper, .popup-content[data-rel="' + $(this).data("rel") + '"]').addClass("active");
        $("html").addClass("overflow-hidden");
        return false
    });
    $(".popup-wrapper .button-close, .popup-wrapper .layer-close").on("click", function () {
        $(".popup-wrapper, .popup-content").removeClass("active");
        $("html").removeClass("overflow-hidden");
        setTimeout(function () {
            $(".ajax-popup").remove()
        }, 300);
        return false
    });
    $(".shop .detail-item .description .color span").on("click", function () {
        $(".shop .detail-item .description .color span").removeClass("active");
        $(this).addClass("active")
    });
    var m = 0;
    $(".tab-menu").on("click", function () {
        if ($(this).hasClass("active") || m) {
            return false
        }
        m = 1;
        var u = $(this).closest(".tabs-block"), t = u.find(".tab-menu"), s = u.find(".tab-entry"), r = t.index(this);
        s.filter(":visible").fadeOut(function () {
            s.eq(r).fadeIn(function () {
                m = 0
            })
        });
        t.removeClass("active");
        $(this).addClass("active");
        setTimeout(function () {
            d()
        }, 1000)
    });
    $(".basket.open-popup").on("click", function () {
        $(".basket.open-popup").addClass("active");
        $(".popup-wrapper").addClass("z-lower");
        return false
    });
    $(".popup-wrapper .button-close, .popup-wrapper .layer-close").on("click", function () {
        if ($(".basket.open-popup").hasClass("active")) {
            $(".basket.open-popup").removeClass("active")
        }
        if ($(".popup-wrapper").hasClass("z-lower")) {
            $(".popup-wrapper").removeClass("z-lower")
        }
        return false
    });
    /*$(".img-preview img").on("click", function () {
        var r = $(this).attr("data-src");
        console.log(r)
        $(".img-main").attr("src", r)
    });*/
    $(".shop").on("click",".img-preview img",function () {
        var r = $(this).attr("data-src");
        console.log(r)
        $(".img-main").attr("src", r)
    });
    $(".scroll-down").on("click", function () {
        var r = $(".baner-bg").next().offset().top - $("header").height();
        $("body, html").animate({scrollTop: r}, 800)
    });



    function e() {
        if ($(".izotope-container").length) {
            var r = $(".izotope-container");
            r.isotope({itemSelector: ".item", layoutMode: "masonry", masonry: {columnWidth: ".grid-sizer"}});
            $(".sorting-menu li").on("click", function () {
                if ($(this).hasClass("active")) {
                    return false
                }
                $(this).parent().find(".active").removeClass("active");
                $(this).addClass("active");
                var s = $(this).attr("data-filter");
                r.isotope({filter: s});
                var t = $(".sorting-menu .button-drop a span");
                t.text($(this).data("name"));
                return false
            })
        }
    }

    $(".sorting-menu .button-drop").on("click", function () {
        var r = $(".sorting-menu ul");
        if ($(this).hasClass("active")) {
            $(this).removeClass("active");
            r.removeClass("active")
        } else {
            $(this).addClass("active");
            r.addClass("active")
        }
        return false
    });
    if ($(window).width() < 992) {
        $(".txt-replace").on("click", function () {
            $(this).parent().find(".tabs-mobile-style").slideToggle(325)
        });
        $(".tabs-mobile-style .tab-menu").on("click", function () {
            $(".txt-replace span").text($(this).text());
            $(this).parent().slideUp(325)
        })
    }
});