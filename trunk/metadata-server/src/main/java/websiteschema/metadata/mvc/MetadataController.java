/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package websiteschema.metadata.mvc;

//import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ray
 */
@Controller
@RequestMapping(value = "/metadata")
public class MetadataController {

    Logger l = Logger.getRootLogger();

//    @RequestMapping(value="/user", method = RequestMethod.GET)
//    public String user() {
//        System.out.println("getUser");
//        return "metadata/user";
//    }
//
//    @RequestMapping(value="/{id}", method = RequestMethod.GET)
//    public String getId(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
//        System.out.println("getUser " + id);
//        return "metadata/user";
//    }
    @RequestMapping(value = "/**", method = RequestMethod.GET)
    public String view(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getQueryString();
        String url = request.getPathInfo();
        l.trace("path:" + url + " uri: " + uri);
        return url;
    }
}
