sap.ui.define('sap/viz/chart/components/plots/ScaleHandler', ['sap/viz/framework/common/util/oo', 'sap/viz/framework/common/util/TimeUtil', 'sap/viz/framework/common/util/TypeUtils', 'sap/viz/framework/common/util/DataUtils'], function Setup(o, T, a, D) {
    var S = function(i, e) {
        this.id = i;
        this._options = e || {
            isHorizontal: true
        };
    };
    var L = "line";
    S.prototype.init = function(s, e, r) {
        this._scale = s;
        this._range = r;
    }
    ;
    S.prototype.scale = function(v, i) {
        return this._scale(v);
    }
    ;
    S.prototype.destroy = function() {
        this.id = null;
        this._options = null;
        this._range = null;
        this._scale = null;
        this.type = null;
    }
    ;
    var C = function(i, e) {
        C.superclass.constructor.apply(this, arguments);
        this.type = "category";
    };
    o.extend(C, S);
    C.prototype.init = function(e, s) {
        C.superclass.init.apply(this, arguments);
        if (!e) {
            this._scaleByIndex = null;
            return;
        }
        var f = e.getDomain();
        var g = e.scale.bind(e);
        this._scaleByIndex = [];
        for (var i = 0; i < f.length; i++) {
            this._scaleByIndex[i] = g(f[i]);
        }
        this._options.itemScaleHandler.init(s);
    }
    ;
    C.prototype.destroy = function() {
        C.superclass.destroy.call(this);
        this._scaleByIndex = null;
    }
    ;
    C.prototype.scale = function(v, e) {
        var i = e.index
          , s = e.seriesIndex;
        var r = this._scaleByIndex && this._scaleByIndex[i] ? this._scaleByIndex[i] : [0, 1];
        var f = this._options.itemScaleHandler.get(s, e.rawData) || [0, 1];
        var g = r[1] - r[0];
        var h = g * (f[1] - f[0]) * this._range;
        var j = (r[0] + g * f[0]) * this._range;
        if (this._options.alignment === "center") {
            return [j + h / 2, 0];
        } else {
            return [j, h];
        }
    }
    ;
    var P = function(i, e) {
        P.superclass.constructor.apply(this, arguments);
        this.type = "polar";
    };
    o.extend(P, C);
    P.prototype.scale = function(v, e) {
        var i = e.index
          , s = e.seriesIndex;
        var r = this._scaleByIndex ? this._scaleByIndex[i] : [0, 1];
        return [Math.PI * 2 * r[0], 0];
    }
    ;
    P.prototype.categorySum = function(v, e) {
        return this._scaleByIndex.length;
    }
    ;
    var V = function(i, e) {
        V.superclass.constructor.apply(this, arguments);
        this.type = "value";
        this.continuous = true;
    };
    o.extend(V, S);
    V.prototype.scale = function(v, e) {
        var i = e.index
          , s = e.seriesIndex
          , f = e.dataHandler;
        var g = this._scale.scale(f ? f.getBase(i, v, s) : 0);
        var n = f ? f.add(i, v, s) : v;
        var h = this._scale.scale(n);
        var j = (h - g) * this._range;
        if (this._options.isHorizontal) {
            if (this._options.alignment === "right") {
                return [h * this._range, j];
            } else {
                return [g * this._range, j];
            }
        } else {
            return [(1 - h) * this._range, j];
        }
    }
    ;
    var b = function(i, e) {
        b.superclass.constructor.apply(this, arguments);
        this.type = "value";
        this.continuous = true;
    };
    o.extend(b, S);
    b.prototype.scale = function(v, e) {
        var i = e.index
          , s = e.seriesIndex
          , f = e.dataHandler;
        var g, n, h, j;
        var m, k, l, p, q, r;
        l = this._scale.getAutoDomain();
        p = this._scale.getDomain();
        q = this._scale.getDomainFixed();
        r = this._scale.getRange();
        m = q[0] ? p[0] : l[0];
        k = q[1] ? p[1] : l[1];
        if (m >= k) {
            m = l[0];
            k = l[1];
        }
        if (v < m) {
            g = r[0];
        } else if (v > k) {
            g = r[1];
        } else {
            g = this._scale.scale(f ? f.getBase(i, v, s) : 0);
        }
        n = f ? f.add(i, v, s) : v;
        if (n > k) {
            h = r[1];
        } else if (n < m) {
            h = r[0];
        } else {
            h = this._scale.scale(n);
        }
        j = (h - g) * this._range;
        if (this._options.isHorizontal) {
            if (this._options.alignment === "right") {
                return [h * this._range, j];
            } else {
                return [g * this._range, j];
            }
        } else {
            return [(1 - h) * this._range, j];
        }
    }
    ;
    var c = function(i, e) {
        c.superclass.constructor.apply(this, arguments);
        this.type = "time";
    };
    o.extend(c, V);
    c.prototype.scale = function(v, e) {
        var i = e.index
          , f = e.dataHandler;
        var n = f ? f.add(i, v) : v;
        var m = this._scale.scale(n);
        return [m * this._range, 0];
    }
    ;
    var d = function(i, e) {
        d.superclass.constructor.apply(this, arguments);
        this.type = "time";
        this._barWidth = null;
        this._innerSpace = null;
        this._columnCount = null;
        this._isSingleBar = e.forSingleBar;
    };
    o.extend(d, V);
    d.prototype.scale = function(v, e) {
        var i = e.index
          , f = e.dataHandler;
        var h = this._options;
        var m = h.minTimeLevel;
        var u = h.showAsUTC;
        var n = f ? f.add(i, v) : v;
        var g = this._scale.scale(n);
        var p;
        if (this._isSingleBar) {
            p = this._barWidth / 2;
        } else {
            p = (this._columnCount * this._barWidth + (this._columnCount - 1) * this._innerSpace) / 2 - e.seriesIndex * (this._barWidth + this._innerSpace);
        }
        return [(g - (e.shape === L ? 0 : p)) * this._range, this._barWidth * this._range];
    }
    ;
    d.prototype.updateBarWidth = function(e, i, f) {
        this._barWidth = e;
        this._innerSpace = i;
        this._columnCount = f;
    }
    ;
    var W = function(i, e) {
        V.superclass.constructor.apply(this, arguments);
        this.type = "value";
        this.continuous = true;
    };
    o.extend(W, S);
    W.prototype.scale = function(e, f) {
        var i = f.index
          , s = f.seriesIndex
          , g = f.dataHandler;
        var v = e.value;
        var t = e.total;
        var h = e.base;
        var j = this._scale.scale(g ? g.getBase(i, h, s) : h);
        var n = g ? g.add(i, v, s) : v;
        var k = this._scale.scale(g ? n : t);
        var l = (k - j) * this._range;
        if (this._options.isHorizontal) {
            if (this._options.alignment === "right") {
                return [k * this._range, l];
            } else {
                return [j * this._range, l];
            }
        } else {
            return [(1 - k) * this._range, l];
        }
    }
    ;
    return {
        getCategoryScaleHandler: function(i, e) {
            return new C(i,e);
        },
        getValueScaleHandler: function(i, e) {
            return new V(i,e);
        },
        getTimeScaleHandler: function(i, e) {
            if (e.forBar) {
                return new d(i,e);
            } else {
                return new c(i,e);
            }
        },
        getPolarScaleHandler: function(i, e) {
            return new P(i,e);
        },
        getPerfectValueScaleHandler: function(i, e) {
            return new b(i,e);
        },
        getWTFScaleHandler: function(i, e) {
            return new W(i,e);
        },
        extend: function(s) {
            var e = S;
            o.extend(s, e);
            return s;
        }
    };
});
define('sap/viz/chart/components/renderers/BarRenderer', ["sap/viz/framework/common/util/SVG", 'sap/viz/framework/common/util/DataGraphics', "sap/viz/chart/components/util/DrawUtil", 'sap/viz/framework/common/util/Constants'], function(S, D, a, C) {
    var M = 2;
    var b = function(c) {
        var n;
        var g = c.graphic;
        var w = g.width < 1 && g.width !== 0 ? 1 : g.width;
        var h = g.height < 1 && g.height !== 0 ? 1 : g.height;
        var f = g.fill;
        if (g.pattern === "noFill" && c.path) {
            n = S.create("path");
            n.setAttribute("d", c.path);
            n.setAttribute("stroke-width", isNaN(g.pathStrokeWidth) ? 3 : g.pathStrokeWidth);
            n.setAttribute("fill", g.bgColor);
            n.setAttribute("stroke", g.fill);
        } else {
            n = S.create("rect");
            if (c.effectManager) {
                f = c.effectManager.register({
                    drawingEffect: g.drawingEffect,
                    fillColor: g.fill,
                    patternEffect: g.pattern,
                    isNegative: g.isNegative,
                    direction: c.horizontal ? 'vertical' : 'horizontal'
                });
            }
            n.setAttribute("width", w);
            n.setAttribute("height", h);
            n.setAttribute("fill", f);
            if (g.shapeRendering) {
                n.setAttribute('shape-rendering', g.shapeRendering);
            }
            if (g.stroke) {
                var m = c.twoDirection ? Math.min(h, w) : (c.horizontal ? h : w);
                var s = m > g.strokeWidth + M ? g.strokeWidth : m - M;
                if (s >= 1) {
                    n.setAttribute('stroke', g.stroke);
                    n.setAttribute('stroke-width', g.strokeWidth);
                }
            }
        }
        D.setCustomizationInfo(n, {
            'defaultRenderer': true
        });
        return n;
    };
    b.update = function(n, c) {
        var g = c.graphic;
        var w = g.width < 1 && g.width !== 0 ? 1 : g.width;
        var h = g.height < 1 && g.height !== 0 ? 1 : g.height;
        var s = {
            width: w,
            height: h,
            minSize: c.twoDirection ? Math.min(h, w) : (c.horizontal ? h : w)
        };
        a.barUpdate(n, c, s);
    }
    ;
    b.expose = {
        renderer: b,
        update: b.update
    };
    return b;
});