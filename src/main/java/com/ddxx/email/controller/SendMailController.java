package com.ddxx.email.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ddxx.email.service.SendMailService;

@RestController
public class SendMailController {

	@Autowired
	private SendMailService sendMailService;
	
    // 我的163邮箱
    @Value("${spring.mail.username}")
    private String my163Mail;
	// 我的QQ邮箱
    private String myQQMail = "470042815@qq.com";
	
	// 邮件的主题和内容
	private String subject = "中东部雨雪发展或扰春运 周中大风降温来袭";
	
	private String path = "src/main/resources/static/image/mail.jpg";
   
    private String content = "中国天气网讯 预计今（28日）起三天，" +
            "中东部雨雪发展增多，有一次较明显降水过程，黄淮、江淮等地还可能迎来雨雪相态转换，" +
            "公众春运出行请注意交通安全。而且本周中还将有冷空气来袭，30日至2月1日，中东部大部气" +
            "温下降4～8℃并伴有大风，需注意防风防寒。";
    
    private String htmlContent="<html>\n" +
            "<body>\n" +
            "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
            "</body>\n" +
            "</html>";
    
	
	@RequestMapping("/sendText")
	@ResponseBody
	public String sendMail(){
		sendMailService.sendSimpleTextMail(my163Mail, myQQMail, my163Mail, subject, content);
		return "OK";
	}
	
	@RequestMapping("/sendHtml")
	@ResponseBody
	public String sendHtml(){
		List<String> toMails = new ArrayList<>();
		toMails.add("ddxx5317@126.com");
		toMails.add(myQQMail);
		List<String> ccMails = new ArrayList<>();
		//ccMails.add(myQQMail);
		sendMailService.sendHtmlMail(my163Mail, toMails, ccMails, subject, htmlContent);
		return "OK";
	}
	
	
	@RequestMapping("/sendAttachment")
	@ResponseBody
	public String sendAttachment(){
		List<String> toMails = new ArrayList<>();
		toMails.add(myQQMail);
		List<String> ccMails = new ArrayList<>();
		ccMails.add(myQQMail);
		sendMailService.sendAttachmentMail(my163Mail, toMails, ccMails, subject, content ,path);
		return "OK";
	}
	
	@RequestMapping("/sendInlineResourceMail")
	@ResponseBody
	public String sendInlineResourceMail(){
		List<String> toMails = new ArrayList<>();
		toMails.add(myQQMail);
		List<String> ccMails = new ArrayList<>();
		ccMails.add(myQQMail);
		sendMailService.sendInlineResourceMail(my163Mail, toMails, ccMails, subject, content,path,null);
		return "OK";
	}
}
