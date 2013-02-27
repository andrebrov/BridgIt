(function ($) {
    var getStringForElement = function (el) {
        var string = el.tagName.toLowerCase();

        if (el.id) {
            string += "#" + el.id;
        }
        if (el.className) {
            string += "." + el.className.replace(/ /g, '.');
        }

        return string;
    };

    $.fn.getDomPath = function (string) {
        if (typeof(string) == "undefined") {
            string = true;
        }

        var p = [],
            el = $(this).first();
        el.parents().not('html').each(function () {
            p.push(getStringForElement(this));
        });
        p.reverse();
        p.push(getStringForElement(el[0]));
        return string ? p.join(" > ") : p;
    };
})(jQuery);