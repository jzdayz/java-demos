package io.github.jzdayz;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmailTest implements ApplicationRunner {

    private JavaMailSender sender;

    private MailProperties mailProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        MimeMessageHelper helper = new MimeMessageHelper(sender.createMimeMessage(),true,"utf-8");
        helper.setFrom(mailProperties.getUsername());
        helper.setTo("1537775310@qq.com");
        helper.setSubject("TEST");
        helper.setText("test text");
        helper.addAttachment("abbbb.xlsx",new ClassPathResource("/a.xlsx"));
        sender.send(helper.getMimeMessage());
        System.out.println("send email success");
    }
}
