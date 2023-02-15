package org.test;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
//@JavaScript("vaadin://js/init.js")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener(e -> {
            layout.addComponent(new Label("Thanks " + name.getValue()
                    + ", it works!"));
        });

        layout.addComponents(name, button);

        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
        @Override
        protected void servletInitialized() throws ServletException {
            super.servletInitialized();
            getService().addSessionInitListener(e -> {
                e.getSession().addBootstrapListener(new BootstrapListener() {
                    @Override
                    public void modifyBootstrapFragment(BootstrapFragmentResponse bootstrapFragmentResponse) {
                    }

                    @Override
                    public void modifyBootstrapPage(BootstrapPageResponse bootstrapPageResponse) {
                        Document document = bootstrapPageResponse.getDocument();
                        for (Element e : document.body().children()) {
                            if (e.tagName().equals("script") && e.attr("src").contains("vaadinBootstrap.js")) {
                                e.attr("src","VAADIN/vaadinBootstrap.js?v=8.14.3");
                                e.attr("integrity", "sha256-f91AD8UHA1EWIfeF6b5WVuny0nZigv9y2cmlXhcqb1Y=");
                            }
                            if (e.tagName().equals("script") && e.html().contains("vaadinBootstrap.js")) {
                                e.html("");
                                e.attr("src","VAADIN/init.js");
                                e.attr("integrity", "sha256-tVTYphMU4jCroNXhvIbrYnLWI7AAZSAP/5AiuVFTJuE=");
                            }
                        }
//                        document.body().appendElement("script")
//                                .attr("type", "text/javascript")
//                                .attr("src", "VAADIN/com.vaadin.DefaultWidgetSet.nocache.js")
//                                .attr("integrity","sha256-RmOirPgzujLV7KMudlEsreSakuCLzWJbNs6xHrsRGiY=");
//                            document.body().appendElement("script")
//                                    .attr("type", "text/javascript")
//                                    .attr("src", "VAADIN/54560EC340AA00165878B372BB99DDF7.cache.js")
//                                    .attr("integrity","sha256-+owkkYPQQydJP6xKNE9XFIomEWYkH9yfS0WgDQwr6Dc=");
//                            document.body().appendElement("script")
//                                    .attr("type", "text/javascript")
//                                    .attr("src", "VAADIN/2.cache.js")
//                                    .attr("integrity","sha256-ot3MJdaPEvVm+6p76iF1WL+S2kmgPsMYa9Z50OwVc6I=");
//                            document.body().appendElement("script")
//                                    .attr("type", "text/javascript")
//                                    .attr("src", "VAADIN/8.cache.js")
//                                    .attr("integrity","sha256-xIfh6hdQpyEfEfB/p2rlsEV1gg1wJUSF5fe5CA9YLV0=");
                    }
                });
            });
        }
    }
}
