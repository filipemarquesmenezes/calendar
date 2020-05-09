'use strict';

(function () {
    function init() {
        var router = new Router([
            new Route('people', 'people'),
            new Route('calendar', 'calendar', "calendar.js"),
            new Route('about', 'about.html')
        ]);
    }
    init();
}());