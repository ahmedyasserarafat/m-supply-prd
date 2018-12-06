sap.ui.define('sap/viz/framework/common/util/DataGraphics', [], function() {
    var P = "__data__";
    var a = "__extra_data__";
    var b = "__graphic_info__";
    var c = '__addition_data__';
    var d = "__customization_info__";
    function f(g) {
        var h = g[0]
          , v = g[1];
        if (Array.isArray(h)) {
            for (var i = 0, l = h.length; i < l; i++) {
                this[h[i]] = v[i];
            }
        } else {
            this[h] = v;
        }
    }
    function e(g) {
        var h = {};
        g.forEach(f, h);
        return h;
    }
    var D = {
        buildContext: e,
        setData: function(n, g) {
            n[a] = g;
        },
        getData: function(n) {
            return n[a];
        },
        setContext: function(n, g) {
            var h = Array.isArray(g) ? e(g) : g;
            n[P] = h;
            return h;
        },
        setAdditionCtx: function(n, g) {
            n[c] = g;
            return g;
        },
        getAdditionCtx: function(n) {
            return n[c];
        },
        getContext: function(n) {
            return n[P];
        },
        setGraphicInfo: function(n, g) {
            n[b] = g;
        },
        getGraphicInfo: function(n) {
            return n[b];
        },
        setCustomizationInfo: function(n, g) {
            n[d] = g;
        },
        getCustomizationInfo: function(n) {
            return n[d];
        }
    };
    return D;
});