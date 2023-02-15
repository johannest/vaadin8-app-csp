vaadin8-app-csp
==============

A simple Vaadin application used for experimenting possibility of using strict CSP policies with Vaadin 8. 

Approach
--------
* Overrode VaadinServlet to make it possible add `integrity` attributes to the script tags.
* Put inline init script from body to init.js file
* Using CSPFilter to add hashes to CSP headers
* Customized vaadinBootstrap.js to include `integrity` attribute for `com.vaadin.DefaultWidgetSet.nocache.js`

Not working though, this simply might not be possible with Vaadin 8
-----

Workflow
========

To compile the entire project, run "mvn install".

To run the application, run "mvn jetty:run" and open http://localhost:8080/ .

To produce a deployable production mode WAR:
- change productionMode to true in the servlet class configuration (nested in the UI class)
- run "mvn clean package"
- test the war file with "mvn jetty:run-war"
