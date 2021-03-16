package io.github.jzdayz.mvc;

import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;
@Component
public class MyView extends AbstractView {

  @Override
  protected void renderMergedOutputModel(Map<String, Object> map,
      HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
      throws Exception {
    httpServletResponse.setStatus(200);
    ServletOutputStream outputStream = httpServletResponse.getOutputStream();
    outputStream.print("OK");
    outputStream.flush();
  }
}
