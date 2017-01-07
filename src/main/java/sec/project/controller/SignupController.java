package sec.project.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

@Controller
public class SignupController{

    @Autowired
    private SignupRepository signupRepository;

   //@RequestMapping("*")
   // public String defaultMapping() {
   //     return "redirect:/form";
    //}

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm() {
        return "form";
    }
    
    // Method to GET unvalidated redirect
        @RequestMapping(value = "/redirect", method = RequestMethod.GET)
        @ResponseBody
        public void method(HttpServletResponse httpServletResponse, @RequestParam("url")String url) throws IOException {
    httpServletResponse.sendRedirect("http://"+url);
}
        
   
    
        @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String list(Model model) { 
        model.addAttribute("users", signupRepository.findAll());        
        return "admin";
    }
    
    
     
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String address) {
        signupRepository.save(new Signup(name, address));               
        return "done";
    }
    
  
   
    
  }

    

