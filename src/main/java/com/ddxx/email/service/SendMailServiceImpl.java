package com.ddxx.email.service;

import java.io.File;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Service
public class SendMailServiceImpl implements SendMailService {
	
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Override
    public void sendSimpleTextMail(String fromMail ,String toMail,String ccMail ,String subject,String content) {
    	Assert.notNull(StringUtils.isEmpty(fromMail), "fromMail is null");
    	Assert.notNull(StringUtils.isEmpty(toMail), "toMail is null");
    	Assert.notNull(StringUtils.isEmpty(subject), "subject is null");
    	Assert.notNull(StringUtils.isEmpty(content), "content is null");

    	SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(fromMail);
        mailMessage.setTo(toMail);
        mailMessage.setCc(ccMail);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        try {
            javaMailSender.send(mailMessage);
            System.out.println("发送简单文本邮件成功,主题是：" + subject);
        } catch (Exception e) {
            System.out.println("-----发送简单文本邮件失败!-------" + e.toString());
            e.printStackTrace();
        }
    }

	@Override
	public void sendHtmlMail(String fromMail ,List<String> toMails  ,List<String> ccMails ,String subject,String content) {
    	Assert.notNull(StringUtils.isEmpty(fromMail), "fromMail is null");
    	Assert.notNull(CollectionUtils.isEmpty(toMails), "toMails is null");
    	Assert.notNull(StringUtils.isEmpty(subject), "subject is null");
    	Assert.notNull(StringUtils.isEmpty(content), "content is null");
		MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(fromMail);
            for(String to : toMails){
            	mimeMessageHelper.addTo(to);
            }
            for(String cc : ccMails){
	            mimeMessageHelper.addCc(cc);
            }
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content, true);
            javaMailSender.send(mimeMailMessage);
        } catch (Exception e) {
            System.out.println("-----发生Html件邮件失败!-------" + e.toString());
            e.printStackTrace();  	
        }

	} 
	
	@Override
	public void sendAttachmentMail(String fromMail ,List<String> toMails,List<String> ccMails ,String subject,String content,String path) {
    	Assert.notNull(StringUtils.isEmpty(fromMail), "fromMail is null");
    	Assert.notNull(CollectionUtils.isEmpty(toMails), "toMails is null");
    	Assert.notNull(StringUtils.isEmpty(subject), "subject is null");
    	Assert.notNull(StringUtils.isEmpty(content), "content is null");
		MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(fromMail);
            for(String to : toMails){
            	mimeMessageHelper.addTo(to);
            }
            for(String cc : ccMails){
	            mimeMessageHelper.addCc(cc);
            }
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content,true);
            //文件路径
            FileSystemResource file = new FileSystemResource(new File(path));
            mimeMessageHelper.addAttachment(file.getFilename(), file);

            javaMailSender.send(mimeMailMessage);
        } catch (Exception e) {
            System.out.println("-----发送附件邮件失败!-------" + e.toString());
            e.printStackTrace();        }
    }
	@Override
	 public void sendInlineResourceMail(String from ,List<String> toMails,List<String> ccMails ,String subject,String content,String path,String id) {
	        MimeMessage message = null;
	        try {
	        	 message = javaMailSender.createMimeMessage();
	            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
	            mimeMessageHelper.setFrom(from);
	            for(String to : toMails){
	            	mimeMessageHelper.addTo(to);
	            }
	            for(String cc : ccMails){
		            mimeMessageHelper.addCc(cc);
	            }

	            mimeMessageHelper.setSubject("这是有图片的邮件");
	            mimeMessageHelper.setText(content, true);

	            FileSystemResource res = new FileSystemResource(new File(path));
	            mimeMessageHelper.addInline(id, res);

	            javaMailSender.send(message);
	        } catch (Exception e) {
	            System.out.println("-----发送附件邮件失败!-------" + e.toString());
	            e.printStackTrace();  	        }
	    }
}
