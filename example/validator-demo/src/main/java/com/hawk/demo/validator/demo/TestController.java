package com.hawk.demo.validator.demo;

import com.hawk.demo.validator.demo.model.Param;
import com.hawk.demo.validator.demo.model.R;
import com.hawk.demo.validator.demo.validator.ValidGroup;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.groups.Default;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPOutputStream;

@RestController
@RequestMapping("/validator")
public class TestController {

    @Resource
    private TestService testService;

    @PostMapping("/addc")
    public R<String> validAddInController(@RequestBody @Validated({ValidGroup.A.class, Default.class}) Param param) {
        return R.success("success");
    }

    @PostMapping("/adds")
    public R<String> validAddInService(@RequestBody Param param) {
        return testService.add(param);
    }

    @GetMapping("/gzip")
    public void test(HttpServletRequest request, HttpServletResponse reponse) throws IOException {

        // Response Body
        String content = "Hello Spring Cloud";

        // Encoding types supported by the client
        String acceptEncooding = request.getHeader(HttpHeaders.ACCEPT_ENCODING);

        // It is assumed that the client only supports the GZIP encoding type.
        acceptEncooding = "gzip";

        // The response body is encoded using gzip
        reponse.setHeader(HttpHeaders.CONTENT_ENCODING, acceptEncooding);
        reponse.setContentType(MediaType.TEXT_PLAIN_VALUE);
        reponse.setCharacterEncoding(StandardCharsets.UTF_8.displayName());

        // Response to the client after compression using Gzip
        try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(reponse.getOutputStream())) {
            gzipOutputStream.write(content.getBytes(StandardCharsets.UTF_8));
            gzipOutputStream.finish();
        }
    }
}
