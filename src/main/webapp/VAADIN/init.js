if (!window.vaadin) alert("Failed to load the bootstrap javascript: ./VAADIN/vaadinBootstrap.js?v=8.14.3");
if (typeof window.__gwtStatsEvent != 'function') {
    vaadin.gwtStatsEvents = [];
    window.__gwtStatsEvent = function(event) {vaadin.gwtStatsEvents.push(event); return true;};
}
vaadin.initApplication("vaadin8appcsp-1663115620",{
    "theme": "mytheme",
    "versionInfo": {
        "vaadinVersion": "8.14.3",
        "atmosphereVersion": "2.4.30.vaadin4"
    },
    "widgetset": "com.vaadin.DefaultWidgetSet",
    "widgetsetReady": true,
    "comErrMsg": {
        "caption": "Communication problem",
        "message": "Take note of any unsaved data, and <u>click here</u> or press ESC to continue.",
        "url": null
    },
    "authErrMsg": {
        "caption": "Authentication problem",
        "message": "Take note of any unsaved data, and <u>click here</u> or press ESC to continue.",
        "url": null
    },
    "sessExpMsg": {
        "caption": "Session Expired",
        "message": "Take note of any unsaved data, and <u>click here</u> or press ESC key to continue.",
        "url": null
    },
    "contextRootUrl": "./",
    "vaadinDir": "./VAADIN/",
    "frontendUrl": "vaadin://frontend/es6/",
    "debug": true,
    "standalone": true,
    "heartbeatInterval": 300,
    "serviceUrl": "."
});