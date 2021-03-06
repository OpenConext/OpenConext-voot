package voot.web;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Properties;

@RestController
public class GitPluginController {

  @RequestMapping(method = RequestMethod.GET, value = "/public/git")
  public Properties gitInfo() throws IOException {
    Properties props = new Properties();
    props.load(new ClassPathResource("git.properties").getInputStream());
    return props;
  }

}
