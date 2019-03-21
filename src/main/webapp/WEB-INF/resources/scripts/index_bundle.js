!function (n) {
    var t = {};

    function e(r) {
        if (t[r]) return t[r].exports;
        var o = t[r] = {i: r, l: !1, exports: {}};
        return n[r].call(o.exports, o, o.exports, e), o.l = !0, o.exports
    }

    e.m = n, e.c = t, e.d = function (n, t, r) {
        e.o(n, t) || Object.defineProperty(n, t, {enumerable: !0, get: r})
    }, e.r = function (n) {
        "undefined" != typeof Symbol && Symbol.toStringTag && Object.defineProperty(n, Symbol.toStringTag, {value: "Module"}), Object.defineProperty(n, "__esModule", {value: !0})
    }, e.t = function (n, t) {
        if (1 & t && (n = e(n)), 8 & t) return n;
        if (4 & t && "object" == typeof n && n && n.__esModule) return n;
        var r = Object.create(null);
        if (e.r(r), Object.defineProperty(r, "default", {
            enumerable: !0,
            value: n
        }), 2 & t && "string" != typeof n) for (var o in n) e.d(r, o, function (t) {
            return n[t]
        }.bind(null, o));
        return r
    }, e.n = function (n) {
        var t = n && n.__esModule ? function () {
            return n.default
        } : function () {
            return n
        };
        return e.d(t, "a", t), t
    }, e.o = function (n, t) {
        return Object.prototype.hasOwnProperty.call(n, t)
    }, e.p = "", e(e.s = 4)
}([function (n, t) {
    n.exports = function (n) {
        var t = [];
        return t.toString = function () {
            return this.map(function (t) {
                var e = function (n, t) {
                    var e = n[1] || "", r = n[3];
                    if (!r) return e;
                    if (t && "function" == typeof btoa) {
                        var o = function (n) {
                            return "/*# sourceMappingURL=data:application/json;charset=utf-8;base64," + btoa(unescape(encodeURIComponent(JSON.stringify(n)))) + " */"
                        }(r), i = r.sources.map(function (n) {
                            return "/*# sourceURL=" + r.sourceRoot + n + " */"
                        });
                        return [e].concat(i).concat([o]).join("\n")
                    }
                    return [e].join("\n")
                }(t, n);
                return t[2] ? "@media " + t[2] + "{" + e + "}" : e
            }).join("")
        }, t.i = function (n, e) {
            "string" == typeof n && (n = [[null, n, ""]]);
            for (var r = {}, o = 0; o < this.length; o++) {
                var i = this[o][0];
                "number" == typeof i && (r[i] = !0)
            }
            for (o = 0; o < n.length; o++) {
                var a = n[o];
                "number" == typeof a[0] && r[a[0]] || (e && !a[2] ? a[2] = e : e && (a[2] = "(" + a[2] + ") and (" + e + ")"), t.push(a))
            }
        }, t
    }
}, function (n, t, e) {
    var r = {}, o = function (n) {
        var t;
        return function () {
            return void 0 === t && (t = n.apply(this, arguments)), t
        }
    }(function () {
        return window && document && document.all && !window.atob
    }), i = function (n) {
        var t = {};
        return function (n, e) {
            if ("function" == typeof n) return n();
            if (void 0 === t[n]) {
                var r = function (n, t) {
                    return t ? t.querySelector(n) : document.querySelector(n)
                }.call(this, n, e);
                if (window.HTMLIFrameElement && r instanceof window.HTMLIFrameElement) try {
                    r = r.contentDocument.head
                } catch (n) {
                    r = null
                }
                t[n] = r
            }
            return t[n]
        }
    }(), a = null, s = 0, l = [], f = e(7);

    function c(n, t) {
        for (var e = 0; e < n.length; e++) {
            var o = n[e], i = r[o.id];
            if (i) {
                i.refs++;
                for (var a = 0; a < i.parts.length; a++) i.parts[a](o.parts[a]);
                for (; a < o.parts.length; a++) i.parts.push(b(o.parts[a], t))
            } else {
                var s = [];
                for (a = 0; a < o.parts.length; a++) s.push(b(o.parts[a], t));
                r[o.id] = {id: o.id, refs: 1, parts: s}
            }
        }
    }

    function p(n, t) {
        for (var e = [], r = {}, o = 0; o < n.length; o++) {
            var i = n[o], a = t.base ? i[0] + t.base : i[0], s = {css: i[1], media: i[2], sourceMap: i[3]};
            r[a] ? r[a].parts.push(s) : e.push(r[a] = {id: a, parts: [s]})
        }
        return e
    }

    function u(n, t) {
        var e = i(n.insertInto);
        if (!e) throw new Error("Couldn't find a style target. This probably means that the value for the 'insertInto' parameter is invalid.");
        var r = l[l.length - 1];
        if ("top" === n.insertAt) r ? r.nextSibling ? e.insertBefore(t, r.nextSibling) : e.appendChild(t) : e.insertBefore(t, e.firstChild), l.push(t); else if ("bottom" === n.insertAt) e.appendChild(t); else {
            if ("object" != typeof n.insertAt || !n.insertAt.before) throw new Error("[Style Loader]\n\n Invalid value for parameter 'insertAt' ('options.insertAt') found.\n Must be 'top', 'bottom', or Object.\n (https://github.com/webpack-contrib/style-loader#insertat)\n");
            var o = i(n.insertAt.before, e);
            e.insertBefore(t, o)
        }
    }

    function d(n) {
        if (null === n.parentNode) return !1;
        n.parentNode.removeChild(n);
        var t = l.indexOf(n);
        t >= 0 && l.splice(t, 1)
    }

    function g(n) {
        var t = document.createElement("style");
        if (void 0 === n.attrs.type && (n.attrs.type = "text/css"), void 0 === n.attrs.nonce) {
            var r = function () {
                0;
                return e.nc
            }();
            r && (n.attrs.nonce = r)
        }
        return h(t, n.attrs), u(n, t), t
    }

    function h(n, t) {
        Object.keys(t).forEach(function (e) {
            n.setAttribute(e, t[e])
        })
    }

    function b(n, t) {
        var e, r, o, i;
        if (t.transform && n.css) {
            if (!(i = t.transform(n.css))) return function () {
            };
            n.css = i
        }
        if (t.singleton) {
            var l = s++;
            e = a || (a = g(t)), r = x.bind(null, e, l, !1), o = x.bind(null, e, l, !0)
        } else n.sourceMap && "function" == typeof URL && "function" == typeof URL.createObjectURL && "function" == typeof URL.revokeObjectURL && "function" == typeof Blob && "function" == typeof btoa ? (e = function (n) {
            var t = document.createElement("link");
            return void 0 === n.attrs.type && (n.attrs.type = "text/css"), n.attrs.rel = "stylesheet", h(t, n.attrs), u(n, t), t
        }(t), r = function (n, t, e) {
            var r = e.css, o = e.sourceMap, i = void 0 === t.convertToAbsoluteUrls && o;
            (t.convertToAbsoluteUrls || i) && (r = f(r));
            o && (r += "\n/*# sourceMappingURL=data:application/json;base64," + btoa(unescape(encodeURIComponent(JSON.stringify(o)))) + " */");
            var a = new Blob([r], {type: "text/css"}), s = n.href;
            n.href = URL.createObjectURL(a), s && URL.revokeObjectURL(s)
        }.bind(null, e, t), o = function () {
            d(e), e.href && URL.revokeObjectURL(e.href)
        }) : (e = g(t), r = function (n, t) {
            var e = t.css, r = t.media;
            r && n.setAttribute("media", r);
            if (n.styleSheet) n.styleSheet.cssText = e; else {
                for (; n.firstChild;) n.removeChild(n.firstChild);
                n.appendChild(document.createTextNode(e))
            }
        }.bind(null, e), o = function () {
            d(e)
        });
        return r(n), function (t) {
            if (t) {
                if (t.css === n.css && t.media === n.media && t.sourceMap === n.sourceMap) return;
                r(n = t)
            } else o()
        }
    }

    n.exports = function (n, t) {
        if ("undefined" != typeof DEBUG && DEBUG && "object" != typeof document) throw new Error("The style-loader cannot be used in a non-browser environment");
        (t = t || {}).attrs = "object" == typeof t.attrs ? t.attrs : {}, t.singleton || "boolean" == typeof t.singleton || (t.singleton = o()), t.insertInto || (t.insertInto = "head"), t.insertAt || (t.insertAt = "bottom");
        var e = p(n, t);
        return c(e, t), function (n) {
            for (var o = [], i = 0; i < e.length; i++) {
                var a = e[i];
                (s = r[a.id]).refs--, o.push(s)
            }
            n && c(p(n, t), t);
            for (i = 0; i < o.length; i++) {
                var s;
                if (0 === (s = o[i]).refs) {
                    for (var l = 0; l < s.parts.length; l++) s.parts[l]();
                    delete r[s.id]
                }
            }
        }
    };
    var m = function () {
        var n = [];
        return function (t, e) {
            return n[t] = e, n.filter(Boolean).join("\n")
        }
    }();

    function x(n, t, e, r) {
        var o = e ? "" : r.css;
        if (n.styleSheet) n.styleSheet.cssText = m(t, o); else {
            var i = document.createTextNode(o), a = n.childNodes;
            a[t] && n.removeChild(a[t]), a.length ? n.insertBefore(i, a[t]) : n.appendChild(i)
        }
    }
}, function (n, t) {
    n.exports = function (n) {
        return "string" != typeof n ? n : (/^['"].*['"]$/.test(n) && (n = n.slice(1, -1)), /["'() \t\n]/.test(n) ? '"' + n.replace(/"/g, '\\"').replace(/\n/g, "\\n") + '"' : n)
    }
}, function (n, t, e) {
    n.exports = e.p + "/Gamma/resources/assets/854fbb661911b7f8f2039b64ff3d1cc6.png"
}, function (n, t, e) {
    "use strict";
    e.r(t);
    e(5), e(8), e(10), e(14), e(18), e(20), e(21)
}, function (n, t, e) {
    var r = e(6);
    "string" == typeof r && (r = [[n.i, r, ""]]);
    var o = {hmr: !0, transform: void 0, insertInto: void 0};
    e(1)(r, o);
    r.locals && (n.exports = r.locals)
}, function (n, t, e) {
    var r = e(2);
    (t = n.exports = e(0)(!1)).push([n.i, "@import url(https://fonts.googleapis.com/css?family=Open+Sans:600|PT+Sans);", ""]), t.push([n.i, ':root {\n    --color-fg: rgb(220, 220, 220);\n    --color-bg: rgba(0, 0, 0, 0.2)\n}\n:root {\n    --p-font-text: "PT Sans", sans-serif;\n    --p-font-weight: 400;\n    --p-font-color: rgb(220, 220, 220);\n    --p-font-color: var(--color-fg);\n\n    --h-font-text: "Open Sans", sans-serif;\n    --h-font-weight: 600;\n    --h-line-height: 1.5;\n    --h-font-color: rgb(220, 220, 220);\n    --h-font-color: var(--color-fg);\n}\n\n/* HEADER WRAPPER */\nheader {\n    left: 0;\n    position: relative;\n    top: 0;\n    min-height: 20vh;\n    margin-left: 17em;\n}\n.nav {\n    position: absolute;\n    height: 50px;\n    bottom: 0;\n}\nul {\n    list-style: none;\n    padding: 0;\n}\n\nth {\n    background-color: rgb(10, 10, 10);\n    height: 50px;\n    margin: 0;\n}\ntd {\n    font-family: "PT Sans", sans-serif;\n    font-family: var(--p-font-text);\n}\nli {\n    display: inline-block;\n}\nli a {\n    font-size: 0.9em;\n    font-family: "PT Sans", sans-serif;\n    font-family: var(--p-font-text);\n    padding: 15px 25px 15px;\n    background-color: rgb(10, 10, 10);\n    width: 70px;\n    text-decoration: none;\n    color: rgb(220, 220, 220);\n    text-shadow: 1px 1px 2px black;\n    border-top-right-radius: 5px;\n    border-top-left-radius: 5px;\n}\nli a:hover {\n    background-color: rgb(20, 20, 20);\n}\n#active {\n    background-image: url(' + r(e(3)) + ');\n    text-shadow: none;\n}\n\n.translate {\n    width: 30px;\n}\n\n/* SERVER STATUS */\n.server-wrapper {\n    position: fixed;\n    width: 15em;\n}\n.server-status {\n    width: 90%;\n    padding: 1em;\n    background-color: rgb(30, 30, 30);\n    margin-left: 5%;\n    margin-bottom: 5%;\n    border-radius: 5px;\n}\n.server-icon {\n    height: 50px;\n    width: 50px !important;\n    margin: 0 10px 0 0 !important;\n    float: left;\n}\n.server-info h3 {\n    line-height: 1em;\n    font-size: 0.9em;\n}\n.server-play {\n    background-color: rgb(20, 20, 20);\n    height: 3em;\n    width: 100%;\n}\n.server-play h3 {\n    text-align: center;\n    line-height: 2.5em;\n}\n.server-play a {\n    text-decoration: none;\n    height: 100%;\n    width: 100%;\n    color: rgb(250, 250, 250);\n}\n\n.server-play:hover {\n    background-color: rgb(250, 250, 250);\n    transition: 0.2s;\n}\n.server-play h3:hover {\n    color: rgb(20, 20, 20);\n    transition: 0.2s;\n}\n/* CONTENT WRAPPER */\n\n\n.article {\n    background-color: rgba(0, 0, 0, 0.2);\n    background-color: var(--color-bg);\n    width: 90%;\n    margin: 0 auto;\n    background-attachment: fixed;\n    background-position: top center;\n    background-repeat: no-repeat;\n}\n.article-margin {\n    margin-top: 20px;\n}\n.article label {\n    color: rgb(220, 220, 220);\n    color: var(--color-fg);\n    line-height: 30px;\n}\n@media screen and (max-width: 1430px) {\n    .flex-content {\n        width: 100vw;\n        margin: 0;\n    }\n    header {\n        margin: 0;\n    }\n}\n\n\n\n/* MAIN STYLING */\nbody {\n    margin: 0;\n    padding: 0;\n    overflow: visible;\n}\np {\n    font-family: "PT Sans", sans-serif;\n    font-family: var(--p-font-text);\n    font-weight: 400;\n    font-weight: var(--p-font-weight);\n    color: rgb(220, 220, 220);\n    color: var(--p-font-color);\n}\nh1, h2, h3 {\n    font-family: "Open Sans", sans-serif;\n    font-family: var(--h-font-text);\n    font-weight: 600;\n    font-weight: var(--h-font-weight);\n    line-height: 1.5;\n    line-height: var(--h-line-height);\n    color: rgb(220, 220, 220);\n    color: var(--h-font-color);\n}\n\n.user-wrapper h2 {\n    font-weight: 200;\n    font-size: 1em;\n    text-align: center;\n\n}\n.user-wrapper h3 {\n    font-weight: 100;\n    font-size: 0.8em;\n    text-align: center;\n}\n\ninput {\n    background-image: url(' + r(e(3)) + ");\n    height: 30px;\n    width: 300px;\n    color: rgb(220, 220, 220);\n    color: var(--color-fg);\n    border: 1px solid rgba(0, 0, 0, 0.2);\n    border: 1px solid var(--color-bg);\n}\n\n/* Announcement content */\n.announce {\n    display: flex;\n    min-height: 300px;\n}\n.announce-content {\n    color: white; background-color: rgba(20, 20, 20, 0.6);\n    padding: 30px;\n    margin: 30px 30px 30px 0px;\n}\n.avatar {\n    align-self: flex-start;\n    flex-grow: 0;\n    flex-shrink: 0;\n    margin: 30px;\n    border-radius: 50%;\n    overflow: hidden;\n    box-shadow: 0 0 0 5px rgb(20, 20, 20);\n    height: 100px;\n    width: 100px;\n}\n.avatar img {\n    height: 100%;\n    margin: 0 auto;\n}\n", ""])
}, function (n, t) {
    n.exports = function (n) {
        var t = "undefined" != typeof window && window.location;
        if (!t) throw new Error("fixUrls requires window.location");
        if (!n || "string" != typeof n) return n;
        var e = t.protocol + "//" + t.host, r = e + t.pathname.replace(/\/[^\/]*$/, "/");
        return n.replace(/url\s*\(((?:[^)(]|\((?:[^)(]+|\([^)(]*\))*\))*)\)/gi, function (n, t) {
            var o, i = t.trim().replace(/^"(.*)"$/, function (n, t) {
                return t
            }).replace(/^'(.*)'$/, function (n, t) {
                return t
            });
            return /^(#|data:|http:\/\/|https:\/\/|file:\/\/\/|\s*$)/i.test(i) ? n : (o = 0 === i.indexOf("//") ? i : 0 === i.indexOf("/") ? e + i : r + i.replace(/^\.\//, ""), "url(" + JSON.stringify(o) + ")")
        })
    }
}, function (n, t, e) {
    var r = e(9);
    "string" == typeof r && (r = [[n.i, r, ""]]);
    var o = {hmr: !0, transform: void 0, insertInto: void 0};
    e(1)(r, o);
    r.locals && (n.exports = r.locals)
}, function (n, t, e) {
    (t = n.exports = e(0)(!1)).push([n.i, "@import url(https://fonts.googleapis.com/css?family=Open+Sans:600|PT+Sans);", ""]), t.push([n.i, ':root {\n    --color-fg: rgb(220, 220, 220);\n    --color-bg: rgba(0, 0, 0, 0.2)\n}\n:root {\n    --p-font-text: "PT Sans", sans-serif;\n    --p-font-weight: 400;\n    --p-font-color: rgb(220, 220, 220);\n    --p-font-color: var(--color-fg);\n\n    --h-font-text: "Open Sans", sans-serif;\n    --h-font-weight: 600;\n    --h-line-height: 1.5;\n    --h-font-color: rgb(220, 220, 220);\n    --h-font-color: var(--color-fg);\n}\n\n.ban-header input {\n    float: left;\n}\n.ban-search {\n    background-color: rgb(10, 10, 10);\n    background-image: none;\n    padding: 10px;\n    box-sizing: border-box;\n}\n.ban-table {\n    width: 100%;\n    text-align: left;\n    color: rgb(220, 220, 220);\n    color: var(--color-fg);\n}\n.ban-table tr {\n    line-height: 30px;\n}\n.ban-table td, .ban-table tr {\n    text-align: center;\n    padding: 0 10px 0;\n}\n.ban-container {\n    min-height: 800px;\n\n}\n.ban-checkbox {\n    width: 16px;\n    height: 16px;\n    line-height: 30px;\n}\n.ban-span {\n    margin: 15px;\n}\n', ""])
}, function (n, t, e) {
    var r = e(11);
    "string" == typeof r && (r = [[n.i, r, ""]]);
    var o = {hmr: !0, transform: void 0, insertInto: void 0};
    e(1)(r, o);
    r.locals && (n.exports = r.locals)
}, function (n, t, e) {
    var r = e(2);
    (n.exports = e(0)(!1)).push([n.i, ".server-icon {\n    width: 100px;\n    margin: 30px 40px;\n    background-image: url(" + r(e(12)) + ");\n    background-repeat: no-repeat;\n    background-position: center;\n}\n.offline {\n    background-image: url(" + r(e(13)) + ") !important;\n}\n.lesser-manifest-table {\n    width: 20%;\n    min-width: 170px;\n}\n.lesser-manifest-container {\n    display: flex;\n    justify-content: center;\n}\n.lesser-manifest-table td {\n    display: block;\n}\n", ""])
}, function (n, t, e) {
    n.exports = e.p + "/Gamma/resources/assets/8a856a2f03ef3dd81ebb6b221e619cbb.svg"
}, function (n, t, e) {
    n.exports = e.p + "/Gamma/resources/assets/ab77299f6bebebb890f6d770e101eb78.svg"
}, function (n, t, e) {
    var r = e(15);
    "string" == typeof r && (r = [[n.i, r, ""]]);
    var o = {hmr: !0, transform: void 0, insertInto: void 0};
    e(1)(r, o);
    r.locals && (n.exports = r.locals)
}, function (n, t, e) {
    var r = e(2);
    (n.exports = e(0)(!1)).push([n.i, "@-webkit-keyframes parallaxAnimation {\n    from {\n        background-position: 0 0;\n    }\n    to {\n        background-position: -960px 0;\n    }\n}\n\n@keyframes parallaxAnimation {\n    from {\n        background-position: 0 0;\n    }\n    to {\n        background-position: -960px 0;\n    }\n}\n\n.layer1, .layer2 {\n    position: fixed;\n    width: 100%;\n    height: 100%;\n    top: 0;\n    left: 0;\n    background-repeat: repeat;\n    z-index: -999;\n}\n\n.layer1 {\n    background-image: url(" + r(e(16)) + ");\n    -webkit-animation: parallaxAnimation 100s linear infinite;\n            animation: parallaxAnimation 100s linear infinite;\n}\n.layer2 {\n    background-image: url(" + r(e(17)) + ");\n    -webkit-animation: parallaxAnimation 50s linear infinite;\n            animation: parallaxAnimation 50s linear infinite;\n}\n\n", ""])
}, function (n, t, e) {
    n.exports = e.p + "/Gamma/resources/assets/4128654496152aea967970201a23227b.png"
}, function (n, t, e) {
    n.exports = e.p + "/Gamma/resources/assets/3558acbeaf7005412ee361882358eece.png"
}, function (n, t, e) {
    var r = e(19);
    "string" == typeof r && (r = [[n.i, r, ""]]);
    var o = {hmr: !0, transform: void 0, insertInto: void 0};
    e(1)(r, o);
    r.locals && (n.exports = r.locals)
}, function (n, t, e) {
    var r = e(2);
    (n.exports = e(0)(!1)).push([n.i, ".flex-layout {\n    display: flex;\n    flex-direction: column;\n}\n.flex-body {\n    display: flex;\n}\n.flex-sidebar {\n    flex: 0 0 17em;\n    order: -1;\n}\n.flex-sidebar-right {\n    flex: 0 0 17em;\n    order: 2;\n}\n.flex-content {\n    width: 100%;\n    min-height: 800px;\n    padding-top: 50px;\n    padding-bottom: 50px;\n    background-image: url(" + r(e(3)) + ");\n}\n", ""])
}, function (n) {
    n.exports = {}
}, function (n) {
    n.exports = {}
}]);